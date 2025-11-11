package com.badlogic.gdx.utils;

public class IdentityMap extends ObjectMap {
   public IdentityMap() {
   }

   public IdentityMap(int initialCapacity) {
      super(initialCapacity);
   }

   public IdentityMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public IdentityMap(IdentityMap map) {
      super(map);
   }

   @Override
   protected int place(Object item) {
      return (int)(System.identityHashCode(item) * -7046029254386353131L >>> this.shift);
   }

   @Override
   int locateKey(Object key) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         Object[] keyTable = (K[])this.keyTable;
         int i = this.place(key);

         while (true) {
            Object other = keyTable[i];
            if (other == null) {
               return -(i + 1);
            }

            if (other == key) {
               return i;
            }

            i = i + 1 & this.mask;
         }
      }
   }

   @Override
   public int hashCode() {
      int h = this.size;
      Object[] keyTable = (K[])this.keyTable;
      Object[] valueTable = (V[])this.valueTable;
      int i = 0;

      for (int n = keyTable.length; i < n; i++) {
         Object key = keyTable[i];
         if (key != null) {
            h += System.identityHashCode(key);
            Object value = valueTable[i];
            if (value != null) {
               h += value.hashCode();
            }
         }
      }

      return h;
   }
}
