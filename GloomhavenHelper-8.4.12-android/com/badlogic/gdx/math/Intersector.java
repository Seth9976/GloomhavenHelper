package com.badlogic.gdx.math;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import java.util.Arrays;
import java.util.List;

public final class Intersector {
    public static class MinimumTranslationVector {
        public float depth;
        public Vector2 normal;

        public MinimumTranslationVector() {
            this.normal = new Vector2();
            this.depth = 0.0f;
        }
    }

    public static class SplitTriangle {
        public float[] back;
        int backOffset;
        float[] edgeSplit;
        public float[] front;
        boolean frontCurrent;
        int frontOffset;
        public int numBack;
        public int numFront;
        public int total;

        public SplitTriangle(int v) {
            this.frontCurrent = false;
            this.frontOffset = 0;
            this.backOffset = 0;
            this.front = new float[v * 6];
            this.back = new float[v * 6];
            this.edgeSplit = new float[v];
        }

        void add(float[] arr_f, int v, int v1) {
            if(this.frontCurrent) {
                System.arraycopy(arr_f, v, this.front, this.frontOffset, v1);
                this.frontOffset += v1;
                return;
            }
            System.arraycopy(arr_f, v, this.back, this.backOffset, v1);
            this.backOffset += v1;
        }

        boolean getSide() {
            return this.frontCurrent;
        }

        void reset() {
            this.frontCurrent = false;
            this.frontOffset = 0;
            this.backOffset = 0;
            this.numFront = 0;
            this.numBack = 0;
            this.total = 0;
        }

        void setSide(boolean z) {
            this.frontCurrent = z;
        }

        @Override
        public String toString() {
            return "SplitTriangle [front=" + Arrays.toString(this.front) + ", back=" + Arrays.toString(this.back) + ", numFront=" + this.numFront + ", numBack=" + this.numBack + ", total=" + this.total + "]";
        }
    }

    static Vector3 best;
    private static final Vector3 dir;
    private static final Vector2 e;
    private static final Vector2 ep1;
    private static final Vector2 ep2;
    private static final FloatArray floatArray;
    private static final FloatArray floatArray2;
    private static final Vector3 i;
    static Vector3 intersection;
    private static final Vector2 ip;
    private static final Plane p;
    private static final Vector2 s;
    private static final Vector3 start;
    static Vector3 tmp;
    static Vector3 tmp1;
    static Vector3 tmp2;
    static Vector3 tmp3;
    private static final Vector3 v0;
    private static final Vector3 v1;
    private static final Vector3 v2;
    static Vector2 v2a;
    static Vector2 v2b;
    static Vector2 v2c;
    static Vector2 v2d;

    static {
        Intersector.v0 = new Vector3();
        Intersector.v1 = new Vector3();
        Intersector.v2 = new Vector3();
        Intersector.floatArray = new FloatArray();
        Intersector.floatArray2 = new FloatArray();
        Intersector.ip = new Vector2();
        Intersector.ep1 = new Vector2();
        Intersector.ep2 = new Vector2();
        Intersector.s = new Vector2();
        Intersector.e = new Vector2();
        Intersector.v2a = new Vector2();
        Intersector.v2b = new Vector2();
        Intersector.v2c = new Vector2();
        Intersector.v2d = new Vector2();
        Intersector.p = new Plane(new Vector3(), 0.0f);
        Intersector.i = new Vector3();
        Intersector.dir = new Vector3();
        Intersector.start = new Vector3();
        Intersector.best = new Vector3();
        Intersector.tmp = new Vector3();
        Intersector.tmp1 = new Vector3();
        Intersector.tmp2 = new Vector3();
        Intersector.tmp3 = new Vector3();
        Intersector.intersection = new Vector3();
    }

    static float det(float f, float f1, float f2, float f3) {
        return f * f3 - f1 * f2;
    }

    static double detd(double f, double f1, double f2, double f3) {
        return f * f3 - f1 * f2;
    }

    public static float distanceLinePoint(float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = f2 - f;
        float f7 = f3 - f1;
        return Math.abs((f4 - f) * f7 - (f5 - f1) * f6) / ((float)Math.sqrt(f6 * f6 + f7 * f7));
    }

    public static float distanceSegmentPoint(float f, float f1, float f2, float f3, float f4, float f5) {
        return Intersector.nearestSegmentPoint(f, f1, f2, f3, f4, f5, Intersector.v2a).dst(f4, f5);
    }

    public static float distanceSegmentPoint(Vector2 vector20, Vector2 vector21, Vector2 vector22) {
        return Intersector.nearestSegmentPoint(vector20, vector21, vector22, Intersector.v2a).dst(vector22);
    }

    public static boolean intersectBoundsPlaneFast(Vector3 vector30, Vector3 vector31, Vector3 vector32, float f) {
        return Math.abs(vector32.dot(vector30) - f) <= vector31.x * Math.abs(vector32.x) + vector31.y * Math.abs(vector32.y) + vector31.z * Math.abs(vector32.z);
    }

    public static boolean intersectBoundsPlaneFast(BoundingBox boundingBox0, Plane plane0) {
        return Intersector.intersectBoundsPlaneFast(boundingBox0.getCenter(Intersector.tmp1), boundingBox0.getDimensions(Intersector.tmp2).scl(0.5f), plane0.normal, plane0.d);
    }

    public static float intersectLinePlane(float f, float f1, float f2, float f3, float f4, float f5, Plane plane0, Vector3 vector30) {
        Vector3 vector31 = Intersector.tmp.set(f3, f4, f5).sub(f, f1, f2);
        Vector3 vector32 = Intersector.tmp2.set(f, f1, f2);
        float f6 = vector31.dot(plane0.getNormal());
        if(f6 != 0.0f) {
            float f7 = -(vector32.dot(plane0.getNormal()) + plane0.getD()) / f6;
            if(vector30 != null) {
                vector30.set(vector32).add(vector31.scl(f7));
            }
            return f7;
        }
        if(plane0.testPoint(vector32) == PlaneSide.OnPlane) {
            if(vector30 != null) {
                vector30.set(vector32);
            }
            return 0.0f;
        }
        return -1.0f;
    }

