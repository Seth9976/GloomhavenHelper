package com.badlogic.gdx.utils;

import java.util.NoSuchElementException;

public class OrderedMap extends ObjectMap {
   final Array keys;

   public OrderedMap() {
      this.keys = new Array();
   }

   public OrderedMap(int initialCapacity) {
      super(initialCapacity);
      this.keys = new Array(initialCapacity);
   }

   public OrderedMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
      this.keys = new Array(initialCapacity);
   }

   public OrderedMap(OrderedMap map) {
      super(map);
      this.keys = new Array(map.keys);
   }

   @Override
   public Object put(Object key, Object value) {
      int i = this.locateKey(key);
      if (i >= 0) {
         Object oldValue = (V)this.valueTable[i];
         this.valueTable[i] = value;
         return oldValue;
      } else {
         i = -(i + 1);
         this.keyTable[i] = key;
         this.valueTable[i] = value;
         this.keys.add(key);
         if (++this.size >= this.threshold) {
            this.resize(this.keyTable.length << 1);
         }

         return null;
      }
   }

   public void putAll(OrderedMap map) {
      this.ensureCapacity(map.size);
      Object[] keys = (K[])map.keys.items;
      int i = 0;

      for (int n = map.keys.size; i < n; i++) {
         Object key = keys[i];
         this.put(key, map.get(key));
      }
   }

   @Override
   public Object remove(Object key) {
      this.keys.removeValue(key, false);
      return super.remove(key);
   }

   public Object removeIndex(int index) {
      return super.remove(this.keys.removeIndex(index));
   }

   public boolean alter(Object before, Object after) {
      if (this.containsKey(after)) {
         return false;
      } else {
         int index = this.keys.indexOf(before, false);
         if (index == -1) {
            return false;
         } else {
            super.put(after, super.remove(before));
            this.keys.set(index, after);
            return true;
         }
      }
   }

   public boolean alterIndex(int index, Object after) {
      if (index >= 0 && index < this.size && !this.containsKey(after)) {
         super.put(after, super.remove(this.keys.get(index)));
         this.keys.set(index, after);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void clear(int maximumCapacity) {
      this.keys.clear();
      super.clear(maximumCapacity);
   }

   @Override
   public void clear() {
      this.keys.clear();
      super.clear();
   }

   public Array orderedKeys() {
      return this.keys;
   }

   @Override
   public ObjectMap.Entries iterator() {
      return this.entries();
   }

   @Override
   public ObjectMap.Entries entries() {
      if (Collections.allocateIterators) {
         return new OrderedMap.OrderedMapEntries(this);
      } else {
         if (this.entries1 == null) {
            this.entries1 = new OrderedMap.OrderedMapEntries(this);
            this.entries2 = new OrderedMap.OrderedMapEntries(this);
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

   @Override
   public ObjectMap.Values values() {
      if (Collections.allocateIterators) {
         return new OrderedMap.OrderedMapValues(this);
      } else {
         if (this.values1 == null) {
            this.values1 = new OrderedMap.OrderedMapValues(this);
            this.values2 = new OrderedMap.OrderedMapValues(this);
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

   @Override
   public ObjectMap.Keys keys() {
      if (Collections.allocateIterators) {
         return new OrderedMap.OrderedMapKeys(this);
      } else {
         if (this.keys1 == null) {
            this.keys1 = new OrderedMap.OrderedMapKeys(this);
            this.keys2 = new OrderedMap.OrderedMapKeys(this);
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

   @Override
   protected String toString(String separator, boolean braces) {
      if (this.size == 0) {
         return braces ? "{}" : "";
      } else {
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         if (braces) {
            buffer.append('{');
         }

         Array keys = this.keys;
         int i = 0;

         for (int n = keys.size; i < n; i++) {
            Object key = (K)keys.get(i);
            if (i > 0) {
               buffer.append(separator);
            }

            buffer.append(key == this ? "(this)" : key);
            buffer.append('=');
            Object value = (V)this.get(key);
            buffer.append(value == this ? "(this)" : value);
         }

         if (braces) {
            buffer.append('}');
         }

         return buffer.toString();
      }
   }

   public static class OrderedMapEntries extends ObjectMap.Entries {
      private Array keys;

      public OrderedMapEntries(OrderedMap map) {
         super(map);
         this.keys = map.keys;
      }

      @Override
      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = 0;
         this.hasNext = this.map.size > 0;
      }

      @Override
      public ObjectMap.Entry next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            this.currentIndex = this.nextIndex;
            this.entry.key = this.keys.get(this.nextIndex);
            this.entry.value = this.map.get(this.entry.key);
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return this.entry;
         }
      }

      @Override
      public void remove() {
         if (this.currentIndex < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            this.map.remove(this.entry.key);
            this.nextIndex--;
            this.currentIndex = -1;
         }
      }
   }

   public static class OrderedMapKeys extends ObjectMap.Keys {
      private Array keys;

      public OrderedMapKeys(OrderedMap map) {
         super(map);
         this.keys = map.keys;
      }

      @Override
      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = 0;
         this.hasNext = this.map.size > 0;
      }

      @Override
      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object key = (K)this.keys.get(this.nextIndex);
            this.currentIndex = this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return key;
         }
      }

      @Override
      public void remove() {
         if (this.currentIndex < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            ((OrderedMap)this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
         }
      }

      @Override
      public Array toArray(Array array) {
         array.addAll(this.keys, this.nextIndex, this.keys.size - this.nextIndex);
         this.nextIndex = this.keys.size;
         this.hasNext = false;
         return array;
      }

      @Override
      public Array toArray() {
         return this.toArray(new Array(true, this.keys.size - this.nextIndex));
      }
   }

   public static class OrderedMapValues extends ObjectMap.Values {
      private Array keys;

      public OrderedMapValues(OrderedMap map) {
         super(map);
         this.keys = map.keys;
      }

      @Override
      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = 0;
         this.hasNext = this.map.size > 0;
      }

      @Override
      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object value = (V)this.map.get(this.keys.get(this.nextIndex));
            this.currentIndex = this.nextIndex++;
            this.hasNext = this.nextIndex < this.map.size;
            return value;
         }
      }

      @Override
      public void remove() {
         if (this.currentIndex < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            ((OrderedMap)this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
         }
      }

      @Override
      public Array toArray(Array array) {
         int n = this.keys.size;
         array.ensureCapacity(n - this.nextIndex);
         Object[] keys = this.keys.items;

         for (int i = this.nextIndex; i < n; i++) {
            array.add(this.map.get(keys[i]));
         }

         this.currentIndex = n - 1;
         this.nextIndex = n;
         this.hasNext = false;
         return array;
      }

      @Override
      public Array toArray() {
         return this.toArray(new Array(true, this.keys.size - this.nextIndex));
      }
   }
}
