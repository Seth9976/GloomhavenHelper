package com.badlogic.gdx.utils;

public final class NumberUtils {
    public static long doubleToLongBits(double f) {
        return Double.doubleToLongBits(f);
    }

    public static int floatToIntBits(float f) {
        return Float.floatToIntBits(f);
    }

    public static int floatToIntColor(float f) {
        int v = Float.floatToRawIntBits(f);
        return v | ((int)(((float)(v >>> 24)) * 1.003937f)) << 24;
    }

    public static int floatToRawIntBits(float f) {
        return Float.floatToRawIntBits(f);
    }

    public static float intBitsToFloat(int v) {
        return Float.intBitsToFloat(v);
    }

    public static float intToFloatColor(int v) {
        return Float.intBitsToFloat(v & 0xFEFFFFFF);
    }

    public static double longBitsToDouble(long v) {
        return Double.longBitsToDouble(v);
    }
}

