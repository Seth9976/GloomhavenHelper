package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectSet implements Iterable {
   public int size;
   Object[] keyTable;
   float loadFactor;
   int threshold;
   protected int shift;
   protected int mask;
   private transient ObjectSet.ObjectSetIterator iterator1;
   private transient ObjectSet.ObjectSetIterator iterator2;

   public ObjectSet() {
      this(51, 0.8F);
   }

   public ObjectSet(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public ObjectSet(int initialCapacity, float loadFactor) {
      if (!(loadFactor <= 0.0F) && !(loadFactor >= 1.0F)) {
         this.loadFactor = loadFactor;
         int tableSize = tableSize(initialCapacity, loadFactor);
         this.threshold = (int)(tableSize * loadFactor);
         this.mask = tableSize - 1;
         this.shift = Long.numberOfLeadingZeros(this.mask);
         this.keyTable = new Object[tableSize];
      } else {
         throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + loadFactor);
      }
   }

   public ObjectSet(ObjectSet set) {
      this((int)(set.keyTable.length * set.loadFactor), set.loadFactor);
      System.arraycopy(set.keyTable, 0, this.keyTable, 0, set.keyTable.length);
      this.size = set.size;
   }

   protected int place(Object item) {
      return (int)(item.hashCode() * -7046029254386353131L >>> this.shift);
   }

   int locateKey(Object key) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         Object[] keyTable = (T[])this.keyTable;
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

   public boolean add(Object key) {
      int i = this.locateKey(key);
      if (i >= 0) {
         return false;
      } else {
         i = -(i + 1);
         this.keyTable[i] = key;
         if (++this.size >= this.threshold) {
            this.resize(this.keyTable.length << 1);
         }

         return true;
      }
   }

   public void addAll(Array array) {
      this.addAll(array.items, 0, array.size);
   }

   public void addAll(Array array, int offset, int length) {
      if (offset + length > array.size) {
         throw new IllegalArgumentException("offset + length must be <= size: " + offset + " + " + length + " <= " + array.size);
      } else {
         this.addAll(array.items, offset, length);
      }
   }

   public boolean addAll(Object... array) {
      return this.addAll(array, 0, array.length);
   }

   public boolean addAll(Object[] array, int offset, int length) {
      this.ensureCapacity(length);
      int oldSize = this.size;
      int i = offset;

      for (int n = offset + length; i < n; i++) {
         this.add(array[i]);
      }

      return oldSize != this.size;
   }

   public void addAll(ObjectSet set) {
      this.ensureCapacity(set.size);
      Object[] keyTable = (T[])set.keyTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         Object key = keyTable[i];
         if (key != null) {
            this.add(key);
         }
      }
   }

   private void addResize(Object key) {
      Object[] keyTable = (T[])this.keyTable;
      int i = this.place(key);

      while (keyTable[i] != null) {
         i = i + 1 & this.mask;
      }

      keyTable[i] = key;
   }

   public boolean remove(Object key) {
      int i = this.locateKey(key);
      if (i < 0) {
         return false;
      } else {
         Object[] keyTable = (T[])this.keyTable;
         int mask = this.mask;

         for (int next = i + 1 & mask; (key = keyTable[next]) != null; next = next + 1 & mask) {
            int placement = this.place(key);
            if ((next - placement & mask) > (i - placement & mask)) {
               keyTable[i] = key;
               i = next;
            }
         }

         keyTable[i] = null;
         this.size--;
         return true;
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
         int tableSize = tableSize(maximumCapacity, this.loadFactor);
         if (this.keyTable.length > tableSize) {
            this.resize(tableSize);
         }
      }
   }

   public void clear(int maximumCapacity) {
      int tableSize = tableSize(maximumCapacity, this.loadFactor);
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

   public boolean contains(Object key) {
      return this.locateKey(key) >= 0;
   }

   @Null
   public Object get(Object key) {
      int i = this.locateKey(key);
      return i < 0 ? null : this.keyTable[i];
   }

   public Object first() {
      Object[] keyTable = (T[])this.keyTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         if (keyTable[i] != null) {
            return keyTable[i];
         }
      }

      throw new IllegalStateException("ObjectSet is empty.");
   }

   public void ensureCapacity(int additionalCapacity) {
      int tableSize = tableSize(this.size + additionalCapacity, this.loadFactor);
      if (this.keyTable.length < tableSize) {
         this.resize(tableSize);
      }
   }

   private void resize(int newSize) {
      int oldCapacity = this.keyTable.length;
      this.threshold = (int)(newSize * this.loadFactor);
      this.mask = newSize - 1;
      this.shift = Long.numberOfLeadingZeros(this.mask);
      Object[] oldKeyTable = (T[])this.keyTable;
      this.keyTable = new Object[newSize];
      if (this.size > 0) {
         for (int i = 0; i < oldCapacity; i++) {
            Object key = oldKeyTable[i];
            if (key != null) {
               this.addResize(key);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int h = this.size;
      Object[] keyTable = (T[])this.keyTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         Object key = keyTable[i];
         if (key != null) {
            h += key.hashCode();
         }
      }

      return h;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof ObjectSet)) {
         return false;
      } else {
         ObjectSet other = (ObjectSet)obj;
         if (other.size != this.size) {
            return false;
         } else {
            Object[] keyTable = (T[])this.keyTable;
            int i = 0;

            for (int n = keyTable.length; i < n; i++) {
               if (keyTable[i] != null && !other.contains(keyTable[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return '{' + this.toString(", ") + '}';
   }

   public String toString(String separator) {
      if (this.size == 0) {
         return "";
      } else {
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         Object[] keyTable = (T[])this.keyTable;
         int i = keyTable.length;

         while (i-- > 0) {
            Object key = keyTable[i];
            if (key != null) {
               buffer.append(key == this ? "(this)" : key);
               break;
            }
         }

         while (i-- > 0) {
            Object key = keyTable[i];
            if (key != null) {
               buffer.append(separator);
               buffer.append(key == this ? "(this)" : key);
            }
         }

         return buffer.toString();
      }
   }

   public ObjectSet.ObjectSetIterator iterator() {
      if (Collections.allocateIterators) {
         return new ObjectSet.ObjectSetIterator(this);
      } else {
         if (this.iterator1 == null) {
            this.iterator1 = new ObjectSet.ObjectSetIterator(this);
            this.iterator2 = new ObjectSet.ObjectSetIterator(this);
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

   public static ObjectSet with(Object... array) {
      ObjectSet set = new ObjectSet();
      set.addAll(array);
      return set;
   }

   static int tableSize(int capacity, float loadFactor) {
      if (capacity < 0) {
         throw new IllegalArgumentException("capacity must be >= 0: " + capacity);
      } else {
         int tableSize = MathUtils.nextPowerOfTwo(Math.max(2, (int)Math.ceil(capacity / loadFactor)));
         if (tableSize > 1073741824) {
            throw new IllegalArgumentException("The required capacity is too large: " + capacity);
         } else {
            return tableSize;
         }
      }
   }

   public static class ObjectSetIterator implements Iterable, Iterator {
      public boolean hasNext;
      final ObjectSet set;
      int nextIndex;
      int currentIndex;
      boolean valid = true;

      public ObjectSetIterator(ObjectSet set) {
         this.set = set;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = -1;
         this.findNextIndex();
      }

      private void findNextIndex() {
         Object[] keyTable = (K[])this.set.keyTable;
         int n = this.set.keyTable.length;

         while (++this.nextIndex < n) {
            if (keyTable[this.nextIndex] != null) {
               this.hasNext = true;
               return;
            }
         }

         this.hasNext = false;
      }

      @Override
      public void remove() {
         int i = this.currentIndex;
         if (i < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            Object[] keyTable = (K[])this.set.keyTable;
            int mask = this.set.mask;

            Object key;
            for (int next = i + 1 & mask; (key = keyTable[next]) != null; next = next + 1 & mask) {
               int placement = this.set.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  i = next;
               }
            }

            keyTable[i] = null;
            this.set.size--;
            if (i != this.currentIndex) {
               this.nextIndex--;
            }

            this.currentIndex = -1;
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
      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            Object key = (K)this.set.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return key;
         }
      }

      public ObjectSet.ObjectSetIterator iterator() {
         return this;
      }

      public Array toArray(Array array) {
         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }

      public Array toArray() {
         return this.toArray(new Array(true, this.set.size));
      }
   }
}
