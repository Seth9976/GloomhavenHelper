package com.badlogic.gdx.math;

public final class GeometryUtils {
    private static final Vector2 tmp1;
    private static final Vector2 tmp2;
    private static final Vector2 tmp3;

    static {
        GeometryUtils.tmp1 = new Vector2();
        GeometryUtils.tmp2 = new Vector2();
        GeometryUtils.tmp3 = new Vector2();
    }

    public static boolean barycoordInsideTriangle(Vector2 vector20) {
        return vector20.x >= 0.0f && vector20.y >= 0.0f && vector20.x + vector20.y <= 1.0f;
    }

    public static boolean colinear(float f, float f1, float f2, float f3, float f4, float f5) {
        return Math.abs((f4 - f2) * (f3 - f1) - (f2 - f) * (f5 - f3)) < 0.000001f;
    }

    public static void ensureCCW(float[] arr_f) {
        GeometryUtils.ensureCCW(arr_f, 0, arr_f.length);
    }

    public static void ensureCCW(float[] arr_f, int v, int v1) {
        if(!GeometryUtils.isClockwise(arr_f, v, v1)) {
            return;
        }
        int v2 = v + v1 - 2;
        int v3 = v1 / 2 + v;
        while(v < v3) {
            int v4 = v2 - v;
            float f = arr_f[v];
            float f1 = arr_f[v + 1];
            arr_f[v] = arr_f[v4];
            arr_f[v + 1] = arr_f[v4 + 1];
            arr_f[v4] = f;
            arr_f[v4 + 1] = f1;
            v += 2;
        }
    }

    public static float fromBarycoord(Vector2 vector20, float f, float f1, float f2) {
        return (1.0f - vector20.x - vector20.y) * f + vector20.x * f1 + vector20.y * f2;
    }

    public static Vector2 fromBarycoord(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        float f = 1.0f - vector20.x - vector20.y;
        vector24.x = vector21.x * f + vector20.x * vector22.x + vector20.y * vector23.x;
        vector24.y = f * vector21.y + vector20.x * vector22.y + vector20.y * vector23.y;
        return vector24;
    }

    public static boolean isClockwise(float[] arr_f, int v, int v1) {
        if(v1 <= 2) {
            return false;
        }
        int v2 = v1 + v - 2;
        float f = arr_f[v2];
        float f1 = arr_f[v2 + 1];
        float f2 = f;
        float f3 = 0.0f;
        while(v <= v2) {
            float f4 = arr_f[v];
            float f5 = arr_f[v + 1];
            f3 += f2 * f5 - f1 * f4;
            v += 2;
            f2 = f4;
            f1 = f5;
        }
        return f3 < 0.0f;
    }

    public static float lowestPositiveRoot(float f, float f1, float f2) {
        float f3 = f1 * f1 - 4.0f * f * f2;
        if(f3 < 0.0f) {
            return NaNf;
        }
        float f4 = (float)Math.sqrt(f3);
        float f5 = 1.0f / (f * 2.0f);
        float f6 = (-f1 - f4) * f5;
        float f7 = (f4 - f1) * f5;
        if(f6 <= f7) {
            float f8 = f6;
            f6 = f7;
            f7 = f8;
        }
        if(f7 > 0.0f) {
            return f7;
        }
        return f6 > 0.0f ? f6 : NaNf;
    }

    public static float polygonArea(float[] arr_f, int v, int v1) {
        int v2 = v1 + v - 2;
        float f = arr_f[v2];
        float f1 = arr_f[v2 + 1];
        float f2 = 0.0f;
        while(v <= v2) {
            float f3 = arr_f[v];
            float f4 = arr_f[v + 1];
            f2 += f * f4 - f1 * f3;
            v += 2;
            f = f3;
            f1 = f4;
        }
        return f2 * 0.5f;
    }

    public static Vector2 polygonCentroid(float[] arr_f, int v, int v1, Vector2 vector20) {
        if(v1 < 6) {
            throw new IllegalArgumentException("A polygon must have 3 or more coordinate pairs.");
        }
        int v2 = v1 + v - 2;
        float f = arr_f[v2];
        float f1 = arr_f[v2 + 1];
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = f;
        float f5 = 0.0f;
        while(v <= v2) {
            float f6 = arr_f[v];
            float f7 = arr_f[v + 1];
            float f8 = f4 * f7 - f6 * f1;
            f5 += f8;
            f2 += (f4 + f6) * f8;
            f3 += (f1 + f7) * f8;
            v += 2;
            f4 = f6;
            f1 = f7;
        }
        if(f5 == 0.0f) {
            vector20.x = 0.0f;
            vector20.y = 0.0f;
            return vector20;
        }
        vector20.x = f2 / (f5 * 3.0f);
        vector20.y = f3 / (f5 * 3.0f);
        return vector20;
    }

