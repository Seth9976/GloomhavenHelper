package com.badlogic.gdx.utils;

public abstract class Pool {
   public final int max;
   public int peak;
   private final Array freeObjects;

   public Pool() {
      this(16, Integer.MAX_VALUE);
   }

   public Pool(int initialCapacity) {
      this(initialCapacity, Integer.MAX_VALUE);
   }

   public Pool(int initialCapacity, int max) {
      this.freeObjects = new Array(false, initialCapacity);
      this.max = max;
   }

   protected abstract Object newObject();

   public Object obtain() {
      return this.freeObjects.size == 0 ? this.newObject() : this.freeObjects.pop();
   }

   public void free(Object object) {
      if (object == null) {
         throw new IllegalArgumentException("object cannot be null.");
      } else {
         if (this.freeObjects.size < this.max) {
            this.freeObjects.add(object);
            this.peak = Math.max(this.peak, this.freeObjects.size);
            this.reset(object);
         } else {
            this.discard(object);
         }
      }
   }

   public void fill(int size) {
      for (int i = 0; i < size; i++) {
         if (this.freeObjects.size < this.max) {
            this.freeObjects.add(this.newObject());
         }
      }

      this.peak = Math.max(this.peak, this.freeObjects.size);
   }

   protected void reset(Object object) {
      if (object instanceof Pool.Poolable) {
         ((Pool.Poolable)object).reset();
      }
   }

   protected void discard(Object object) {
   }

   public void freeAll(Array objects) {
      if (objects == null) {
         throw new IllegalArgumentException("objects cannot be null.");
      } else {
         Array freeObjects = this.freeObjects;
         int max = this.max;
         int i = 0;

         for (int n = objects.size; i < n; i++) {
            Object object = (T)objects.get(i);
            if (object != null) {
               if (freeObjects.size < max) {
                  freeObjects.add(object);
                  this.reset(object);
               } else {
                  this.discard(object);
               }
            }
         }

         this.peak = Math.max(this.peak, freeObjects.size);
      }
   }

   public void clear() {
      for (int i = 0; i < this.freeObjects.size; i++) {
         Object obj = (T)this.freeObjects.pop();
         this.discard(obj);
      }
   }

   public int getFree() {
      return this.freeObjects.size;
   }

   public interface Poolable {
      void reset();
   }
}