    public static boolean intersectLinePolygon(Vector2 vector20, Vector2 vector21, Polygon polygon0) {
        float[] arr_f = polygon0.getTransformedVertices();
        float f = vector20.x;
        float f1 = vector20.y;
        float f2 = vector21.x;
        float f3 = vector21.y;
        float f4 = arr_f[arr_f.length - 2];
        float f5 = arr_f[arr_f.length - 1];
        float f6 = f4;
        int v = 0;
        while(v < arr_f.length) {
            float f7 = arr_f[v];
            float f8 = arr_f[v + 1];
            float f9 = f8 - f5;
            float f10 = f7 - f6;
            float f11 = (f2 - f) * f9 - (f3 - f1) * f10;
            if(f11 != 0.0f) {
                float f12 = (f10 * (f1 - f5) - f9 * (f - f6)) / f11;
                if(f12 >= 0.0f && f12 <= 1.0f) {
                    return true;
                }
            }
            v += 2;
            f6 = f7;
            f5 = f8;
        }
        return false;
    }

    public static boolean intersectLines(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, Vector2 vector20) {
        float f8 = f7 - f5;
        float f9 = f2 - f;
        float f10 = f6 - f4;
        float f11 = f3 - f1;
        float f12 = f8 * f9 - f10 * f11;
        if(f12 == 0.0f) {
            return false;
        }
        if(vector20 != null) {
            float f13 = (f10 * (f1 - f5) - f8 * (f - f4)) / f12;
            vector20.set(f + f9 * f13, f1 + f11 * f13);
        }
        return true;
    }

    public static boolean intersectLines(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        float f = vector20.x;
        float f1 = vector20.y;
        float f2 = vector22.x;
        float f3 = vector22.y;
        float f4 = vector23.y - f3;
        float f5 = vector21.x - f;
        float f6 = vector23.x - f2;
        float f7 = vector21.y - f1;
        float f8 = f4 * f5 - f6 * f7;
        if(f8 == 0.0f) {
            return false;
        }
        if(vector24 != null) {
            float f9 = (f6 * (f1 - f3) - f4 * (f - f2)) / f8;
            vector24.set(f + f5 * f9, f1 + f7 * f9);
        }
        return true;
    }

    public static boolean intersectPlanes(Plane plane0, Plane plane1, Plane plane2, Vector3 vector30) {
        Intersector.tmp1.set(plane0.normal).crs(plane1.normal);
        Intersector.tmp2.set(plane1.normal).crs(plane2.normal);
        Intersector.tmp3.set(plane2.normal).crs(plane0.normal);
        float f = plane0.normal.dot(Intersector.tmp2);
        if(Math.abs(-f) < 0.000001f) {
            return false;
        }
        Intersector.tmp1.scl(plane2.d);
        Intersector.tmp2.scl(plane0.d);
        Intersector.tmp3.scl(plane1.d);
        vector30.set(Intersector.tmp1.x + Intersector.tmp2.x + Intersector.tmp3.x, Intersector.tmp1.y + Intersector.tmp2.y + Intersector.tmp3.y, Intersector.tmp1.z + Intersector.tmp2.z + Intersector.tmp3.z);
        vector30.scl(1.0f / -f);
        return true;
    }

    public static boolean intersectPolygonEdges(FloatArray floatArray0, FloatArray floatArray1) {
        int v = floatArray0.size - 2;
        int v1 = floatArray1.size - 2;
        float[] arr_f = floatArray0.items;
        float[] arr_f1 = floatArray1.items;
        float f = arr_f[v];
        float f1 = arr_f[v + 1];
        float f2 = f;
        int v2 = 0;
        while(v2 <= v) {
            float f3 = arr_f[v2];
            float f4 = arr_f[v2 + 1];
            float f5 = arr_f1[v1];
            float f6 = arr_f1[v1 + 1];
            int v3 = 0;
            while(v3 <= v1) {
                float f7 = arr_f1[v3];
                float f8 = arr_f1[v3 + 1];
                if(Intersector.intersectSegments(f2, f1, f3, f4, f5, f6, f7, f8, null)) {
                    return true;
                }
                v3 += 2;
                f5 = f7;
                f6 = f8;
            }
            v2 += 2;
            f2 = f3;
            f1 = f4;
        }
        return false;
    }

