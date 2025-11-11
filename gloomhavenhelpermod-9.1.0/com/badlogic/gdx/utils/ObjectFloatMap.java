package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectFloatMap implements Iterable {
   public int size;
   Object[] keyTable;
   float[] valueTable;
   float loadFactor;
   int threshold;
   protected int shift;
   protected int mask;
   transient ObjectFloatMap.Entries entries1;
   transient ObjectFloatMap.Entries entries2;
   transient ObjectFloatMap.Values values1;
   transient ObjectFloatMap.Values values2;
   transient ObjectFloatMap.Keys keys1;
   transient ObjectFloatMap.Keys keys2;

   public ObjectFloatMap() {
      this(51, 0.8F);
   }

   public ObjectFloatMap(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public ObjectFloatMap(int initialCapacity, float loadFactor) {
      if (!(loadFactor <= 0.0F) && !(loadFactor >= 1.0F)) {
         this.loadFactor = loadFactor;
         int tableSize = ObjectSet.tableSize(initialCapacity, loadFactor);
         this.threshold = (int)(tableSize * loadFactor);
         this.mask = tableSize - 1;
         this.shift = Long.numberOfLeadingZeros(this.mask);
         this.keyTable = new Object[tableSize];
         this.valueTable = new float[tableSize];
      } else {
         throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + loadFactor);
      }
   }

   public ObjectFloatMap(ObjectFloatMap map) {
      this((int)Math.floor(map.keyTable.length * map.loadFactor), map.loadFactor);
      System.arraycopy(map.keyTable, 0, this.keyTable, 0, map.keyTable.length);
      System.arraycopy(map.valueTable, 0, this.valueTable, 0, map.valueTable.length);
      this.size = map.size;
   }

   protected int place(Object item) {
      return (int)(item.hashCode() * -7046029254386353131L >>> this.shift);
   }

   int locateKey(Object key) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         Object[] keyTable = (K[])this.keyTable;
         int i = this.place(key);

         while (true) {
            Object other = keyTable[i];
            if (other == null) {
               return -(i + 1);
            }

            if (other.equals(key)) {
               return i;
            }

            i = i + 1 & this.mask;
         }
      }
   }

   public void put(Object key, float value) {
      int i = this.locateKey(key);
      if (i >= 0) {
         this.valueTable[i] = value;
      } else {
         i = -(i + 1);
         this.keyTable[i] = key;
         this.valueTable[i] = value;
         if (++this.size >= this.threshold) {
            this.resize(this.keyTable.length << 1);
         }
      }
   }

   public float put(Object key, float value, float defaultValue) {
      int i = this.locateKey(key);
      if (i >= 0) {
         float oldValue = this.valueTable[i];
         this.valueTable[i] = value;
         return oldValue;
      } else {
         i = -(i + 1);
         this.keyTable[i] = key;
         this.valueTable[i] = value;
         if (++this.size >= this.threshold) {
            this.resize(this.keyTable.length << 1);
         }

         return defaultValue;
      }
   }

   public void putAll(ObjectFloatMap map) {
      this.ensureCapacity(map.size);
      Object[] keyTable = (K[])map.keyTable;
      float[] valueTable = map.valueTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         Object key = keyTable[i];
         if (key != null) {
            this.put(key, valueTable[i]);
         }
      }
   }

   private void putResize(Object key, float value) {
      Object[] keyTable = (K[])this.keyTable;
      int i = this.place(key);

      while (keyTable[i] != null) {
         i = i + 1 & this.mask;
      }

      keyTable[i] = key;
      this.valueTable[i] = value;
   }

   public float get(Object key, float defaultValue) {
      int i = this.locateKey(key);
      return i < 0 ? defaultValue : this.valueTable[i];
   }

   public float getAndIncrement(Object key, float defaultValue, float increment) {
      int i = this.locateKey(key);
      if (i >= 0) {
         float oldValue = this.valueTable[i];
         this.valueTable[i] = this.valueTable[i] + increment;
         return oldValue;
      } else {
         i = -(i + 1);
         this.keyTable[i] = key;
         this.valueTable[i] = defaultValue + increment;
         if (++this.size >= this.threshold) {
            this.resize(this.keyTable.length << 1);
         }

         return defaultValue;
      }
   }

   public float remove(Object key, float defaultValue) {
      int i = this.locateKey(key);
      if (i < 0) {
         return defaultValue;
      } else {
         Object[] keyTable = (K[])this.keyTable;
         float[] valueTable = this.valueTable;
         float oldValue = valueTable[i];
         int mask = this.mask;

         for (int next = i + 1 & mask; (key = keyTable[next]) != null; next = next + 1 & mask) {
            int placement = this.place(key);
            if ((next - placement & mask) > (i - placement & mask)) {
               keyTable[i] = key;
               valueTable[i] = valueTable[next];
               i = next;
            }
         }

         keyTable[i] = null;
         this.size--;
         return oldValue;
      }
   }

   public boolean notEmpty() {
      return this.size > 0;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public void shrink(int maximumCapacity) {
      if (maximumCapacity < 0) {
         throw new IllegalArgumentException("maximumCapacity must be >= 0: " + maximumCapacity);
      } else {
         int tableSize = ObjectSet.tableSize(maximumCapacity, this.loadFactor);
         if (this.keyTable.length > tableSize) {
            this.resize(tableSize);
         }
      }
   }

   public void clear(int maximumCapacity) {
      int tableSize = ObjectSet.tableSize(maximumCapacity, this.loadFactor);
      if (this.keyTable.length <= tableSize) {
         this.clear();
      } else {
         this.size = 0;
         this.resize(tableSize);
      }
   }

   public void clear() {
      if (this.size != 0) {
         this.size = 0;
         Arrays.fill(this.keyTable, null);
      }
   }

   public boolean containsValue(float value) {
      Object[] keyTable = (K[])this.keyTable;
      float[] valueTable = this.valueTable;

      for (int i = valueTable.length - 1; i >= 0; i--) {
         if (keyTable[i] != null && valueTable[i] == value) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(float value, float epsilon) {
      Object[] keyTable = (K[])this.keyTable;
      float[] valueTable = this.valueTable;

      for (int i = valueTable.length - 1; i >= 0; i--) {
         if (keyTable[i] != null && Math.abs(valueTable[i] - value) <= epsilon) {
            return true;
         }
      }

      return false;
   }

   public boolean containsKey(Object key) {
      return this.locateKey(key) >= 0;
   }

   @Null
   public Object findKey(float value) {
      Object[] keyTable = (K[])this.keyTable;
      float[] valueTable = this.valueTable;

      for (int i = valueTable.length - 1; i >= 0; i--) {
         Object key = keyTable[i];
         if (key != null && valueTable[i] == value) {
            return key;
         }
      }

      return null;
   }

   @Null
   public Object findKey(float value, float epsilon) {
      Object[] keyTable = (K[])this.keyTable;
      float[] valueTable = this.valueTable;

      for (int i = valueTable.length - 1; i >= 0; i--) {
         Object key = keyTable[i];
         if (key != null && Math.abs(valueTable[i] - value) <= epsilon) {
            return key;
         }
      }

      return null;
   }

   public void ensureCapacity(int additionalCapacity) {
      int tableSize = ObjectSet.tableSize(this.size + additionalCapacity, this.loadFactor);
      if (this.keyTable.length < tableSize) {
         this.resize(tableSize);
      }
   }

   final void resize(int newSize) {
      int oldCapacity = this.keyTable.length;
      this.threshold = (int)(newSize * this.loadFactor);
      this.mask = newSize - 1;
      this.shift = Long.numberOfLeadingZeros(this.mask);
      Object[] oldKeyTable = (K[])this.keyTable;
      float[] oldValueTable = this.valueTable;
      this.keyTable = new Object[newSize];
      this.valueTable = new float[newSize];
      if (this.size > 0) {
         for (int i = 0; i < oldCapacity; i++) {
            Object key = oldKeyTable[i];
            if (key != null) {
               this.putResize(key, oldValueTable[i]);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int h = this.size;
      Object[] keyTable = (K[])this.keyTable;
      float[] valueTable = this.valueTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         Object key = keyTable[i];
         if (key != null) {
            h += key.hashCode() + NumberUtils.floatToRawIntBits(valueTable[i]);
         }
      }

      return h;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ObjectFloatMap)) {
         return false;
      } else {
         ObjectFloatMap other = (ObjectFloatMap)obj;
         if (other.size != this.size) {
            return false;
         } else {
            Object[] keyTable = (K[])this.keyTable;
            float[] valueTable = this.valueTable;
            int i = 0;

            for (int n = keyTable.length; i < n; i++) {
               Object key = keyTable[i];
               if (key != null) {
                  float otherValue = other.get(key, 0.0F);
                  if (otherValue == 0.0F && !other.containsKey(key)) {
                     return false;
                  }

                  if (otherValue != valueTable[i]) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public String toString(String separator) {
      return this.toString(separator, false);
   }

   @Override
   public String toString() {
      return this.toString(", ", true);
   }

   private String toString(String separator, boolean braces) {
      if (this.size == 0) {
         return braces ? "{}" : "";
      } else {
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         if (braces) {
            buffer.append('{');
         }

         Object[] keyTable = (K[])this.keyTable;
         float[] valueTable = this.valueTable;
         int i = keyTable.length;

         while (i-- > 0) {
            Object key = keyTable[i];
            if (key != null) {
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
               break;
            }
         }

         while (i-- > 0) {
            Object key = keyTable[i];
            if (key != null) {
               buffer.append(separator);
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
            }
         }

         if (braces) {
            buffer.append('}');
         }

         return buffer.toString();
      }
   }

   public ObjectFloatMap.Entries iterator() {
      return this.entries();
   }

   public ObjectFloatMap.Entries entries() {
      if (Collections.allocateIterators) {
         return new ObjectFloatMap.Entries(this);
      } else {
         if (this.entries1 == null) {
            this.entries1 = new ObjectFloatMap.Entries(this);
            this.entries2 = new ObjectFloatMap.Entries(this);
         }

         if (!this.entries1.valid) {
            this.entries1.reset();
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
         } else {
            this.entries2.reset();
            this.entries2.valid = true;
            this.entries1.valid = false;
            return this.entries2;
         }
      }
   }

   public ObjectFloatMap.Values values() {
      if (Collections.allocateIterators) {
         return new ObjectFloatMap.Values(this);
      } else {
         if (this.values1 == null) {
            this.values1 = new ObjectFloatMap.Values(this);
            this.values2 = new ObjectFloatMap.Values(this);
         }

         if (!this.values1.valid) {
            this.values1.reset();
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
         } else {
            this.values2.reset();
            this.values2.valid = true;
            this.values1.valid = false;
            return this.values2;
         }
      }
   }

   public ObjectFloatMap.Keys keys() {
      if (Collections.allocateIterators) {
         return new ObjectFloatMap.Keys(this);
      } else {
         if (this.keys1 == null) {
            this.keys1 = new ObjectFloatMap.Keys(this);
            this.keys2 = new ObjectFloatMap.Keys(this);
         }

         if (!this.keys1.valid) {
            this.keys1.reset();
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
         } else {
            this.keys2.reset();
            this.keys2.valid = true;
            this.keys1.valid = false;
            return this.keys2;
         }
      }
   }

   public static class Entries extends ObjectFloatMap.MapIterator implements Iterable, Iterator {
      ObjectFloatMap.Entry entry = new ObjectFloatMap.Entry();

      public Entries(ObjectFloatMap map) {
         super(map);
      }

      public ObjectFloatMap.Entry next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object[] keyTable = (K[])this.map.keyTable;
            this.entry.key = keyTable[this.nextIndex];
            this.entry.value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return this.entry;
         }
      }

      @Override
      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.hasNext;
         }
      }

      public ObjectFloatMap.Entries iterator() {
         return this;
      }
   }

   public static class Entry {
      public Object key;
      public float value;

      @Override
      public String toString() {
         return this.key + "=" + this.value;
      }
   }

   public static class Keys extends ObjectFloatMap.MapIterator implements Iterable, Iterator {
      public Keys(ObjectFloatMap map) {
         super(map);
      }

      @Override
      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.hasNext;
         }
      }

      @Override
      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object key = (K)this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return key;
         }
      }

      public ObjectFloatMap.Keys iterator() {
         return this;
      }

      public Array toArray() {
         return this.toArray(new Array(true, this.map.size));
      }

      public Array toArray(Array array) {
         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }

   private static class MapIterator {
      public boolean hasNext;
      final ObjectFloatMap map;
      int nextIndex;
      int currentIndex;
      boolean valid = true;

      public MapIterator(ObjectFloatMap map) {
         this.map = map;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = -1;
         this.findNextIndex();
      }

      void findNextIndex() {
         Object[] keyTable = (K[])this.map.keyTable;
         int n = keyTable.length;

         while (++this.nextIndex < n) {
            if (keyTable[this.nextIndex] != null) {
               this.hasNext = true;
               return;
            }
         }

         this.hasNext = false;
      }

      public void remove() {
         int i = this.currentIndex;
         if (i < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            Object[] keyTable = (K[])this.map.keyTable;
            float[] valueTable = this.map.valueTable;
            int mask = this.map.mask;

            Object key;
            for (int next = i + 1 & mask; (key = keyTable[next]) != null; next = next + 1 & mask) {
               int placement = this.map.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  valueTable[i] = valueTable[next];
                  i = next;
               }
            }

            keyTable[i] = null;
            this.map.size--;
            if (i != this.currentIndex) {
               this.nextIndex--;
            }

            this.currentIndex = -1;
         }
      }
   }

   public static class Values extends ObjectFloatMap.MapIterator {
      public Values(ObjectFloatMap map) {
         super(map);
      }

      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.hasNext;
         }
      }

      public float next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            float value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return value;
         }
      }

      public ObjectFloatMap.Values iterator() {
         return this;
      }

      public FloatArray toArray() {
         FloatArray array = new FloatArray(true, this.map.size);

         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }

      public FloatArray toArray(FloatArray array) {
         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }
}
