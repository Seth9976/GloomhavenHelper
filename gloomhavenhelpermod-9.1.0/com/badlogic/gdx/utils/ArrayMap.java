package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayMap implements Iterable {
   public Object[] keys;
   public Object[] values;
   public int size;
   public boolean ordered;
   private transient ArrayMap.Entries entries1;
   private transient ArrayMap.Entries entries2;
   private transient ArrayMap.Values values1;
   private transient ArrayMap.Values values2;
   private transient ArrayMap.Keys keys1;
   private transient ArrayMap.Keys keys2;

   public ArrayMap() {
      this(true, 16);
   }

   public ArrayMap(int capacity) {
      this(true, capacity);
   }

   public ArrayMap(boolean ordered, int capacity) {
      this.ordered = ordered;
      this.keys = new Object[capacity];
      this.values = new Object[capacity];
   }

   public ArrayMap(boolean ordered, int capacity, Class keyArrayType, Class valueArrayType) {
      this.ordered = ordered;
      this.keys = (Object[])ArrayReflection.newInstance(keyArrayType, capacity);
      this.values = (Object[])ArrayReflection.newInstance(valueArrayType, capacity);
   }

   public ArrayMap(Class keyArrayType, Class valueArrayType) {
      this(false, 16, keyArrayType, valueArrayType);
   }

   public ArrayMap(ArrayMap array) {
      this(array.ordered, array.size, array.keys.getClass().getComponentType(), array.values.getClass().getComponentType());
      this.size = array.size;
      System.arraycopy(array.keys, 0, this.keys, 0, this.size);
      System.arraycopy(array.values, 0, this.values, 0, this.size);
   }

   public int put(Object key, Object value) {
      int index = this.indexOfKey(key);
      if (index == -1) {
         if (this.size == this.keys.length) {
            this.resize(Math.max(8, (int)(this.size * 1.75F)));
         }

         index = this.size++;
      }

      this.keys[index] = key;
      this.values[index] = value;
      return index;
   }

   public int put(Object key, Object value, int index) {
      int existingIndex = this.indexOfKey(key);
      if (existingIndex != -1) {
         this.removeIndex(existingIndex);
      } else if (this.size == this.keys.length) {
         this.resize(Math.max(8, (int)(this.size * 1.75F)));
      }

      System.arraycopy(this.keys, index, this.keys, index + 1, this.size - index);
      System.arraycopy(this.values, index, this.values, index + 1, this.size - index);
      this.keys[index] = key;
      this.values[index] = value;
      this.size++;
      return index;
   }

   public void putAll(ArrayMap map) {
      this.putAll(map, 0, map.size);
   }

   public void putAll(ArrayMap map, int offset, int length) {
      if (offset + length > map.size) {
         throw new IllegalArgumentException("offset + length must be <= size: " + offset + " + " + length + " <= " + map.size);
      } else {
         int sizeNeeded = this.size + length - offset;
         if (sizeNeeded >= this.keys.length) {
            this.resize(Math.max(8, (int)(sizeNeeded * 1.75F)));
         }

         System.arraycopy(map.keys, offset, this.keys, this.size, length);
         System.arraycopy(map.values, offset, this.values, this.size, length);
         this.size += length;
      }
   }

   @Null
   public Object get(Object key) {
      return this.get(key, null);
   }

   @Null
   public Object get(Object key, @Null Object defaultValue) {
      Object[] keys = this.keys;
      int i = this.size - 1;
      if (key == null) {
         while (i >= 0) {
            if (keys[i] == key) {
               return this.values[i];
            }

            i--;
         }
      } else {
         while (i >= 0) {
            if (key.equals(keys[i])) {
               return this.values[i];
            }

            i--;
         }
      }

      return defaultValue;
   }

   @Null
   public Object getKey(Object value, boolean identity) {
      Object[] values = this.values;
      int i = this.size - 1;
      if (!identity && value != null) {
         while (i >= 0) {
            if (value.equals(values[i])) {
               return this.keys[i];
            }

            i--;
         }
      } else {
         while (i >= 0) {
            if (values[i] == value) {
               return this.keys[i];
            }

            i--;
         }
      }

      return null;
   }

   public Object getKeyAt(int index) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         return this.keys[index];
      }
   }

   public Object getValueAt(int index) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         return this.values[index];
      }
   }

   public Object firstKey() {
      if (this.size == 0) {
         throw new IllegalStateException("Map is empty.");
      } else {
         return this.keys[0];
      }
   }

   public Object firstValue() {
      if (this.size == 0) {
         throw new IllegalStateException("Map is empty.");
      } else {
         return this.values[0];
      }
   }

   public void setKey(int index, Object key) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         this.keys[index] = key;
      }
   }

   public void setValue(int index, Object value) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         this.values[index] = value;
      }
   }

   public void insert(int index, Object key, Object value) {
      if (index > this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         if (this.size == this.keys.length) {
            this.resize(Math.max(8, (int)(this.size * 1.75F)));
         }

         if (this.ordered) {
            System.arraycopy(this.keys, index, this.keys, index + 1, this.size - index);
            System.arraycopy(this.values, index, this.values, index + 1, this.size - index);
         } else {
            this.keys[this.size] = this.keys[index];
            this.values[this.size] = this.values[index];
         }

         this.size++;
         this.keys[index] = key;
         this.values[index] = value;
      }
   }

   public boolean containsKey(Object key) {
      Object[] keys = (K[])this.keys;
      int i = this.size - 1;
      if (key == null) {
         while (i >= 0) {
            if (keys[i--] == key) {
               return true;
            }
         }
      } else {
         while (i >= 0) {
            if (key.equals(keys[i--])) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean containsValue(Object value, boolean identity) {
      Object[] values = (V[])this.values;
      int i = this.size - 1;
      if (!identity && value != null) {
         while (i >= 0) {
            if (value.equals(values[i--])) {
               return true;
            }
         }
      } else {
         while (i >= 0) {
            if (values[i--] == value) {
               return true;
            }
         }
      }

      return false;
   }

   public int indexOfKey(Object key) {
      Object[] keys = this.keys;
      if (key == null) {
         int i = 0;

         for (int n = this.size; i < n; i++) {
            if (keys[i] == key) {
               return i;
            }
         }
      } else {
         int i = 0;

         for (int nx = this.size; i < nx; i++) {
            if (key.equals(keys[i])) {
               return i;
            }
         }
      }

      return -1;
   }

   public int indexOfValue(Object value, boolean identity) {
      Object[] values = this.values;
      if (!identity && value != null) {
         int i = 0;

         for (int n = this.size; i < n; i++) {
            if (value.equals(values[i])) {
               return i;
            }
         }
      } else {
         int i = 0;

         for (int nx = this.size; i < nx; i++) {
            if (values[i] == value) {
               return i;
            }
         }
      }

      return -1;
   }

   @Null
   public Object removeKey(Object key) {
      Object[] keys = this.keys;
      if (key == null) {
         int i = 0;

         for (int n = this.size; i < n; i++) {
            if (keys[i] == key) {
               Object value = (V)this.values[i];
               this.removeIndex(i);
               return value;
            }
         }
      } else {
         int i = 0;

         for (int nx = this.size; i < nx; i++) {
            if (key.equals(keys[i])) {
               Object value = (V)this.values[i];
               this.removeIndex(i);
               return value;
            }
         }
      }

      return null;
   }

   public boolean removeValue(Object value, boolean identity) {
      Object[] values = this.values;
      if (!identity && value != null) {
         int i = 0;

         for (int n = this.size; i < n; i++) {
            if (value.equals(values[i])) {
               this.removeIndex(i);
               return true;
            }
         }
      } else {
         int i = 0;

         for (int nx = this.size; i < nx; i++) {
            if (values[i] == value) {
               this.removeIndex(i);
               return true;
            }
         }
      }

      return false;
   }

   public void removeIndex(int index) {
      if (index >= this.size) {
         throw new IndexOutOfBoundsException(String.valueOf(index));
      } else {
         Object[] keys = this.keys;
         this.size--;
         if (this.ordered) {
            System.arraycopy(keys, index + 1, keys, index, this.size - index);
            System.arraycopy(this.values, index + 1, this.values, index, this.size - index);
         } else {
            keys[index] = keys[this.size];
            this.values[index] = this.values[this.size];
         }

         keys[this.size] = null;
         this.values[this.size] = null;
      }
   }

   public boolean notEmpty() {
      return this.size > 0;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public Object peekKey() {
      return this.keys[this.size - 1];
   }

   public Object peekValue() {
      return this.values[this.size - 1];
   }

   public void clear(int maximumCapacity) {
      if (this.keys.length <= maximumCapacity) {
         this.clear();
      } else {
         this.size = 0;
         this.resize(maximumCapacity);
      }
   }

   public void clear() {
      Arrays.fill(this.keys, 0, this.size, null);
      Arrays.fill(this.values, 0, this.size, null);
      this.size = 0;
   }

   public void shrink() {
      if (this.keys.length != this.size) {
         this.resize(this.size);
      }
   }

   public void ensureCapacity(int additionalCapacity) {
      if (additionalCapacity < 0) {
         throw new IllegalArgumentException("additionalCapacity must be >= 0: " + additionalCapacity);
      } else {
         int sizeNeeded = this.size + additionalCapacity;
         if (sizeNeeded > this.keys.length) {
            this.resize(Math.max(Math.max(8, sizeNeeded), (int)(this.size * 1.75F)));
         }
      }
   }

   protected void resize(int newSize) {
      Object[] newKeys = (K[])((Object[])ArrayReflection.newInstance(this.keys.getClass().getComponentType(), newSize));
      System.arraycopy(this.keys, 0, newKeys, 0, Math.min(this.size, newKeys.length));
      this.keys = newKeys;
      Object[] newValues = (V[])((Object[])ArrayReflection.newInstance(this.values.getClass().getComponentType(), newSize));
      System.arraycopy(this.values, 0, newValues, 0, Math.min(this.size, newValues.length));
      this.values = newValues;
   }

   public void reverse() {
      int i = 0;
      int lastIndex = this.size - 1;

      for (int n = this.size / 2; i < n; i++) {
         int ii = lastIndex - i;
         Object tempKey = (K)this.keys[i];
         this.keys[i] = this.keys[ii];
         this.keys[ii] = tempKey;
         Object tempValue = (V)this.values[i];
         this.values[i] = this.values[ii];
         this.values[ii] = tempValue;
      }
   }

   public void shuffle() {
      for (int i = this.size - 1; i >= 0; i--) {
         int ii = MathUtils.random(i);
         Object tempKey = (K)this.keys[i];
         this.keys[i] = this.keys[ii];
         this.keys[ii] = tempKey;
         Object tempValue = (V)this.values[i];
         this.values[i] = this.values[ii];
         this.values[ii] = tempValue;
      }
   }

   public void truncate(int newSize) {
      if (this.size > newSize) {
         for (int i = newSize; i < this.size; i++) {
            this.keys[i] = null;
            this.values[i] = null;
         }

         this.size = newSize;
      }
   }

   @Override
   public int hashCode() {
      Object[] keys = (K[])this.keys;
      Object[] values = (V[])this.values;
      int h = 0;
      int i = 0;

      for (int n = this.size; i < n; i++) {
         Object key = keys[i];
         Object value = values[i];
         if (key != null) {
            h += key.hashCode() * 31;
         }

         if (value != null) {
            h += value.hashCode();
         }
      }

      return h;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ArrayMap)) {
         return false;
      } else {
         ArrayMap other = (ArrayMap)obj;
         if (other.size != this.size) {
            return false;
         } else {
            Object[] keys = (K[])this.keys;
            Object[] values = (V[])this.values;
            int i = 0;

            for (int n = this.size; i < n; i++) {
               Object key = keys[i];
               Object value = values[i];
               if (value == null) {
                  if (other.get(key, ObjectMap.dummy) != null) {
                     return false;
                  }
               } else if (!value.equals(other.get(key))) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean equalsIdentity(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ArrayMap)) {
         return false;
      } else {
         ArrayMap other = (ArrayMap)obj;
         if (other.size != this.size) {
            return false;
         } else {
            Object[] keys = (K[])this.keys;
            Object[] values = (V[])this.values;
            int i = 0;

            for (int n = this.size; i < n; i++) {
               if (values[i] != other.get(keys[i], ObjectMap.dummy)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      if (this.size == 0) {
         return "{}";
      } else {
         Object[] keys = (K[])this.keys;
         Object[] values = (V[])this.values;
         StringBuilder buffer = new StringBuilder(32);
         buffer.append('{');
         buffer.append(keys[0]);
         buffer.append('=');
         buffer.append(values[0]);

         for (int i = 1; i < this.size; i++) {
            buffer.append(", ");
            buffer.append(keys[i]);
            buffer.append('=');
            buffer.append(values[i]);
         }

         buffer.append('}');
         return buffer.toString();
      }
   }

   @Override
   public Iterator iterator() {
      return this.entries();
   }

   public ArrayMap.Entries entries() {
      if (Collections.allocateIterators) {
         return new ArrayMap.Entries(this);
      } else {
         if (this.entries1 == null) {
            this.entries1 = new ArrayMap.Entries(this);
            this.entries2 = new ArrayMap.Entries(this);
         }

         if (!this.entries1.valid) {
            this.entries1.index = 0;
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
         } else {
            this.entries2.index = 0;
            this.entries2.valid = true;
            this.entries1.valid = false;
            return this.entries2;
         }
      }
   }

   public ArrayMap.Values values() {
      if (Collections.allocateIterators) {
         return new ArrayMap.Values(this);
      } else {
         if (this.values1 == null) {
            this.values1 = new ArrayMap.Values(this);
            this.values2 = new ArrayMap.Values(this);
         }

         if (!this.values1.valid) {
            this.values1.index = 0;
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
         } else {
            this.values2.index = 0;
            this.values2.valid = true;
            this.values1.valid = false;
            return this.values2;
         }
      }
   }

   public ArrayMap.Keys keys() {
      if (Collections.allocateIterators) {
         return new ArrayMap.Keys(this);
      } else {
         if (this.keys1 == null) {
            this.keys1 = new ArrayMap.Keys(this);
            this.keys2 = new ArrayMap.Keys(this);
         }

         if (!this.keys1.valid) {
            this.keys1.index = 0;
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
         } else {
            this.keys2.index = 0;
            this.keys2.valid = true;
            this.keys1.valid = false;
            return this.keys2;
         }
      }
   }

   public static class Entries implements Iterable, Iterator {
      private final ArrayMap map;
      ObjectMap.Entry entry = new ObjectMap.Entry();
      int index;
      boolean valid = true;

      public Entries(ArrayMap map) {
         this.map = map;
      }

      @Override
      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.index < this.map.size;
         }
      }

      @Override
      public Iterator iterator() {
         return this;
      }

      public ObjectMap.Entry next() {
         if (this.index >= this.map.size) {
            throw new NoSuchElementException(String.valueOf(this.index));
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            this.entry.key = this.map.keys[this.index];
            this.entry.value = this.map.values[this.index++];
            return this.entry;
         }
      }

      @Override
      public void remove() {
         this.index--;
         this.map.removeIndex(this.index);
      }

      public void reset() {
         this.index = 0;
      }
   }

   public static class Keys implements Iterable, Iterator {
      private final ArrayMap map;
      int index;
      boolean valid = true;

      public Keys(ArrayMap map) {
         this.map = map;
      }

      @Override
      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.index < this.map.size;
         }
      }

      @Override
      public Iterator iterator() {
         return this;
      }

      @Override
      public Object next() {
         if (this.index >= this.map.size) {
            throw new NoSuchElementException(String.valueOf(this.index));
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.map.keys[this.index++];
         }
      }

      @Override
      public void remove() {
         this.index--;
         this.map.removeIndex(this.index);
      }

      public void reset() {
         this.index = 0;
      }

      public Array toArray() {
         return new Array(true, this.map.keys, this.index, this.map.size - this.index);
      }

      public Array toArray(Array array) {
         array.addAll(this.map.keys, this.index, this.map.size - this.index);
         return array;
      }
   }

   public static class Values implements Iterable, Iterator {
      private final ArrayMap map;
      int index;
      boolean valid = true;

      public Values(ArrayMap map) {
         this.map = map;
      }

      @Override
      public boolean hasNext() {
         if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.index < this.map.size;
         }
      }

      @Override
      public Iterator iterator() {
         return this;
      }

      @Override
      public Object next() {
         if (this.index >= this.map.size) {
            throw new NoSuchElementException(String.valueOf(this.index));
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            return this.map.values[this.index++];
         }
      }

      @Override
      public void remove() {
         this.index--;
         this.map.removeIndex(this.index);
      }

      public void reset() {
         this.index = 0;
      }

      public Array toArray() {
         return new Array(true, this.map.values, this.index, this.map.size - this.index);
      }

      public Array toArray(Array array) {
         array.addAll(this.map.values, this.index, this.map.size - this.index);
         return array;
      }
   }
}
