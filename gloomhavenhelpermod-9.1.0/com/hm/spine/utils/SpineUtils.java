package com.hm.spine.utils;

import java.lang.reflect.Array;

public class SpineUtils {
   public static final float PI = (float) Math.PI;
   public static final float PI2 = (float) (Math.PI * 2);
   public static final float radiansToDegrees = 180.0F / (float)Math.PI;
   public static final float radDeg = 180.0F / (float)Math.PI;
   public static final float degreesToRadians = (float) (Math.PI / 180.0);
   public static final float degRad = (float) (Math.PI / 180.0);

   public static float cosDeg(float degrees) {
      return (float)Math.cos(degrees * (float) (Math.PI / 180.0));
   }

   public static float sinDeg(float degrees) {
      return (float)Math.sin(degrees * (float) (Math.PI / 180.0));
   }

   public static float cos(float radians) {
      return (float)Math.cos(radians);
   }

   public static float sin(float radians) {
      return (float)Math.sin(radians);
   }

   public static float atan2(float y, float x) {
      return (float)Math.atan2(y, x);
   }

   public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
      if (src == null) {
         throw new IllegalArgumentException("src cannot be null.");
      } else if (dest == null) {
         throw new IllegalArgumentException("dest cannot be null.");
      } else {
         try {
            System.arraycopy(src, srcPos, dest, destPos, length);
         } catch (ArrayIndexOutOfBoundsException var6) {
            throw new ArrayIndexOutOfBoundsException(
               "Src: " + Array.getLength(src) + ", " + srcPos + ", dest: " + Array.getLength(dest) + ", " + destPos + ", count: " + length
            );
         }
      }
   }
}