    public static Vector2 quadrilateralCentroid(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, Vector2 vector20) {
        float f8 = (f2 + f + f4) / 3.0f;
        float f9 = (f3 + f1 + f5) / 3.0f;
        vector20.x = f8 - (f8 - (f + f6 + f4) / 3.0f) / 2.0f;
        vector20.y = f9 - (f9 - (f1 + f7 + f5) / 3.0f) / 2.0f;
        return vector20;
    }

    public static Vector2 toBarycoord(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        Vector2 vector25 = GeometryUtils.tmp1.set(vector22).sub(vector21);
        Vector2 vector26 = GeometryUtils.tmp2.set(vector23).sub(vector21);
        Vector2 vector27 = GeometryUtils.tmp3.set(vector20).sub(vector21);
        float f = vector25.dot(vector25);
        float f1 = vector25.dot(vector26);
        float f2 = vector26.dot(vector26);
        float f3 = vector27.dot(vector25);
        float f4 = vector27.dot(vector26);
        float f5 = f * f2 - f1 * f1;
        vector24.x = (f2 * f3 - f1 * f4) / f5;
        vector24.y = (f * f4 - f1 * f3) / f5;
        return vector24;
    }

    public static float triangleArea(float f, float f1, float f2, float f3, float f4, float f5) {
        return Math.abs((f - f4) * (f3 - f1) - (f - f2) * (f5 - f1)) * 0.5f;
    }

    public static Vector2 triangleCentroid(float f, float f1, float f2, float f3, float f4, float f5, Vector2 vector20) {
        vector20.x = (f + f2 + f4) / 3.0f;
        vector20.y = (f1 + f3 + f5) / 3.0f;
        return vector20;
    }

    public static Vector2 triangleCircumcenter(float f, float f1, float f2, float f3, float f4, float f5, Vector2 vector20) {
        float f6 = f2 - f;
        float f7 = f3 - f1;
        float f8 = f4 - f2;
        float f9 = f5 - f3;
        float f10 = f8 * f7 - f6 * f9;
        if(Math.abs(f10) < 0.000001f) {
            throw new IllegalArgumentException("Triangle points must not be colinear.");
        }
        float f11 = f * f + f1 * f1;
        float f12 = f2 * f2 + f3 * f3;
        float f13 = f4 * f4 + f5 * f5;
        vector20.set((f9 * f11 + (f1 - f5) * f12 + f7 * f13) / (f10 * 2.0f), -(f11 * f8 + f12 * (f - f4) + f13 * f6) / (f10 * 2.0f));
        return vector20;
    }

    public static float triangleCircumradius(float f, float f1, float f2, float f3, float f4, float f5) {
        float f8;
        float f7;
        float f6 = f3 - f1;
        if(Math.abs(f6) < 0.000001f) {
            f7 = (f2 + f) / 2.0f;
            f8 = -(f4 - f2) / (f5 - f3) * (f7 - (f4 + f2) / 2.0f) + (f3 + f5) / 2.0f;
            return (float)Math.sqrt((f - f7) * (f - f7) + (f1 - f8) * (f1 - f8));
        }
        float f9 = f5 - f3;
        if(Math.abs(f9) < 0.000001f) {
            f7 = (f4 + f2) / 2.0f;
            f8 = -(f2 - f) / f6 * (f7 - (f + f2) / 2.0f) + (f3 + f1) / 2.0f;
            return (float)Math.sqrt((f - f7) * (f - f7) + (f1 - f8) * (f1 - f8));
        }
        float f10 = -(f2 - f) / f6;
        float f11 = -(f4 - f2) / f9;
        float f12 = (f + f2) / 2.0f;
        float f13 = (f1 + f3) / 2.0f;
        f7 = (f10 * f12 - (f2 + f4) / 2.0f * f11 + (f3 + f5) / 2.0f - f13) / (f10 - f11);
        f8 = f10 * (f7 - f12) + f13;
        return (float)Math.sqrt((f - f7) * (f - f7) + (f1 - f8) * (f1 - f8));
    }

    public static float triangleQuality(float f, float f1, float f2, float f3, float f4, float f5) {
        return Math.min(((float)Math.sqrt(f * f + f1 * f1)), Math.min(((float)Math.sqrt(f2 * f2 + f3 * f3)), ((float)Math.sqrt(f4 * f4 + f5 * f5)))) / GeometryUtils.triangleCircumradius(f, f1, f2, f3, f4, f5);
    }
}

