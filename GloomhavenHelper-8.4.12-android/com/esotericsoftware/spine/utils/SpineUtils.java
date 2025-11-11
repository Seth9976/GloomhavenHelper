package com.esotericsoftware.spine.utils;

import java.lang.reflect.Array;

public class SpineUtils {
    public static final float PI = 3.141593f;
    public static final float PI2 = 6.283185f;
    public static final float degRad = 0.017453f;
    public static final float degreesToRadians = 0.017453f;
    public static final float radDeg = 57.295776f;
    public static final float radiansToDegrees = 57.295776f;

    public static void arraycopy(Object object0, int v, Object object1, int v1, int v2) {
        if(object0 == null) {
            throw new IllegalArgumentException("src cannot be null.");
        }
        if(object1 != null) {
            try {
                System.arraycopy(object0, v, object1, v1, v2);
                return;
            }
            catch(ArrayIndexOutOfBoundsException unused_ex) {
                throw new ArrayIndexOutOfBoundsException("Src: " + Array.getLength(object0) + ", " + v + ", dest: " + Array.getLength(object1) + ", " + v1 + ", count: " + v2);
            }
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public static float atan2(float f, float f1) {
        return (float)Math.atan2(f, f1);
    }

    public static float cos(float f) {
        return (float)Math.cos(f);
    }

    public static float cosDeg(float f) {
        return (float)Math.cos(f * 0.017453f);
    }

    public static float sin(float f) {
        return (float)Math.sin(f);
    }

    public static float sinDeg(float f) {
        return (float)Math.sin(f * 0.017453f);
    }
}

