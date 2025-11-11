package com.badlogic.gdx.utils;

public class PooledLinkedList {
   private PooledLinkedList.Item head;
   private PooledLinkedList.Item tail;
   private PooledLinkedList.Item iter;
   private PooledLinkedList.Item curr;
   private int size = 0;
   private final Pool pool;

   public PooledLinkedList(int maxPoolSize) {
      this.pool = new Pool(16, maxPoolSize) {
         protected PooledLinkedList.Item newObject() {
            return new PooledLinkedList.Item();
         }
      };
   }

   public void add(Object object) {
      PooledLinkedList.Item item = (PooledLinkedList.Item<T>)this.pool.obtain();
      item.payload = object;
      item.next = null;
      item.prev = null;
      if (this.head == null) {
         this.head = item;
         this.tail = item;
         this.size++;
      } else {
         item.prev = this.tail;
         this.tail.next = item;
         this.tail = item;
         this.size++;
      }
   }

   public void addFirst(Object object) {
      PooledLinkedList.Item item = (PooledLinkedList.Item<T>)this.pool.obtain();
      item.payload = object;
      item.next = this.head;
      item.prev = null;
      if (this.head != null) {
         this.head.prev = item;
      } else {
         this.tail = item;
      }

      this.head = item;
      this.size++;
   }

   public int size() {
      return this.size;
   }

   public void iter() {
      this.iter = this.head;
   }

   public void iterReverse() {
      this.iter = this.tail;
   }

   @Null
   public Object next() {
      if (this.iter == null) {
         return null;
      } else {
         Object payload = (T)this.iter.payload;
         this.curr = this.iter;
         this.iter = this.iter.next;
         return payload;
      }
   }

   @Null
   public Object previous() {
      if (this.iter == null) {
         return null;
      } else {
         Object payload = (T)this.iter.payload;
         this.curr = this.iter;
         this.iter = this.iter.prev;
         return payload;
      }
   }

   public void remove() {
      if (this.curr != null) {
         this.size--;
         PooledLinkedList.Item c = this.curr;
         PooledLinkedList.Item n = this.curr.next;
         PooledLinkedList.Item p = this.curr.prev;
         this.pool.free(this.curr);
         this.curr = null;
         if (this.size == 0) {
            this.head = null;
            this.tail = null;
         } else if (c == this.head) {
            n.prev = null;
            this.head = n;
         } else if (c == this.tail) {
            p.next = null;
            this.tail = p;
         } else {
            p.next = n;
            n.prev = p;
         }
      }
   }

   @Null
   public Object removeLast() {
      if (this.tail == null) {
         return null;
      } else {
         Object payload = (T)this.tail.payload;
         this.size--;
         PooledLinkedList.Item p = this.tail.prev;
         this.pool.free(this.tail);
         if (this.size == 0) {
            this.head = null;
            this.tail = null;
         } else {
            this.tail = p;
            this.tail.next = null;
         }

         return payload;
      }
   }

   public void clear() {
      this.iter();
      Object v = null;

      while (this.next() != null) {
         this.remove();
      }
   }

   static final class Item {
      public Object payload;
      public PooledLinkedList.Item next;
      public PooledLinkedList.Item prev;
   }
}