    public static boolean intersectPolygons(Polygon polygon0, Polygon polygon1, Polygon polygon2) {
        if(polygon0.getVertices().length != 0 && polygon1.getVertices().length != 0) {
            Vector2 vector20 = Intersector.ip;
            Vector2 vector21 = Intersector.ep1;
            Vector2 vector22 = Intersector.ep2;
            Vector2 vector23 = Intersector.s;
            Vector2 vector24 = Intersector.e;
            FloatArray floatArray0 = Intersector.floatArray;
            FloatArray floatArray1 = Intersector.floatArray2;
            floatArray0.clear();
            floatArray1.clear();
            floatArray1.addAll(polygon0.getTransformedVertices());
            float[] arr_f = polygon1.getTransformedVertices();
            int v = arr_f.length - 2;
            for(int v1 = 0; v1 <= v; v1 += 2) {
                vector21.set(arr_f[v1], arr_f[v1 + 1]);
                if(v1 < v) {
                    vector22.set(arr_f[v1 + 2], arr_f[v1 + 3]);
                }
                else {
                    vector22.set(arr_f[0], arr_f[1]);
                }
                if(floatArray1.size == 0) {
                    return false;
                }
                vector23.set(floatArray1.get(floatArray1.size - 2), floatArray1.get(floatArray1.size - 1));
                for(int v2 = 0; v2 < floatArray1.size; v2 += 2) {
                    vector24.set(floatArray1.get(v2), floatArray1.get(v2 + 1));
                    boolean z = Intersector.pointLineSide(vector22, vector21, vector23) > 0;
                    if(Intersector.pointLineSide(vector22, vector21, vector24) > 0) {
                        if(!z) {
                            Intersector.intersectLines(vector23, vector24, vector21, vector22, vector20);
                            if(floatArray0.size < 2 || floatArray0.get(floatArray0.size - 2) != vector20.x || floatArray0.get(floatArray0.size - 1) != vector20.y) {
                                floatArray0.add(vector20.x);
                                floatArray0.add(vector20.y);
                            }
                        }
                        floatArray0.add(vector24.x);
                        floatArray0.add(vector24.y);
                    }
                    else if(z) {
                        Intersector.intersectLines(vector23, vector24, vector21, vector22, vector20);
                        floatArray0.add(vector20.x);
                        floatArray0.add(vector20.y);
                    }
                    vector23.set(vector24.x, vector24.y);
                }
                floatArray1.clear();
                floatArray1.addAll(floatArray0);
                floatArray0.clear();
            }
            if(floatArray1.size != 0) {
                if(polygon2 != null) {
                    if(polygon2.getVertices().length == floatArray1.size) {
                        System.arraycopy(floatArray1.items, 0, polygon2.getVertices(), 0, floatArray1.size);
                        return true;
                    }
                    polygon2.setVertices(floatArray1.toArray());
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean intersectPolygons(FloatArray floatArray0, FloatArray floatArray1) {
        if(Intersector.isPointInPolygon(floatArray0.items, 0, floatArray0.size, floatArray1.items[0], floatArray1.items[1])) {
            return true;
        }
        return Intersector.isPointInPolygon(floatArray1.items, 0, floatArray1.size, floatArray0.items[0], floatArray0.items[1]) ? true : Intersector.intersectPolygonEdges(floatArray0, floatArray1);
    }

    public static boolean intersectRayBounds(Ray ray0, BoundingBox boundingBox0, Vector3 vector30) {
        float f;
        boolean z = false;
        if(boundingBox0.contains(ray0.origin)) {
            if(vector30 != null) {
                vector30.set(ray0.origin);
            }
            return true;
        }
        if(ray0.origin.x > boundingBox0.min.x || ray0.direction.x <= 0.0f) {
            f = 0.0f;
        }
        else {
            f = (boundingBox0.min.x - ray0.origin.x) / ray0.direction.x;
            if(f >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f).add(ray0.origin);
                if(Intersector.v2.y < boundingBox0.min.y || Intersector.v2.y > boundingBox0.max.y || Intersector.v2.z < boundingBox0.min.z || Intersector.v2.z > boundingBox0.max.z) {
                    f = 0.0f;
                }
                else {
                    z = true;
                }
            }
            else {
                f = 0.0f;
            }
        }
        if(ray0.origin.x >= boundingBox0.max.x && ray0.direction.x < 0.0f) {
            float f1 = (boundingBox0.max.x - ray0.origin.x) / ray0.direction.x;
            if(f1 >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f1).add(ray0.origin);
                if(Intersector.v2.y >= boundingBox0.min.y && Intersector.v2.y <= boundingBox0.max.y && Intersector.v2.z >= boundingBox0.min.z && Intersector.v2.z <= boundingBox0.max.z && (!z || f1 < f)) {
                    f = f1;
                    z = true;
                }
            }
        }
        if(ray0.origin.y <= boundingBox0.min.y && ray0.direction.y > 0.0f) {
            float f2 = (boundingBox0.min.y - ray0.origin.y) / ray0.direction.y;
            if(f2 >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f2).add(ray0.origin);
                if(Intersector.v2.x >= boundingBox0.min.x && Intersector.v2.x <= boundingBox0.max.x && Intersector.v2.z >= boundingBox0.min.z && Intersector.v2.z <= boundingBox0.max.z && (!z || f2 < f)) {
                    f = f2;
                    z = true;
                }
            }
        }
        if(ray0.origin.y >= boundingBox0.max.y && ray0.direction.y < 0.0f) {
            float f3 = (boundingBox0.max.y - ray0.origin.y) / ray0.direction.y;
            if(f3 >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f3).add(ray0.origin);
                if(Intersector.v2.x >= boundingBox0.min.x && Intersector.v2.x <= boundingBox0.max.x && Intersector.v2.z >= boundingBox0.min.z && Intersector.v2.z <= boundingBox0.max.z && (!z || f3 < f)) {
                    f = f3;
                    z = true;
                }
            }
        }
        if(ray0.origin.z <= boundingBox0.min.z && ray0.direction.z > 0.0f) {
            float f4 = (boundingBox0.min.z - ray0.origin.z) / ray0.direction.z;
            if(f4 >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f4).add(ray0.origin);
                if(Intersector.v2.x >= boundingBox0.min.x && Intersector.v2.x <= boundingBox0.max.x && Intersector.v2.y >= boundingBox0.min.y && Intersector.v2.y <= boundingBox0.max.y && (!z || f4 < f)) {
                    f = f4;
                    z = true;
                }
            }
        }
        if(ray0.origin.z >= boundingBox0.max.z && ray0.direction.z < 0.0f) {
            float f5 = (boundingBox0.max.z - ray0.origin.z) / ray0.direction.z;
            if(f5 >= 0.0f) {
                Intersector.v2.set(ray0.direction).scl(f5).add(ray0.origin);
                if(Intersector.v2.x >= boundingBox0.min.x && Intersector.v2.x <= boundingBox0.max.x && Intersector.v2.y >= boundingBox0.min.y && Intersector.v2.y <= boundingBox0.max.y && (!z || f5 < f)) {
                    f = f5;
                    z = true;
                }
            }
        }
        if(z && vector30 != null) {
            vector30.set(ray0.direction).scl(f).add(ray0.origin);
            if(vector30.x < boundingBox0.min.x) {
                vector30.x = boundingBox0.min.x;
            }
            else if(vector30.x > boundingBox0.max.x) {
                vector30.x = boundingBox0.max.x;
            }
            if(vector30.y < boundingBox0.min.y) {
                vector30.y = boundingBox0.min.y;
            }
            else if(vector30.y > boundingBox0.max.y) {
                vector30.y = boundingBox0.max.y;
            }
            if(vector30.z < boundingBox0.min.z) {
                vector30.z = boundingBox0.min.z;
                return z;
            }
            if(vector30.z > boundingBox0.max.z) {
                vector30.z = boundingBox0.max.z;
            }
        }
        return z;
    }

    public static boolean intersectRayBoundsFast(Ray ray0, Vector3 vector30, Vector3 vector31) {
        float f = 1.0f / ray0.direction.x;
        float f1 = 1.0f / ray0.direction.y;
        float f2 = 1.0f / ray0.direction.z;
        float f3 = (vector30.x - vector31.x * 0.5f - ray0.origin.x) * f;
        float f4 = f * (vector30.x + vector31.x * 0.5f - ray0.origin.x);
        if(f3 <= f4) {
            float f5 = f3;
            f3 = f4;
            f4 = f5;
        }
        float f6 = (vector30.y - vector31.y * 0.5f - ray0.origin.y) * f1;
        float f7 = f1 * (vector30.y + vector31.y * 0.5f - ray0.origin.y);
        if(f6 <= f7) {
            float f8 = f6;
            f6 = f7;
            f7 = f8;
        }
        float f9 = (vector30.z - vector31.z * 0.5f - ray0.origin.z) * f2;
        float f10 = (vector30.z + vector31.z * 0.5f - ray0.origin.z) * f2;
        if(f9 <= f10) {
            float f11 = f9;
            f9 = f10;
            f10 = f11;
        }
        float f12 = Math.min(Math.min(f3, f6), f9);
        return f12 >= 0.0f && f12 >= Math.max(Math.max(f4, f7), f10);
    }

    public static boolean intersectRayBoundsFast(Ray ray0, BoundingBox boundingBox0) {
        return Intersector.intersectRayBoundsFast(ray0, boundingBox0.getCenter(Intersector.tmp1), boundingBox0.getDimensions(Intersector.tmp2));
    }

    public static boolean intersectRayOrientedBoundsFast(Ray ray0, BoundingBox boundingBox0, Matrix4 matrix40) {
        float f3;
        Vector3 vector30 = matrix40.getTranslation(Intersector.tmp).sub(ray0.origin);
        Vector3 vector31 = Intersector.tmp1;
        vector31.set(matrix40.val[0], matrix40.val[1], matrix40.val[2]);
        float f = vector31.dot(vector30);
        float f1 = ray0.direction.dot(vector31);
        float f2 = 3.402823E+38f;
        if(Math.abs(f1) > 0.000001f) {
            f3 = (boundingBox0.min.x + f) / f1;
            float f4 = (f + boundingBox0.max.x) / f1;
            if(f3 > f4) {
                float f5 = f3;
                f3 = f4;
                f4 = f5;
            }
            if(f4 < 3.402823E+38f) {
                f2 = f4;
            }
            if(f3 <= 0.0f) {
                f3 = 0.0f;
            }
            if(f2 >= f3) {
                goto label_21;
            }
            return false;
        }
        if(boundingBox0.min.x + -f <= 0.0f && boundingBox0.max.x - f >= 0.0f) {
            f3 = 0.0f;
        label_21:
            Vector3 vector32 = Intersector.tmp2;
            vector32.set(matrix40.val[4], matrix40.val[5], matrix40.val[6]);
            float f6 = vector32.dot(vector30);
            float f7 = ray0.direction.dot(vector32);
            if(Math.abs(f7) > 0.000001f) {
                float f8 = (boundingBox0.min.y + f6) / f7;
                float f9 = (f6 + boundingBox0.max.y) / f7;
                if(f8 > f9) {
                    float f10 = f8;
                    f8 = f9;
                    f9 = f10;
                }
                if(f9 < f2) {
                    f2 = f9;
                }
                if(f8 > f3) {
                    f3 = f8;
                }
                if(f3 <= f2) {
                    goto label_39;
                }
                return false;
            }
            if(boundingBox0.min.y + -f6 <= 0.0f && boundingBox0.max.y - f6 >= 0.0f) {
            label_39:
                Vector3 vector33 = Intersector.tmp3;
                vector33.set(matrix40.val[8], matrix40.val[9], matrix40.val[10]);
                float f11 = vector33.dot(vector30);
                float f12 = ray0.direction.dot(vector33);
                if(Math.abs(f12) > 0.000001f) {
                    float f13 = (boundingBox0.min.z + f11) / f12;
                    float f14 = (f11 + boundingBox0.max.z) / f12;
                    if(f13 > f14) {
                        float f15 = f13;
                        f13 = f14;
                        f14 = f15;
                    }
                    if(f14 >= f2) {
                        f14 = f2;
                    }
                    if(f13 <= f3) {
                        f13 = f3;
                    }
                    return f13 <= f14;
                }
                return boundingBox0.min.z + -f11 <= 0.0f && boundingBox0.max.z - f11 >= 0.0f;
            }
            return false;
        }
        return false;
    }

    public static boolean intersectRayPlane(Ray ray0, Plane plane0, Vector3 vector30) {
        float f = ray0.direction.dot(plane0.getNormal());
        if(f != 0.0f) {
            float f1 = -(ray0.origin.dot(plane0.getNormal()) + plane0.getD()) / f;
            if(f1 < 0.0f) {
                return false;
            }
            if(vector30 != null) {
                vector30.set(ray0.origin).add(Intersector.v0.set(ray0.direction).scl(f1));
            }
            return true;
        }
        if(plane0.testPoint(ray0.origin) == PlaneSide.OnPlane) {
            if(vector30 != null) {
                vector30.set(ray0.origin);
            }
            return true;
        }
        return false;
    }

    public static float intersectRayRay(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
        float f = vector21.x * vector23.y - vector21.y * vector23.x;
        return f == 0.0f ? Infinityf : (vector22.x - vector20.x) * (vector23.y / f) - (vector22.y - vector20.y) * (vector23.x / f);
    }

    public static boolean intersectRaySphere(Ray ray0, Vector3 vector30, float f, Vector3 vector31) {
        float f1 = ray0.direction.dot(vector30.x - ray0.origin.x, vector30.y - ray0.origin.y, vector30.z - ray0.origin.z);
        if(f1 < 0.0f) {
            return false;
        }
        float f2 = vector30.dst2(ray0.origin.x + ray0.direction.x * f1, ray0.origin.y + ray0.direction.y * f1, ray0.origin.z + ray0.direction.z * f1);
        float f3 = f * f;
        if(f2 > f3) {
            return false;
        }
        if(vector31 != null) {
            vector31.set(ray0.direction).scl(f1 - ((float)Math.sqrt(f3 - f2))).add(ray0.origin);
        }
        return true;
    }

    public static boolean intersectRayTriangle(Ray ray0, Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        Vector3 vector34 = Intersector.v0.set(vector31).sub(vector30);
        Vector3 vector35 = Intersector.v1.set(vector32).sub(vector30);
        Vector3 vector36 = Intersector.v2.set(ray0.direction).crs(vector35);
        float f = vector34.dot(vector36);
        if(MathUtils.isZero(f)) {
            Intersector.p.set(vector30, vector31, vector32);
            if(Intersector.p.testPoint(ray0.origin) == PlaneSide.OnPlane && Intersector.isPointInTriangle(ray0.origin, vector30, vector31, vector32)) {
                if(vector33 != null) {
                    vector33.set(ray0.origin);
                }
                return true;
            }
            return false;
        }
        Vector3 vector37 = Intersector.i.set(ray0.origin).sub(vector30);
        float f1 = vector37.dot(vector36) * (1.0f / f);
        if(f1 >= 0.0f && f1 <= 1.0f) {
            Vector3 vector38 = vector37.crs(vector34);
            float f2 = ray0.direction.dot(vector38) * (1.0f / f);
            if(f2 >= 0.0f && f1 + f2 <= 1.0f) {
                float f3 = vector35.dot(vector38) * (1.0f / f);
                if(f3 < 0.0f) {
                    return false;
                }
                if(vector33 != null) {
                    if(f3 <= 0.000001f) {
                        vector33.set(ray0.origin);
                        return true;
                    }
                    ray0.getEndPoint(vector33, f3);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean intersectRayTriangles(Ray ray0, List list0, Vector3 vector30) {
        if(list0.size() % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }
        boolean z = false;
        float f = 3.402823E+38f;
        for(int v = 0; v < list0.size(); v += 3) {
            if(Intersector.intersectRayTriangle(ray0, ((Vector3)list0.get(v)), ((Vector3)list0.get(v + 1)), ((Vector3)list0.get(v + 2)), Intersector.tmp)) {
                float f1 = ray0.origin.dst2(Intersector.tmp);
                if(f1 < f) {
                    Intersector.best.set(Intersector.tmp);
                    f = f1;
                    z = true;
                }
            }
        }
        if(!z) {
            return false;
        }
        if(vector30 != null) {
            vector30.set(Intersector.best);
        }
        return true;
    }

    public static boolean intersectRayTriangles(Ray ray0, float[] arr_f, Vector3 vector30) {
        if(arr_f.length % 9 != 0) {
            throw new RuntimeException("triangles array size is not a multiple of 9");
        }
        boolean z = false;
        float f = 3.402823E+38f;
        for(int v = 0; v < arr_f.length; v += 9) {
            if(Intersector.intersectRayTriangle(ray0, Intersector.tmp1.set(arr_f[v], arr_f[v + 1], arr_f[v + 2]), Intersector.tmp2.set(arr_f[v + 3], arr_f[v + 4], arr_f[v + 5]), Intersector.tmp3.set(arr_f[v + 6], arr_f[v + 7], arr_f[v + 8]), Intersector.tmp)) {
                float f1 = ray0.origin.dst2(Intersector.tmp);
                if(f1 < f) {
                    Intersector.best.set(Intersector.tmp);
                    f = f1;
                    z = true;
                }
            }
        }
        if(!z) {
            return false;
        }
        if(vector30 != null) {
            vector30.set(Intersector.best);
        }
        return true;
    }

    public static boolean intersectRayTriangles(Ray ray0, float[] arr_f, short[] arr_v, int v, Vector3 vector30) {
        if(arr_v.length % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }
        boolean z = false;
        float f = 3.402823E+38f;
        for(int v1 = 0; v1 < arr_v.length; v1 += 3) {
            int v2 = arr_v[v1] * v;
            int v3 = arr_v[v1 + 1] * v;
            int v4 = arr_v[v1 + 2] * v;
            if(Intersector.intersectRayTriangle(ray0, Intersector.tmp1.set(arr_f[v2], arr_f[v2 + 1], arr_f[v2 + 2]), Intersector.tmp2.set(arr_f[v3], arr_f[v3 + 1], arr_f[v3 + 2]), Intersector.tmp3.set(arr_f[v4], arr_f[v4 + 1], arr_f[v4 + 2]), Intersector.tmp)) {
                float f1 = ray0.origin.dst2(Intersector.tmp);
                if(f1 < f) {
                    Intersector.best.set(Intersector.tmp);
                    f = f1;
                    z = true;
                }
            }
        }
        if(!z) {
            return false;
        }
        if(vector30 != null) {
            vector30.set(Intersector.best);
        }
        return true;
    }

    public static boolean intersectRectangles(Rectangle rectangle0, Rectangle rectangle1, Rectangle rectangle2) {
        if(rectangle0.overlaps(rectangle1)) {
            rectangle2.x = Math.max(rectangle0.x, rectangle1.x);
            rectangle2.width = Math.min(rectangle0.x + rectangle0.width, rectangle1.x + rectangle1.width) - rectangle2.x;
            rectangle2.y = Math.max(rectangle0.y, rectangle1.y);
            rectangle2.height = Math.min(rectangle0.y + rectangle0.height, rectangle1.y + rectangle1.height) - rectangle2.y;
            return true;
        }
        return false;
    }

    public static boolean intersectSegmentCircle(Vector2 vector20, Vector2 vector21, Circle circle0, MinimumTranslationVector intersector$MinimumTranslationVector0) {
        Intersector.v2a.set(vector21).sub(vector20);
        Intersector.v2b.set(circle0.x - vector20.x, circle0.y - vector20.y);
        float f = Intersector.v2a.len();
        float f1 = Intersector.v2b.dot(Intersector.v2a.nor());
        if(f1 <= 0.0f) {
            Intersector.v2c.set(vector20);
        }
        else if(f1 >= f) {
            Intersector.v2c.set(vector21);
        }
        else {
            Intersector.v2d.set(Intersector.v2a.scl(f1));
            Intersector.v2c.set(Intersector.v2d).add(vector20);
        }
        Intersector.v2a.set(Intersector.v2c.x - circle0.x, Intersector.v2c.y - circle0.y);
        if(intersector$MinimumTranslationVector0 != null) {
            if(Intersector.v2a.equals(Vector2.Zero)) {
                Intersector.v2d.set(vector21.y - vector20.y, vector20.x - vector21.x);
                intersector$MinimumTranslationVector0.normal.set(Intersector.v2d).nor();
                intersector$MinimumTranslationVector0.depth = circle0.radius;
                return Intersector.v2a.len2() <= circle0.radius * circle0.radius;
            }
            intersector$MinimumTranslationVector0.normal.set(Intersector.v2a).nor();
            intersector$MinimumTranslationVector0.depth = circle0.radius - Intersector.v2a.len();
        }
        return Intersector.v2a.len2() <= circle0.radius * circle0.radius;
    }

    public static boolean intersectSegmentCircle(Vector2 vector20, Vector2 vector21, Vector2 vector22, float f) {
        Intersector.tmp.set(vector21.x - vector20.x, vector21.y - vector20.y, 0.0f);
        Intersector.tmp1.set(vector22.x - vector20.x, vector22.y - vector20.y, 0.0f);
        float f1 = Intersector.tmp.len();
        float f2 = Intersector.tmp1.dot(Intersector.tmp.nor());
        if(f2 <= 0.0f) {
            Intersector.tmp2.set(vector20.x, vector20.y, 0.0f);
        }
        else if(f2 >= f1) {
            Intersector.tmp2.set(vector21.x, vector21.y, 0.0f);
        }
        else {
            Intersector.tmp3.set(Intersector.tmp.scl(f2));
            Intersector.tmp2.set(Intersector.tmp3.x + vector20.x, Intersector.tmp3.y + vector20.y, 0.0f);
        }
        float f3 = vector22.x - Intersector.tmp2.x;
        float f4 = vector22.y - Intersector.tmp2.y;
        return f3 * f3 + f4 * f4 <= f;
    }

    public static boolean intersectSegmentPlane(Vector3 vector30, Vector3 vector31, Plane plane0, Vector3 vector32) {
        Vector3 vector33 = Intersector.v0.set(vector31).sub(vector30);
        float f = vector33.dot(plane0.getNormal());
        if(f == 0.0f) {
            return false;
        }
        float f1 = -(vector30.dot(plane0.getNormal()) + plane0.getD()) / f;
        if(f1 >= 0.0f && f1 <= 1.0f) {
            vector32.set(vector30).add(vector33.scl(f1));
            return true;
        }
        return false;
    }

    public static boolean intersectSegmentPolygon(Vector2 vector20, Vector2 vector21, Polygon polygon0) {
        float[] arr_f = polygon0.getTransformedVertices();
        float f = vector20.x;
        float f1 = vector20.y;
        float f2 = vector21.x;
        float f3 = vector21.y;
        float f4 = arr_f[arr_f.length - 2];
        float f5 = arr_f[arr_f.length - 1];
        float f6 = f4;
        int v = 0;
        while(v < arr_f.length) {
            float f7 = arr_f[v];
            float f8 = arr_f[v + 1];
            float f9 = f8 - f5;
            float f10 = f2 - f;
            float f11 = f7 - f6;
            float f12 = f3 - f1;
            float f13 = f9 * f10 - f11 * f12;
            if(f13 != 0.0f) {
                float f14 = f1 - f5;
                float f15 = f - f6;
                float f16 = (f11 * f14 - f9 * f15) / f13;
                if(f16 >= 0.0f && f16 <= 1.0f) {
                    float f17 = (f10 * f14 - f12 * f15) / f13;
                    if(f17 >= 0.0f && f17 <= 1.0f) {
                        return true;
                    }
                }
            }
            v += 2;
            f6 = f7;
            f5 = f8;
        }
        return false;
    }

    public static boolean intersectSegmentRectangle(float f, float f1, float f2, float f3, Rectangle rectangle0) {
        float f4 = rectangle0.x + rectangle0.width;
        float f5 = rectangle0.y + rectangle0.height;
        if(Intersector.intersectSegments(f, f1, f2, f3, rectangle0.x, rectangle0.y, rectangle0.x, f5, null)) {
            return true;
        }
        if(Intersector.intersectSegments(f, f1, f2, f3, rectangle0.x, rectangle0.y, f4, rectangle0.y, null)) {
            return true;
        }
        if(Intersector.intersectSegments(f, f1, f2, f3, f4, rectangle0.y, f4, f5, null)) {
            return true;
        }
        return Intersector.intersectSegments(f, f1, f2, f3, rectangle0.x, f5, f4, f5, null) ? true : rectangle0.contains(f, f1);
    }

    public static boolean intersectSegmentRectangle(Vector2 vector20, Vector2 vector21, Rectangle rectangle0) {
        return Intersector.intersectSegmentRectangle(vector20.x, vector20.y, vector21.x, vector21.y, rectangle0);
    }

    public static boolean intersectSegments(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, Vector2 vector20) {
        float f8 = f7 - f5;
        float f9 = f2 - f;
        float f10 = f6 - f4;
        float f11 = f3 - f1;
        float f12 = f8 * f9 - f10 * f11;
        if(f12 == 0.0f) {
            return false;
        }
        float f13 = f1 - f5;
        float f14 = f - f4;
        float f15 = (f10 * f13 - f8 * f14) / f12;
        if(f15 >= 0.0f && f15 <= 1.0f) {
            float f16 = (f13 * f9 - f14 * f11) / f12;
            if(f16 >= 0.0f && f16 <= 1.0f) {
                if(vector20 != null) {
                    vector20.set(f + f9 * f15, f1 + f11 * f15);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean intersectSegments(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        float f = vector20.x;
        float f1 = vector20.y;
        float f2 = vector22.x;
        float f3 = vector22.y;
        float f4 = vector23.y - f3;
        float f5 = vector21.x - f;
        float f6 = vector23.x - f2;
        float f7 = vector21.y - f1;
        float f8 = f4 * f5 - f6 * f7;
        if(f8 == 0.0f) {
            return false;
        }
        float f9 = f1 - f3;
        float f10 = f - f2;
        float f11 = (f6 * f9 - f4 * f10) / f8;
        if(f11 >= 0.0f && f11 <= 1.0f) {
            float f12 = (f9 * f5 - f10 * f7) / f8;
            if(f12 >= 0.0f && f12 <= 1.0f) {
                if(vector24 != null) {
                    vector24.set(f + f5 * f11, f1 + f7 * f11);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isPointInPolygon(Array array0, Vector2 vector20) {
        Vector2 vector21 = (Vector2)array0.peek();
        float f = vector20.x;
        float f1 = vector20.y;
        int v = 0;
        Vector2 vector22 = vector21;
        boolean z = false;
        while(v < array0.size) {
            Vector2 vector23 = (Vector2)array0.get(v);
            if((vector23.y < f1 && vector22.y >= f1 || vector22.y < f1 && vector23.y >= f1) && vector23.x + (f1 - vector23.y) / (vector22.y - vector23.y) * (vector22.x - vector23.x) < f) {
                z = !z;
            }
            ++v;
            vector22 = vector23;
        }
        return z;
    }

    public static boolean isPointInPolygon(float[] arr_f, int v, int v1, float f, float f1) {
        float f2 = arr_f[v];
        float f3 = arr_f[v + 1];
        int v2 = v + 3;
        float f4 = f3;
        boolean z = false;
        while(v2 < v + v1) {
            float f5 = arr_f[v2];
            if(f5 < f1 && f4 >= f1 || f4 < f1 && f5 >= f1) {
                float f6 = arr_f[v2 - 1];
                if(f6 + (f1 - f5) / (f4 - f5) * (arr_f[v2 - 3] - f6) < f) {
                    z = !z;
                }
            }
            v2 += 2;
            f4 = f5;
        }
        return (f3 >= f1 || f4 < f1) && (f4 >= f1 || f3 < f1) || f2 + (f1 - f3) / (f4 - f3) * (arr_f[v2 - 3] - f2) >= f ? z : !z;
    }

    public static boolean isPointInTriangle(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f - f2;
        float f9 = f1 - f3;
        int v = (f4 - f2) * f9 - (f5 - f3) * f8 > 0.0f ? 1 : 0;
        return ((f6 - f2) * f9 - (f7 - f3) * f8 > 0.0f ? 1 : 0) == v ? false : ((f6 - f4) * (f1 - f5) - (f7 - f5) * (f - f4) > 0.0f ? 1 : 0) == v;
    }

    public static boolean isPointInTriangle(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
        float f = vector20.x - vector21.x;
        float f1 = vector20.y - vector21.y;
        int v = (vector22.x - vector21.x) * f1 - (vector22.y - vector21.y) * f > 0.0f ? 1 : 0;
        return ((vector23.x - vector21.x) * f1 - (vector23.y - vector21.y) * f > 0.0f ? 1 : 0) == v ? false : ((vector23.x - vector22.x) * (vector20.y - vector22.y) - (vector23.y - vector22.y) * (vector20.x - vector22.x) > 0.0f ? 1 : 0) == v;
    }

    public static boolean isPointInTriangle(Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        Intersector.v0.set(vector31).sub(vector30);
        Intersector.v1.set(vector32).sub(vector30);
        Intersector.v2.set(vector33).sub(vector30);
        float f = Intersector.v0.dot(Intersector.v1);
        float f1 = Intersector.v0.dot(Intersector.v2);
        float f2 = Intersector.v1.dot(Intersector.v2);
        return f2 * f1 - Intersector.v2.dot(Intersector.v2) * f < 0.0f ? false : f * f2 - f1 * Intersector.v1.dot(Intersector.v1) >= 0.0f;
    }

    public static Vector2 nearestSegmentPoint(float f, float f1, float f2, float f3, float f4, float f5, Vector2 vector20) {
        float f6 = f2 - f;
        float f7 = f3 - f1;
        float f8 = f6 * f6 + f7 * f7;
        if(f8 == 0.0f) {
            return vector20.set(f, f1);
        }
        float f9 = ((f4 - f) * f6 + (f5 - f1) * f7) / f8;
        if(f9 < 0.0f) {
            return vector20.set(f, f1);
        }
        return f9 > 1.0f ? vector20.set(f2, f3) : vector20.set(f + f6 * f9, f1 + f9 * f7);
    }

    public static Vector2 nearestSegmentPoint(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
        float f = vector20.dst2(vector21);
        if(f == 0.0f) {
            return vector23.set(vector20);
        }
        float f1 = ((vector22.x - vector20.x) * (vector21.x - vector20.x) + (vector22.y - vector20.y) * (vector21.y - vector20.y)) / f;
        if(f1 < 0.0f) {
            return vector23.set(vector20);
        }
        return f1 > 1.0f ? vector23.set(vector21) : vector23.set(vector20.x + (vector21.x - vector20.x) * f1, vector20.y + f1 * (vector21.y - vector20.y));
    }

    public static boolean overlapConvexPolygons(Polygon polygon0, Polygon polygon1) {
        return Intersector.overlapConvexPolygons(polygon0, polygon1, null);
    }

    public static boolean overlapConvexPolygons(Polygon polygon0, Polygon polygon1, MinimumTranslationVector intersector$MinimumTranslationVector0) {
        return Intersector.overlapConvexPolygons(polygon0.getTransformedVertices(), polygon1.getTransformedVertices(), intersector$MinimumTranslationVector0);
    }

    public static boolean overlapConvexPolygons(float[] arr_f, int v, int v1, float[] arr_f1, int v2, int v3, MinimumTranslationVector intersector$MinimumTranslationVector0) {
        if(intersector$MinimumTranslationVector0 != null) {
            intersector$MinimumTranslationVector0.depth = 3.402823E+38f;
            intersector$MinimumTranslationVector0.normal.setZero();
        }
        boolean z = Intersector.overlapsOnAxisOfShape(arr_f1, v2, v3, arr_f, v, v1, intersector$MinimumTranslationVector0, true);
        if(z) {
            z = Intersector.overlapsOnAxisOfShape(arr_f, v, v1, arr_f1, v2, v3, intersector$MinimumTranslationVector0, false);
        }
        if(!z) {
            if(intersector$MinimumTranslationVector0 != null) {
                intersector$MinimumTranslationVector0.depth = 0.0f;
                intersector$MinimumTranslationVector0.normal.setZero();
            }
            return false;
        }
        return true;
    }

    public static boolean overlapConvexPolygons(float[] arr_f, float[] arr_f1, MinimumTranslationVector intersector$MinimumTranslationVector0) {
        return Intersector.overlapConvexPolygons(arr_f, 0, arr_f.length, arr_f1, 0, arr_f1.length, intersector$MinimumTranslationVector0);
    }

    public static boolean overlaps(Circle circle0, Circle circle1) {
        return circle0.overlaps(circle1);
    }

    public static boolean overlaps(Circle circle0, Rectangle rectangle0) {
        float f = circle0.x;
        float f1 = circle0.y;
        if(circle0.x < rectangle0.x) {
            f = rectangle0.x;
        }
        else if(circle0.x > rectangle0.x + rectangle0.width) {
            f = rectangle0.x + rectangle0.width;
        }
        if(circle0.y < rectangle0.y) {
            f1 = rectangle0.y;
        }
        else if(circle0.y > rectangle0.y + rectangle0.height) {
            f1 = rectangle0.y + rectangle0.height;
        }
        float f2 = f - circle0.x;
        float f3 = f1 - circle0.y;
        return f2 * f2 + f3 * f3 < circle0.radius * circle0.radius;
    }

    public static boolean overlaps(Rectangle rectangle0, Rectangle rectangle1) {
        return rectangle0.overlaps(rectangle1);
    }

    private static boolean overlapsOnAxisOfShape(float[] arr_f, int v, int v1, float[] arr_f1, int v2, int v3, MinimumTranslationVector intersector$MinimumTranslationVector0, boolean z) {
        float f16;
        int v4 = v + v1;
        int v5 = v;
        while(true) {
            boolean z1 = true;
            if(v5 >= v4) {
                break;
            }
            float f = arr_f[v5];
            float f1 = arr_f[v5 + 1];
            float f2 = arr_f[(v5 + 2) % v1];
            float f3 = f1 - arr_f[(v5 + 3) % v1];
            float f4 = -(f - f2);
            float f5 = (float)Math.sqrt(f3 * f3 + f4 * f4);
            float f6 = f3 / f5;
            float f7 = f4 / f5;
            float f8 = 3.402823E+38f;
            int v6 = v;
            float f9 = 3.402823E+38f;
            float f10 = -3.402823E+38f;
            while(v6 < v4) {
                float f11 = arr_f[v6] * f6 + arr_f[v6 + 1] * f7;
                f9 = Math.min(f9, f11);
                f10 = Math.max(f10, f11);
                v6 += 2;
            }
            int v7 = v2;
            float f12 = -3.402823E+38f;
            while(v7 < v2 + v3) {
                float f13 = arr_f1[v7] * f6 + arr_f1[v7 + 1] * f7;
                f8 = Math.min(f8, f13);
                f12 = Math.max(f12, f13);
                v7 += 2;
            }
            if(f10 >= f8 && f12 >= f9) {
                if(intersector$MinimumTranslationVector0 != null) {
                    float f14 = Math.min(f10, f12) - Math.max(f9, f8);
                    boolean z2 = f9 < f8 && f10 > f12;
                    boolean z3 = f8 < f9 && f12 > f10;
                    float f15 = 0.0f;
                    if(z2 || z3) {
                        f15 = Math.abs(f9 - f8);
                        f16 = Math.abs(f10 - f12);
                        f14 += Math.min(f15, f16);
                    }
                    else {
                        f16 = 0.0f;
                    }
                    if(intersector$MinimumTranslationVector0.depth > f14) {
                        intersector$MinimumTranslationVector0.depth = f14;
                        if(z) {
                            boolean z4 = f9 < f8;
                            if(!z4) {
                                f6 = -f6;
                            }
                            if(!z4) {
                                f7 = -f7;
                            }
                        }
                        else {
                            boolean z5 = f9 > f8;
                            if(!z5) {
                                f6 = -f6;
                            }
                            if(!z5) {
                                f7 = -f7;
                            }
                        }
                        if(z2 || z3) {
                            if(f15 <= f16) {
                                f6 = -f6;
                                z1 = false;
                            }
                            if(!z1) {
                                f7 = -f7;
                            }
                        }
                        intersector$MinimumTranslationVector0.normal.set(f6, f7);
                    }
                }
                v5 += 2;
                continue;
            }
            return false;
        }
        return true;
    }

    public static int pointLineSide(float f, float f1, float f2, float f3, float f4, float f5) {
        return (int)Math.signum((f2 - f) * (f5 - f1) - (f3 - f1) * (f4 - f));
    }

    public static int pointLineSide(Vector2 vector20, Vector2 vector21, Vector2 vector22) {
        return (int)Math.signum((vector21.x - vector20.x) * (vector22.y - vector20.y) - (vector21.y - vector20.y) * (vector22.x - vector20.x));
    }

    private static void splitEdge(float[] arr_f, int v, int v1, int v2, Plane plane0, float[] arr_f1, int v3) {
        float f = Intersector.intersectLinePlane(arr_f[v], arr_f[v + 1], arr_f[v + 2], arr_f[v1], arr_f[v1 + 1], arr_f[v1 + 2], plane0, Intersector.intersection);
        arr_f1[v3] = Intersector.intersection.x;
        arr_f1[v3 + 1] = Intersector.intersection.y;
        arr_f1[v3 + 2] = Intersector.intersection.z;
        for(int v4 = 3; v4 < v2; ++v4) {
            float f1 = arr_f[v + v4];
            arr_f1[v3 + v4] = f1 + (arr_f[v1 + v4] - f1) * f;
        }
    }

    public static void splitTriangle(float[] arr_f, Plane plane0, SplitTriangle intersector$SplitTriangle0) {
        int v6;
        int v4;
        int v = arr_f.length / 3;
        int v1 = plane0.testPoint(arr_f[0], arr_f[1], arr_f[2]) == PlaneSide.Back ? 1 : 0;
        int v2 = plane0.testPoint(arr_f[v], arr_f[v + 1], arr_f[v + 2]) == PlaneSide.Back ? 1 : 0;
        int v3 = plane0.testPoint(arr_f[v * 2], arr_f[v * 2 + 1], arr_f[v * 2 + 2]) == PlaneSide.Back ? 1 : 0;
        intersector$SplitTriangle0.reset();
        if(v1 == v2 && v2 == v3) {
            intersector$SplitTriangle0.total = 1;
            if(v1 != 0) {
                intersector$SplitTriangle0.numBack = 1;
                System.arraycopy(arr_f, 0, intersector$SplitTriangle0.back, 0, arr_f.length);
                return;
            }
            intersector$SplitTriangle0.numFront = 1;
            System.arraycopy(arr_f, 0, intersector$SplitTriangle0.front, 0, arr_f.length);
            return;
        }
        intersector$SplitTriangle0.total = 3;
        intersector$SplitTriangle0.numFront = (v1 ^ 1) + (v2 ^ 1) + (v3 ^ 1);
        intersector$SplitTriangle0.numBack = 3 - intersector$SplitTriangle0.numFront;
        intersector$SplitTriangle0.setSide(((boolean)(v1 ^ 1)));
        if(v1 == v2) {
            v4 = v * 2;
            intersector$SplitTriangle0.add(arr_f, 0, v);
        }
        else {
            v4 = v * 2;
            Intersector.splitEdge(arr_f, 0, v, v, plane0, intersector$SplitTriangle0.edgeSplit, 0);
            intersector$SplitTriangle0.add(arr_f, 0, v);
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
            intersector$SplitTriangle0.setSide(!intersector$SplitTriangle0.getSide());
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
        }
        int v5 = v + v;
        if(v2 == v3) {
            v6 = v5;
            intersector$SplitTriangle0.add(arr_f, v, v);
        }
        else {
            v6 = v5;
            Intersector.splitEdge(arr_f, v, v5, v, plane0, intersector$SplitTriangle0.edgeSplit, 0);
            intersector$SplitTriangle0.add(arr_f, v, v);
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
            intersector$SplitTriangle0.setSide(!intersector$SplitTriangle0.getSide());
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
        }
        if(v3 == v1) {
            intersector$SplitTriangle0.add(arr_f, v6, v);
        }
        else {
            Intersector.splitEdge(arr_f, v6, 0, v, plane0, intersector$SplitTriangle0.edgeSplit, 0);
            intersector$SplitTriangle0.add(arr_f, v6, v);
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
            intersector$SplitTriangle0.setSide(!intersector$SplitTriangle0.getSide());
            intersector$SplitTriangle0.add(intersector$SplitTriangle0.edgeSplit, 0, v);
        }
        if(intersector$SplitTriangle0.numFront == 2) {
            System.arraycopy(intersector$SplitTriangle0.front, v4, intersector$SplitTriangle0.front, v * 3, v4);
            System.arraycopy(intersector$SplitTriangle0.front, 0, intersector$SplitTriangle0.front, v * 5, v);
            return;
        }
        System.arraycopy(intersector$SplitTriangle0.back, v4, intersector$SplitTriangle0.back, v * 3, v4);
        System.arraycopy(intersector$SplitTriangle0.back, 0, intersector$SplitTriangle0.back, v * 5, v);
    }
}

