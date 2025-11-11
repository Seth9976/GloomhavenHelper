package com.badlogic.gdx.math;

import java.util.Random;

public final class MathUtils {
    static class Sin {
        static final float[] table;

        static {
            Sin.table = new float[0x4000];
            for(int v1 = 0; v1 < 0x4000; ++v1) {
                Sin.table[v1] = (float)Math.sin((((float)v1) + 0.5f) / 16384.0f * 6.283185f);
            }
            for(int v = 0; v < 360; v += 90) {
                Sin.table[((int)(45.511112f * ((float)v))) & 0x3FFF] = (float)Math.sin(((float)v) * 0.017453f);
            }
        }
    }

    private static final double BIG_ENOUGH_CEIL = 16385.0;
    private static final double BIG_ENOUGH_FLOOR = 16384.0;
    private static final int BIG_ENOUGH_INT = 0x4000;
    private static final double BIG_ENOUGH_ROUND = 16384.5;
    private static final double CEIL = 1.0;
    public static final float E = 2.718282f;
    public static final float FLOAT_ROUNDING_ERROR = 0.000001f;
    public static final float HALF_PI = 1.570796f;
    public static final float PI = 3.141593f;
    public static final float PI2 = 6.283185f;
    private static final int SIN_BITS = 14;
    private static final int SIN_COUNT = 0x4000;
    private static final int SIN_MASK = 0x3FFF;
    private static final float degFull = 360.0f;
    public static final float degRad = 0.017453f;
    private static final float degToIndex = 45.511112f;
    public static final float degreesToRadians = 0.017453f;
    public static final float nanoToSec = 1.000000E-09f;
    public static final float radDeg = 57.295776f;
    private static final float radFull = 6.283185f;
    private static final float radToIndex = 2607.594482f;
    public static final float radiansToDegrees = 57.295776f;
    public static Random random;

    static {
        MathUtils.random = new RandomXS128();
    }

    public static float acos(float f) {
        float f1 = f * f;
        float f2 = f * f1;
        return f >= 0.0f ? ((float)Math.sqrt(1.0f - f)) * (1.570729f - f * 0.212114f + f1 * 0.074261f - f2 * 0.018729f) : 3.141593f - ((float)Math.sqrt(f + 1.0f)) * (f * 0.212114f + 1.570729f + f1 * 0.074261f + f2 * 0.018729f);
    }

    public static float asin(float f) {
        float f1 = f * f;
        float f2 = f * f1;
        return f >= 0.0f ? 1.570796f - ((float)Math.sqrt(1.0f - f)) * (1.570729f - f * 0.212114f + f1 * 0.074261f - f2 * 0.018729f) : ((float)Math.sqrt(f + 1.0f)) * (f * 0.212114f + 1.570729f + f1 * 0.074261f + f2 * 0.018729f) - 1.570796f;
    }

    public static float atan2(float f, float f1) {
        if(f1 == 0.0f) {
            if(f > 0.0f) {
                return 1.570796f;
            }
            return f == 0.0f ? 0.0f : -1.570796f;
        }
        float f2 = f / f1;
        float f3 = 3.141593f;
        if(Math.abs(f2) < 1.0f) {
            float f4 = f2 / (0.28f * f2 * f2 + 1.0f);
            if(f1 < 0.0f) {
                if(f < 0.0f) {
                    f3 = -3.141593f;
                }
                return f4 + f3;
            }
            return f4;
        }
        float f5 = 1.570796f - f2 / (f2 * f2 + 0.28f);
        return f < 0.0f ? f5 - 3.141593f : f5;
    }

    public static int ceil(float f) [...] // Inlined contents

    public static int ceilPositive(float f) {
        return (int)(((double)f) + 1.0);
    }

    public static double clamp(double f, double f1, double f2) {
        if(f < f1) {
            return f1;
        }
        return f > f2 ? f2 : f;
    }

    public static float clamp(float f, float f1, float f2) {
        if(f < f1) {
            return f1;
        }
        return f > f2 ? f2 : f;
    }

