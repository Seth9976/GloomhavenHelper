package com.badlogic.gdx.utils;

public class SortedIntList implements Iterable {
   private SortedIntList.NodePool nodePool = new SortedIntList.NodePool();
   private transient SortedIntList.Iterator iterator;
   int size = 0;
   SortedIntList.Node first;

   @Null
   public Object insert(int index, Object value) {
      if (this.first != null) {
         SortedIntList.Node c = this.first;

         while (c.n != null && c.n.index <= index) {
            c = c.n;
         }

         if (index > c.index) {
            c.n = this.nodePool.obtain(c, c.n, value, index);
            if (c.n.n != null) {
               c.n.n.p = c.n;
            }

            this.size++;
         } else if (index < c.index) {
            SortedIntList.Node newFirst = this.nodePool.obtain(null, this.first, value, index);
            this.first.p = newFirst;
            this.first = newFirst;
            this.size++;
         } else {
            c.value = value;
         }
      } else {
         this.first = this.nodePool.obtain(null, null, value, index);
         this.size++;
      }

      return null;
   }

   public Object get(int index) {
      Object match = null;
      if (this.first != null) {
         SortedIntList.Node c = this.first;

         while (c.n != null && c.index < index) {
            c = c.n;
         }

         if (c.index == index) {
            match = (E)c.value;
         }
      }

      return match;
   }

   public void clear() {
      while (this.first != null) {
         this.nodePool.free(this.first);
         this.first = this.first.n;
      }

      this.size = 0;
   }

   public int size() {
      return this.size;
   }

   public boolean notEmpty() {
      return this.size > 0;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   @Override
   public java.util.Iterator iterator() {
      if (Collections.allocateIterators) {
         return new SortedIntList.Iterator();
      } else {
         if (this.iterator == null) {
            this.iterator = new SortedIntList.Iterator();
         }

         return this.iterator.reset();
      }
   }

   public class Iterator implements java.util.Iterator {
      private SortedIntList.Node position;
      private SortedIntList.Node previousPosition;

      @Override
      public boolean hasNext() {
         return this.position != null;
      }

      public SortedIntList.Node next() {
         this.previousPosition = this.position;
         this.position = this.position.n;
         return this.previousPosition;
      }

      @Override
      public void remove() {
         if (this.previousPosition != null) {
            if (this.previousPosition == SortedIntList.this.first) {
               SortedIntList.this.first = this.position;
            } else {
               this.previousPosition.p.n = this.position;
               if (this.position != null) {
                  this.position.p = this.previousPosition.p;
               }
            }

            SortedIntList.this.size--;
         }
      }

      public SortedIntList.Iterator reset() {
         this.position = SortedIntList.this.first;
         this.previousPosition = null;
         return this;
      }
   }

   public static class Node {
      protected SortedIntList.Node p;
      protected SortedIntList.Node n;
      public Object value;
      public int index;
   }

   static class NodePool extends Pool {
      protected SortedIntList.Node newObject() {
         return new SortedIntList.Node();
      }

      public SortedIntList.Node obtain(SortedIntList.Node p, SortedIntList.Node n, Object value, int index) {
         SortedIntList.Node newNode = (SortedIntList.Node<E>)super.obtain();
         newNode.p = p;
         newNode.n = n;
         newNode.value = value;
         newNode.index = index;
         return newNode;
      }
   }
}
