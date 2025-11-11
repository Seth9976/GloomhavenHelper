package com.badlogic.gdx.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicQueue {
   private final AtomicInteger writeIndex = new AtomicInteger();
   private final AtomicInteger readIndex = new AtomicInteger();
   private final AtomicReferenceArray queue;

   public AtomicQueue(int capacity) {
      this.queue = new AtomicReferenceArray(capacity);
   }

   private int next(int idx) {
      return (idx + 1) % this.queue.length();
   }

   public boolean put(@Null Object value) {
      int write = this.writeIndex.get();
      int read = this.readIndex.get();
      int next = this.next(write);
      if (next == read) {
         return false;
      } else {
         this.queue.set(write, value);
         this.writeIndex.set(next);
         return true;
      }
   }

   @Null
   public Object poll() {
      int read = this.readIndex.get();
      int write = this.writeIndex.get();
      if (read == write) {
         return null;
      } else {
         Object value = (T)this.queue.get(read);
         this.readIndex.set(this.next(read));
         return value;
      }
   }
}