    public static int clamp(int v, int v1, int v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    public static long clamp(long v, long v1, long v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    public static short clamp(short v, short v1, short v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    public static float cos(float f) {
        return Sin.table[((int)((f + 1.570796f) * 2607.594482f)) & 0x3FFF];
    }

    public static float cosDeg(float f) {
        return Sin.table[((int)((f + 90.0f) * 45.511112f)) & 0x3FFF];
    }

    public static int floor(float f) {
        return ((int)(((double)f) + 16384.0)) - 0x4000;
    }

    public static int floorPositive(float f) {
        return (int)f;
    }

    public static boolean isEqual(float f, float f1) {
        return Math.abs(f - f1) <= 0.000001f;
    }

    public static boolean isEqual(float f, float f1, float f2) {
        return Math.abs(f - f1) <= f2;
    }

    public static boolean isPowerOfTwo(int v) {
        return v != 0 && (v & v - 1) == 0;
    }

    public static boolean isZero(float f) {
        return Math.abs(f) <= 0.000001f;
    }

    public static boolean isZero(float f, float f1) {
        return Math.abs(f) <= f1;
    }

    public static float lerp(float f, float f1, float f2) {
        return f + (f1 - f) * f2;
    }

    public static float lerpAngle(float f, float f1, float f2) {
        return (f + ((f1 - f + 9.424778f) % 6.283185f - 3.141593f) * f2 + 6.283185f) % 6.283185f;
    }

    public static float lerpAngleDeg(float f, float f1, float f2) {
        return (f + ((f1 - f + 540.0f) % 360.0f - 180.0f) * f2 + 360.0f) % 360.0f;
    }

    public static float log(float f, float f1) {
        return (float)(Math.log(f1) / Math.log(f));
    }

    public static float log2(float f) {
        return MathUtils.log(2.0f, f);
    }

    public static float map(float f, float f1, float f2, float f3, float f4) {
        return f2 + (f4 - f) * (f3 - f2) / (f1 - f);
    }

    public static int nextPowerOfTwo(int v) {
        if(v == 0) {
            return 1;
        }
        int v1 = v - 1 | v - 1 >> 1;
        int v2 = v1 | v1 >> 2;
        int v3 = v2 | v2 >> 4;
        int v4 = v3 | v3 >> 8;
        return (v4 | v4 >> 16) + 1;
    }

    public static float norm(float f, float f1, float f2) {
        return (f2 - f) / (f1 - f);
    }

    public static float random() {
        return MathUtils.random.nextFloat();
    }

    public static float random(float f) {
        return MathUtils.random.nextFloat() * f;
    }

    public static float random(float f, float f1) {
        return f + MathUtils.random.nextFloat() * (f1 - f);
    }

    public static int random(int v) {
        return MathUtils.random.nextInt(v + 1);
    }

    public static int random(int v, int v1) {
        return v + MathUtils.random.nextInt(v1 - v + 1);
    }

    public static long random(long v) {
        return MathUtils.random(0L, v);
    }

    public static long random(long v, long v1) {
        long v2 = MathUtils.random.nextLong();
        if(v1 >= v) {
            long v3 = v;
            v = v1;
            v1 = v3;
        }
        long v4 = v - v1 + 1L;
        return v1 + ((0xFFFFFFFFL & v4) * (v2 >>> 0x20) >>> 0x20) + ((v2 & 0xFFFFFFFFL) * (v4 >>> 0x20) >>> 0x20) + (v2 >>> 0x20) * (v4 >>> 0x20);
    }

    public static boolean randomBoolean() {
        return MathUtils.random.nextBoolean();
    }

    public static boolean randomBoolean(float f) {
        return MathUtils.random() < f;
    }

    public static int randomSign() {
        return MathUtils.random.nextInt() >> 0x1F | 1;
    }

    public static float randomTriangular() {
        return MathUtils.random.nextFloat() - MathUtils.random.nextFloat();
    }

    public static float randomTriangular(float f) {
        return (MathUtils.random.nextFloat() - MathUtils.random.nextFloat()) * f;
    }

    public static float randomTriangular(float f, float f1) {
        return MathUtils.randomTriangular(f, f1, (f + f1) * 0.5f);
    }

    public static float randomTriangular(float f, float f1, float f2) {
        float f3 = MathUtils.random.nextFloat();
        float f4 = f1 - f;
        float f5 = f2 - f;
        return f3 <= f5 / f4 ? f + ((float)Math.sqrt(f3 * f4 * f5)) : f1 - ((float)Math.sqrt((1.0f - f3) * f4 * (f1 - f2)));
    }

    public static int round(float f) {
        return ((int)(((double)f) + 16384.5)) - 0x4000;
    }

    public static int roundPositive(float f) {
        return (int)(f + 0.5f);
    }

    public static float sin(float f) {
        return Sin.table[((int)(f * 2607.594482f)) & 0x3FFF];
    }

    public static float sinDeg(float f) {
        return Sin.table[((int)(f * 45.511112f)) & 0x3FFF];
    }
}

