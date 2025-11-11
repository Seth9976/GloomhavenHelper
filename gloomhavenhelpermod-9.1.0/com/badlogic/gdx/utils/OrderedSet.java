package com.badlogic.gdx.utils;

import java.util.NoSuchElementException;

public class OrderedSet extends ObjectSet {
   final Array items;
   transient OrderedSet.OrderedSetIterator iterator1;
   transient OrderedSet.OrderedSetIterator iterator2;

   public OrderedSet() {
      this.items = new Array();
   }

   public OrderedSet(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
      this.items = new Array(initialCapacity);
   }

   public OrderedSet(int initialCapacity) {
      super(initialCapacity);
      this.items = new Array(initialCapacity);
   }

   public OrderedSet(OrderedSet set) {
      super(set);
      this.items = new Array(set.items);
   }

   @Override
   public boolean add(Object key) {
      if (!super.add(key)) {
         return false;
      } else {
         this.items.add(key);
         return true;
      }
   }

   public boolean add(Object key, int index) {
      if (!super.add(key)) {
         int oldIndex = this.items.indexOf(key, true);
         if (oldIndex != index) {
            this.items.insert(index, this.items.removeIndex(oldIndex));
         }

         return false;
      } else {
         this.items.insert(index, key);
         return true;
      }
   }

   public void addAll(OrderedSet set) {
      this.ensureCapacity(set.size);
      Object[] keys = (T[])set.items.items;
      int i = 0;

      for (int n = set.items.size; i < n; i++) {
         this.add(keys[i]);
      }
   }

   @Override
   public boolean remove(Object key) {
      if (!super.remove(key)) {
         return false;
      } else {
         this.items.removeValue(key, false);
         return true;
      }
   }

   public Object removeIndex(int index) {
      Object key = (T)this.items.removeIndex(index);
      super.remove(key);
      return key;
   }

   public boolean alter(Object before, Object after) {
      if (this.contains(after)) {
         return false;
      } else if (!super.remove(before)) {
         return false;
      } else {
         super.add(after);
         this.items.set(this.items.indexOf(before, false), after);
         return true;
      }
   }

   public boolean alterIndex(int index, Object after) {
      if (index >= 0 && index < this.size && !this.contains(after)) {
         super.remove(this.items.get(index));
         super.add(after);
         this.items.set(index, after);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void clear(int maximumCapacity) {
      this.items.clear();
      super.clear(maximumCapacity);
   }

   @Override
   public void clear() {
      this.items.clear();
      super.clear();
   }

   public Array orderedItems() {
      return this.items;
   }

   public OrderedSet.OrderedSetIterator iterator() {
      if (Collections.allocateIterators) {
         return new OrderedSet.OrderedSetIterator(this);
      } else {
         if (this.iterator1 == null) {
            this.iterator1 = new OrderedSet.OrderedSetIterator(this);
            this.iterator2 = new OrderedSet.OrderedSetIterator(this);
         }

         if (!this.iterator1.valid) {
            this.iterator1.reset();
            this.iterator1.valid = true;
            this.iterator2.valid = false;
            return this.iterator1;
         } else {
            this.iterator2.reset();
            this.iterator2.valid = true;
            this.iterator1.valid = false;
            return this.iterator2;
         }
      }
   }

   @Override
   public String toString() {
      if (this.size == 0) {
         return "{}";
      } else {
         Object[] items = (T[])this.items.items;
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         buffer.append('{');
         buffer.append(items[0]);

         for (int i = 1; i < this.size; i++) {
            buffer.append(", ");
            buffer.append(items[i]);
         }

         buffer.append('}');
         return buffer.toString();
      }
   }

   @Override
   public String toString(String separator) {
      return this.items.toString(separator);
   }

   public static OrderedSet with(Object... array) {
      OrderedSet set = new OrderedSet();
      set.addAll(array);
      return set;
   }

   public static class OrderedSetIterator extends ObjectSet.ObjectSetIterator {
      private Array items;

      public OrderedSetIterator(OrderedSet set) {
         super(set);
         this.items = set.items;
      }

      @Override
      public void reset() {
         this.nextIndex = 0;
         this.hasNext = this.set.size > 0;
      }

      @Override
      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object key = (K)this.items.get(this.nextIndex);
            this.nextIndex++;
            this.hasNext = this.nextIndex < this.set.size;
            return key;
         }
      }

      @Override
      public void remove() {
         if (this.nextIndex < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            this.nextIndex--;
            ((OrderedSet)this.set).removeIndex(this.nextIndex);
         }
      }

      @Override
      public Array toArray(Array array) {
         array.addAll(this.items, this.nextIndex, this.items.size - this.nextIndex);
         this.nextIndex = this.items.size;
         this.hasNext = false;
         return array;
      }

      @Override
      public Array toArray() {
         return this.toArray(new Array(true, this.set.size - this.nextIndex));
      }
   }
}
