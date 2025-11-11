package com.badlogic.gdx.utils;

import java.util.Comparator;

public class DelayedRemovalArray extends Array {
   private int iterating;
   private IntArray remove = new IntArray(0);
   private int clear;

   public DelayedRemovalArray() {
   }

   public DelayedRemovalArray(Array array) {
      super(array);
   }

   public DelayedRemovalArray(boolean ordered, int capacity, Class arrayType) {
      super(ordered, capacity, arrayType);
   }

   public DelayedRemovalArray(boolean ordered, int capacity) {
      super(ordered, capacity);
   }

   public DelayedRemovalArray(boolean ordered, Object[] array, int startIndex, int count) {
      super(ordered, array, startIndex, count);
   }

   public DelayedRemovalArray(Class arrayType) {
      super(arrayType);
   }

   public DelayedRemovalArray(int capacity) {
      super(capacity);
   }

   public DelayedRemovalArray(Object[] array) {
      super(array);
   }

   public void begin() {
      this.iterating++;
   }

   public void end() {
      if (this.iterating == 0) {
         throw new IllegalStateException("begin must be called before end.");
      } else {
         this.iterating--;
         if (this.iterating == 0) {
            if (this.clear > 0 && this.clear == this.size) {
               this.remove.clear();
               this.clear();
            } else {
               int i = 0;

               for (int n = this.remove.size; i < n; i++) {
                  int index = this.remove.pop();
                  if (index >= this.clear) {
                     this.removeIndex(index);
                  }
               }

               for (int ix = this.clear - 1; ix >= 0; ix--) {
                  this.removeIndex(ix);
               }
            }

            this.clear = 0;
         }
      }
   }

   private void remove(int index) {
      if (index >= this.clear) {
         int i = 0;

         for (int n = this.remove.size; i < n; i++) {
            int removeIndex = this.remove.get(i);
            if (index == removeIndex) {
               return;
            }

            if (index < removeIndex) {
               this.remove.insert(i, index);
               return;
            }
         }

         this.remove.add(index);
      }
   }

   @Override
   public boolean removeValue(Object value, boolean identity) {
      if (this.iterating > 0) {
         int index = this.indexOf(value, identity);
         if (index == -1) {
            return false;
         } else {
            this.remove(index);
            return true;
         }
      } else {
         return super.removeValue(value, identity);
      }
   }

   @Override
   public Object removeIndex(int index) {
      if (this.iterating > 0) {
         this.remove(index);
         return this.get(index);
      } else {
         return super.removeIndex(index);
      }
   }

   @Override
   public void removeRange(int start, int end) {
      if (this.iterating > 0) {
         for (int i = end; i >= start; i--) {
            this.remove(i);
         }
      } else {
         super.removeRange(start, end);
      }
   }

   @Override
   public void clear() {
      if (this.iterating > 0) {
         this.clear = this.size;
      } else {
         super.clear();
      }
   }

   @Override
   public void set(int index, Object value) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.set(index, value);
      }
   }

   @Override
   public void insert(int index, Object value) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.insert(index, value);
      }
   }

   @Override
   public void insertRange(int index, int count) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.insertRange(index, count);
      }
   }

   @Override
   public void swap(int first, int second) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.swap(first, second);
      }
   }

   @Override
   public Object pop() {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         return super.pop();
      }
   }

   @Override
   public void sort() {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.sort();
      }
   }

   @Override
   public void sort(Comparator comparator) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.sort(comparator);
      }
   }

   @Override
   public void reverse() {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.reverse();
      }
   }

   @Override
   public void shuffle() {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.shuffle();
      }
   }

   @Override
   public void truncate(int newSize) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         super.truncate(newSize);
      }
   }

   @Override
   public Object[] setSize(int newSize) {
      if (this.iterating > 0) {
         throw new IllegalStateException("Invalid between begin/end.");
      } else {
         return super.setSize(newSize);
      }
   }

   public static DelayedRemovalArray with(Object... array) {
      return new DelayedRemovalArray(array);
   }
}
