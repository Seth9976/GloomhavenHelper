package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntSet {
   public int size;
   int[] keyTable;
   boolean hasZeroValue;
   private final float loadFactor;
   private int threshold;
   protected int shift;
   protected int mask;
   private transient IntSet.IntSetIterator iterator1;
   private transient IntSet.IntSetIterator iterator2;

   public IntSet() {
      this(51, 0.8F);
   }

   public IntSet(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public IntSet(int initialCapacity, float loadFactor) {
      if (!(loadFactor <= 0.0F) && !(loadFactor >= 1.0F)) {
         this.loadFactor = loadFactor;
         int tableSize = ObjectSet.tableSize(initialCapacity, loadFactor);
         this.threshold = (int)(tableSize * loadFactor);
         this.mask = tableSize - 1;
         this.shift = Long.numberOfLeadingZeros(this.mask);
         this.keyTable = new int[tableSize];
      } else {
         throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + loadFactor);
      }
   }

   public IntSet(IntSet set) {
      this((int)(set.keyTable.length * set.loadFactor), set.loadFactor);
      System.arraycopy(set.keyTable, 0, this.keyTable, 0, set.keyTable.length);
      this.size = set.size;
      this.hasZeroValue = set.hasZeroValue;
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

   public boolean add(int key) {
      if (key == 0) {
         if (this.hasZeroValue) {
            return false;
         } else {
            this.hasZeroValue = true;
            this.size++;
            return true;
         }
      } else {
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
   }

   public void addAll(IntArray array) {
      this.addAll(array.items, 0, array.size);
   }

   public void addAll(IntArray array, int offset, int length) {
      if (offset + length > array.size) {
         throw new IllegalArgumentException("offset + length must be <= size: " + offset + " + " + length + " <= " + array.size);
      } else {
         this.addAll(array.items, offset, length);
      }
   }

   public void addAll(int... array) {
      this.addAll(array, 0, array.length);
   }

   public void addAll(int[] array, int offset, int length) {
      this.ensureCapacity(length);
      int i = offset;

      for (int n = offset + length; i < n; i++) {
         this.add(array[i]);
      }
   }

   public void addAll(IntSet set) {
      this.ensureCapacity(set.size);
      if (set.hasZeroValue) {
         this.add(0);
      }

      int[] keyTable = set.keyTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         int key = keyTable[i];
         if (key != 0) {
            this.add(key);
         }
      }
   }

   private void addResize(int key) {
      int[] keyTable = this.keyTable;
      int i = this.place(key);

      while (keyTable[i] != 0) {
         i = i + 1 & this.mask;
      }

      keyTable[i] = key;
   }

   public boolean remove(int key) {
      if (key == 0) {
         if (!this.hasZeroValue) {
            return false;
         } else {
            this.hasZeroValue = false;
            this.size--;
            return true;
         }
      } else {
         int i = this.locateKey(key);
         if (i < 0) {
            return false;
         } else {
            int[] keyTable = this.keyTable;
            int mask = this.mask;

            for (int next = i + 1 & mask; (key = keyTable[next]) != 0; next = next + 1 & mask) {
               int placement = this.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  i = next;
               }
            }

            keyTable[i] = 0;
            this.size--;
            return true;
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
         this.size = 0;
         Arrays.fill(this.keyTable, 0);
         this.hasZeroValue = false;
      }
   }

   public boolean contains(int key) {
      return key == 0 ? this.hasZeroValue : this.locateKey(key) >= 0;
   }

   public int first() {
      if (this.hasZeroValue) {
         return 0;
      } else {
         int[] keyTable = this.keyTable;
         int i = 0;

         for (int n = keyTable.length; i < n; i++) {
            if (keyTable[i] != 0) {
               return keyTable[i];
            }
         }

         throw new IllegalStateException("IntSet is empty.");
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
      this.keyTable = new int[newSize];
      if (this.size > 0) {
         for (int i = 0; i < oldCapacity; i++) {
            int key = oldKeyTable[i];
            if (key != 0) {
               this.addResize(key);
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int h = this.size;
      int[] keyTable = this.keyTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         int key = keyTable[i];
         if (key != 0) {
            h += key;
         }
      }

      return h;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof IntSet)) {
         return false;
      } else {
         IntSet other = (IntSet)obj;
         if (other.size != this.size) {
            return false;
         } else if (other.hasZeroValue != this.hasZeroValue) {
            return false;
         } else {
            int[] keyTable = this.keyTable;
            int i = 0;

            for (int n = keyTable.length; i < n; i++) {
               if (keyTable[i] != 0 && !other.contains(keyTable[i])) {
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
         return "[]";
      } else {
         java.lang.StringBuilder buffer = new java.lang.StringBuilder(32);
         buffer.append('[');
         int[] keyTable = this.keyTable;
         int i = keyTable.length;
         if (this.hasZeroValue) {
            buffer.append("0");
         } else {
            while (i-- > 0) {
               int key = keyTable[i];
               if (key != 0) {
                  buffer.append(key);
                  break;
               }
            }
         }

         while (i-- > 0) {
            int key = keyTable[i];
            if (key != 0) {
               buffer.append(", ");
               buffer.append(key);
            }
         }

         buffer.append(']');
         return buffer.toString();
      }
   }

   public IntSet.IntSetIterator iterator() {
      if (Collections.allocateIterators) {
         return new IntSet.IntSetIterator(this);
      } else {
         if (this.iterator1 == null) {
            this.iterator1 = new IntSet.IntSetIterator(this);
            this.iterator2 = new IntSet.IntSetIterator(this);
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

   public static IntSet with(int... array) {
      IntSet set = new IntSet();
      set.addAll(array);
      return set;
   }

   public static class IntSetIterator {
      private static final int INDEX_ILLEGAL = -2;
      private static final int INDEX_ZERO = -1;
      public boolean hasNext;
      final IntSet set;
      int nextIndex;
      int currentIndex;
      boolean valid = true;

      public IntSetIterator(IntSet set) {
         this.set = set;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -2;
         this.nextIndex = -1;
         if (this.set.hasZeroValue) {
            this.hasNext = true;
         } else {
            this.findNextIndex();
         }
      }

      void findNextIndex() {
         int[] keyTable = this.set.keyTable;
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
         if (i == -1 && this.set.hasZeroValue) {
            this.set.hasZeroValue = false;
         } else {
            if (i < 0) {
               throw new IllegalStateException("next must be called before remove.");
            }

            int[] keyTable = this.set.keyTable;
            int mask = this.set.mask;

            int key;
            for (int next = i + 1 & mask; (key = keyTable[next]) != 0; next = next + 1 & mask) {
               int placement = this.set.place(key);
               if ((next - placement & mask) > (i - placement & mask)) {
                  keyTable[i] = key;
                  i = next;
               }
            }

            keyTable[i] = 0;
            if (i != this.currentIndex) {
               this.nextIndex--;
            }
         }

         this.currentIndex = -2;
         this.set.size--;
      }

      public int next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else if (!this.valid) {
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
         } else {
            int key = this.nextIndex == -1 ? 0 : this.set.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return key;
         }
      }

      public IntArray toArray() {
         IntArray array = new IntArray(true, this.set.size);

         while (this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }
}
