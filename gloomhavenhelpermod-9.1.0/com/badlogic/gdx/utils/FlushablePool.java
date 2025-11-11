package com.badlogic.gdx.utils;

public abstract class FlushablePool extends Pool {
   protected Array obtained = new Array();

   public FlushablePool() {
   }

   public FlushablePool(int initialCapacity) {
      super(initialCapacity);
   }

   public FlushablePool(int initialCapacity, int max) {
      super(initialCapacity, max);
   }

   @Override
   public Object obtain() {
      Object result = (T)super.obtain();
      this.obtained.add(result);
      return result;
   }

   public void flush() {
      super.freeAll(this.obtained);
      this.obtained.clear();
   }

   @Override
   public void free(Object object) {
      this.obtained.removeValue(object, true);
      super.free(object);
   }

   @Override
   public void freeAll(Array objects) {
      this.obtained.removeAll(objects, true);
      super.freeAll(objects);
   }
}
