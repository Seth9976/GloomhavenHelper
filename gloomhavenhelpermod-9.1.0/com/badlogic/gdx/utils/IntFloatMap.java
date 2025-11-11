package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntFloatMap implements Iterable {
   public int size;
   int[] keyTable;
   float[] valueTable;
   float zeroValue;
   boolean hasZeroValue;
   private final float loadFactor;
   private int threshold;
   protected int shift;
   protected int mask;
   private transient IntFloatMap.Entries entries1;
   private transient IntFloatMap.Entries entries2;
   private transient IntFloatMap.Values values1;
   private transient IntFloatMap.Values values2;
   private transient IntFloatMap.Keys keys1;
   private transient IntFloatMap.Keys keys2;

   public IntFloatMap() {
      this(51, 0.8F);
   }

   public IntFloatMap(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public IntFloatMap(int initialCapacity, float loadFactor) {
      if (!(loadFactor <= 0.0F) && !(loadFactor >= 1.0F)) {
         this.loadFactor = loadFactor;
         int tableSize = ObjectSet.tableSize(initialCapacity, loadFactor);
         this.threshold = (int)(tableSize * loadFactor);
         this.mask = tableSize - 1;
         this.shift = Long.numberOfLeadingZeros(this.mask);
         this.keyTable = new int[tableSize];
         this.valueTable = new float[tableSize];
      } else {
         throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + loadFactor);
      }
   }

   public IntFloatMap(IntFloatMap map) {
      this((int)(map.keyTable.length * map.loadFactor), map.loadFactor);
      System.arraycopy(map.keyTable, 0, this.keyTable, 0, map.keyTable.length);
      System.arraycopy(map.valueTable, 0, this.valueTable, 0, map.valueTable.length);
      this.size = map.size;
      this.zeroValue = map.zeroValue;
      this.hasZeroValue = map.hasZeroValue;
   }

   protected int place(int item) {
      return (int)(item * -7046029254386353131L >>> this.shift);
   }

   private int locateKey(int key) {
      int[] keyTable = this.keyTable;
      int i = this.place(key);

      while (true) {
         int other = keyTable[i];
         if (other == 0) {
            return -(i + 1);
         }

         if (other == key) {
            return i;
         }

         i = i + 1 & this.mask;
      }
   }

   public void put(int key, float value) {
      if (key == 0) {
         this.zeroValue = value;
         if (!this.hasZeroValue) {
            this.hasZeroValue = true;
            this.size++;
         }
      } else {
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
   }

   public float put(int key, float value, float defaultValue) {
      if (key == 0) {
         float oldValue = this.zeroValue;
         this.zeroValue = value;
         if (!this.hasZeroValue) {
            this.hasZeroValue = true;
            this.size++;
            return defaultValue;
         } else {
            return oldValue;
         }
      } else {
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
   }

   public void putAll(IntFloatMap map) {
      this.ensureCapacity(map.size);
      if (map.hasZeroValue) {
         this.put(0, map.zeroValue);
      }

      int[] keyTable = map.keyTable;
      float[] valueTable = map.valueTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         int key = keyTable[i];
         if (key != 0) {
            this.put(key, valueTable[i]);
         }
      }
   }

   private void putResize(int key, float value) {
      int[] keyTable = this.keyTable;
      int i = this.place(key);

      while (keyTable[i] != 0) {
         i = i + 1 & this.mask;
      }

      keyTable[i] = key;
      this.valueTable[i] = value;
   }

   public float get(int key, float defaultValue) {
      if (key == 0) {
         return this.hasZeroValue ? this.zeroValue : defaultValue;
      } else {
         int i = this.locateKey(key);
         return i >= 0 ? this.valueTable[i] : defaultValue;
      }
   }

   public float getAndIncrement(int key, float defaultValue, float increment) {
      if (key == 0) {
         if (!this.hasZeroValue) {
            this.hasZeroValue = true;
            this.zeroValue = defaultValue + increment;
            this.size++;
            return defaultValue;
         } else {
            float oldValue = this.zeroValue;
            this.zeroValue += increment;
            return oldValue;
         }
      } else {
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
   }

   public float remove(int key, float defaultValue) {
      if (key == 0) {
         if (!this.hasZeroValue) {
            return defaultValue;
         } else {
            this.hasZeroValue = false;
            this.size--;
            return this.zeroValue;
         }
      } else {
         int i = this.locateKey(key);
         if (i < 0) {
            return defaultValue;
         } else {
            int[] keyTable = this.keyTable;
            float[] valueTable = this.valueTable;
            float oldValue = valueTable[i];
            int mask = this.mask;

            for (int next = i + 1 & mask; (key = keyTable[next]) != 0; next = next + 1 & mask) {
               int placement = this.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  valueTable[i] = valueTable[next];
                  i = next;
               }
            }

            keyTable[i] = 0;
            this.size--;
            return oldValue;
         }
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
         this.hasZeroValue = false;
         this.resize(tableSize);
      }
   }

   public void clear() {
      if (this.size != 0) {
         Arrays.fill(this.keyTable, 0);
         this.size = 0;
         this.hasZeroValue = false;
      }
   }

   public boolean containsValue(float value) {
      if (this.hasZeroValue && this.zeroValue == value) {
         return true;
      } else {
         int[] keyTable = this.keyTable;
         float[] valueTable = this.valueTable;

         for (int i = valueTable.length - 1; i >= 0; i--) {
            if (keyTable[i] != 0 && valueTable[i] == value) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean containsValue(float value, float epsilon) {
      if (this.hasZeroValue && Math.abs(this.zeroValue - value) <= epsilon) {
         return true;
      } else {
         int[] keyTable = this.keyTable;
         float[] valueTable = this.valueTable;

         for (int i = valueTable.length - 1; i >= 0; i--) {
            if (keyTable[i] != 0 && Math.abs(valueTable[i] - value) <= epsilon) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean containsKey(int key) {
      return key == 0 ? this.hasZeroValue : this.locateKey(key) >= 0;
   }

   public int findKey(float value, int notFound) {
      if (this.hasZeroValue && this.zeroValue == value) {
         return 0;
      } else {
         int[] keyTable = this.keyTable;
         float[] valueTable = this.valueTable;

         for (int i = valueTable.length - 1; i >= 0; i--) {
            if (keyTable[i] != 0 && valueTable[i] == value) {
               return keyTable[i];
            }
         }

         return notFound;
      }
   }

   public int findKey(float value, float epsilon, int notFound) {
      if (this.hasZeroValue && Math.abs(this.zeroValue - value) <= epsilon) {
         return 0;
      } else {
         int[] keyTable = this.keyTable;
         float[] valueTable = this.valueTable;

         for (int i = valueTable.length - 1; i >= 0; i--) {
            if (keyTable[i] != 0 && Math.abs(valueTable[i] - value) <= epsilon) {
               return keyTable[i];
            }
         }

         return notFound;
      }
   }

   public void ensureCapacity(int additionalCapacity) {
      int tableSize = ObjectSet.tableSize(this.size + additionalCapacity, this.loadFactor);
      if (this.keyTable.length < tableSize) {
         this.resize(tableSize);
      }
   }

   private void resize(int newSize) {
      int oldCapacity = this.keyTable.length;
      this.threshold = (int)(newSize * this.loadFactor);
      this.mask = newSize - 1;
      this.shift = Long.numberOfLeadingZeros(this.mask);
      int[] oldKeyTable = this.keyTable;
      float[] oldValueTable = this.valueTable;
      this.keyTable = new int[newSize];
      this.valueTable = new float[newSize];
      if (this.size > 0) {
         for (int i = 0; i < oldCapacity; i++) {
            int key = oldKeyTable[i];
            if (key != 0) {
               this.putResize(key, oldValueTable[i]);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int h = this.size;
      if (this.hasZeroValue) {
         h += NumberUtils.floatToRawIntBits(this.zeroValue);
      }

      int[] keyTable = this.keyTable;
      float[] valueTable = this.valueTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         int key = keyTable[i];
         if (key != 0) {
            h += key * 31 + NumberUtils.floatToRawIntBits(valueTable[i]);
         }
      }

      return h;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof IntFloatMap)) {
         return false;
      } else {
         IntFloatMap other = (IntFloatMap)obj;
         if (other.size != this.size) {
            return false;
         } else if (other.hasZeroValue != this.hasZeroValue) {
            return false;
         } else if (this.hasZeroValue && other.zeroValue != this.zeroValue) {
            return false;
         } else {
            int[] keyTable = this.keyTable;
            float[] valueTable = this.valueTable;
            int i = 0;

            for (int n = keyTable.length; i < n; i++) {
               int key = keyTable[i];
               if (key != 0) {
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

   @Override
   public String toString() {
      if (this.size == 0) {
         return "[]";
      } else {
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         buffer.append('[');
         int[] keyTable = this.keyTable;
         float[] valueTable = this.valueTable;
         int i = keyTable.length;
         if (this.hasZeroValue) {
            buffer.append("0=");
            buffer.append(this.zeroValue);
         } else {
            while (i-- > 0) {
               int key = keyTable[i];
               if (key != 0) {
                  buffer.append(key);
                  buffer.append('=');
                  buffer.append(valueTable[i]);
                  break;
               }
            }
         }

         while (i-- > 0) {
            int key = keyTable[i];
            if (key != 0) {
               buffer.append(", ");
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
            }
         }

         buffer.append(']');
         return buffer.toString();
      }
   }

   @Override
   public Iterator iterator() {
      return this.entries();
   }

   public IntFloatMap.Entries entries() {
      if (Collections.allocateIterators) {
         return new IntFloatMap.Entries(this);
      } else {
         if (this.entries1 == null) {
            this.entries1 = new IntFloatMap.Entries(this);
            this.entries2 = new IntFloatMap.Entries(this);
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

   public IntFloatMap.Values values() {
      if (Collections.allocateIterators) {
         return new IntFloatMap.Values(this);
      } else {
         if (this.values1 == null) {
            this.values1 = new IntFloatMap.Values(this);
            this.values2 = new IntFloatMap.Values(this);
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

   public IntFloatMap.Keys keys() {
      if (Collections.allocateIterators) {
         return new IntFloatMap.Keys(this);
      } else {
         if (this.keys1 == null) {
            this.keys1 = new IntFloatMap.Keys(this);
            this.keys2 = new IntFloatMap.Keys(this);
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

   public static class Entries extends IntFloatMap.MapIterator implements Iterable, Iterator {
      private final IntFloatMap.Entry entry = new IntFloatMap.Entry();

      public Entries(IntFloatMap map) {
         super(map);
      }

      public IntFloatMap.Entry next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            int[] keyTable = this.map.keyTable;
            if (this.nextIndex == -1) {
               this.entry.key = 0;
               this.entry.value = this.map.zeroValue;
            } else {
               this.entry.key = keyTable[this.nextIndex];
               this.entry.value = this.map.valueTable[this.nextIndex];
            }

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

      @Override
      public Iterator iterator() {
         return this;
      }

      @Override
      public void remove() {
         super.remove();
      }
   }

   public static class Entry {
      public int key;
      public float value;

      @Override
      public String toString() {
         return this.key + "=" + this.value;
      }
   }

   public static class Keys extends IntFloatMap.MapIterator {
      public Keys(IntFloatMap map) {
         super(map);
      }

      public int next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            int key = this.nextIndex == -1 ? 0 : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return key;
         }
      }

      public IntArray toArray() {
         IntArray array = new IntArray(true, this.map.size);

         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }

      public IntArray toArray(IntArray array) {
         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }

   private static class MapIterator {
      private static final int INDEX_ILLEGAL = -2;
      static final int INDEX_ZERO = -1;
      public boolean hasNext;
      final IntFloatMap map;
      int nextIndex;
      int currentIndex;
      boolean valid = true;

      public MapIterator(IntFloatMap map) {
         this.map = map;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -2;
         this.nextIndex = -1;
         if (this.map.hasZeroValue) {
            this.hasNext = true;
         } else {
            this.findNextIndex();
         }
      }

      void findNextIndex() {
         int[] keyTable = this.map.keyTable;
         int n = keyTable.length;

         while (++this.nextIndex < n) {
            if (keyTable[this.nextIndex] != 0) {
               this.hasNext = true;
               return;
            }
         }

         this.hasNext = false;
      }

      public void remove() {
         int i = this.currentIndex;
         if (i == -1 && this.map.hasZeroValue) {
            this.map.hasZeroValue = false;
         } else {
            if (i < 0) {
               throw new IllegalStateException("next must be called before remove.");
            }

            int[] keyTable = this.map.keyTable;
            float[] valueTable = this.map.valueTable;
            int mask = this.map.mask;

            int key;
            for (int next = i + 1 & mask; (key = keyTable[next]) != 0; next = next + 1 & mask) {
               int placement = this.map.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  valueTable[i] = valueTable[next];
                  i = next;
               }
            }

            keyTable[i] = 0;
            if (i != this.currentIndex) {
               this.nextIndex--;
            }
         }

         this.currentIndex = -2;
         this.map.size--;
      }
   }

   public static class Values extends IntFloatMap.MapIterator {
      public Values(IntFloatMap map) {
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
            float value = this.nextIndex == -1 ? this.map.zeroValue : this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return value;
         }
      }

      public IntFloatMap.Values iterator() {
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
