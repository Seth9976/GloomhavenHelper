package com.badlogic.gdx.utils;

public class Pools {
   private static final ObjectMap typePools = new ObjectMap();

   public static Pool get(Class type, int max) {
      Pool pool = (Pool)typePools.get(type);
      if (pool == null) {
         pool = new ReflectionPool(type, 4, max);
         typePools.put(type, pool);
      }

      return pool;
   }

   public static Pool get(Class type) {
      return get(type, 100);
   }

   public static void set(Class type, Pool pool) {
      typePools.put(type, pool);
   }

   public static Object obtain(Class type) {
      return get(type).obtain();
   }

   public static void free(Object object) {
      if (object == null) {
         throw new IllegalArgumentException("object cannot be null.");
      } else {
         Pool pool = (Pool)typePools.get(object.getClass());
         if (pool != null) {
            pool.free(object);
         }
      }
   }

   public static void freeAll(Array objects) {
      freeAll(objects, false);
   }

   public static void freeAll(Array objects, boolean samePool) {
      if (objects == null) {
         throw new IllegalArgumentException("objects cannot be null.");
      } else {
         Pool pool = null;
         int i = 0;

         for (int n = objects.size; i < n; i++) {
            Object object = objects.get(i);
            if (object != null) {
               if (pool == null) {
                  pool = (Pool)typePools.get(object.getClass());
                  if (pool == null) {
                     continue;
                  }
               }

               pool.free(object);
               if (!samePool) {
                  pool = null;
               }
            }
         }
      }
   }

   private Pools() {
   }
}
