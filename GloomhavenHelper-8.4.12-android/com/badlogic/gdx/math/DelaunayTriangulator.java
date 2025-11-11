package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

public class DelaunayTriangulator {
    private static final int COMPLETE = 1;
    private static final float EPSILON = 0.000001f;
    private static final int INCOMPLETE = 2;
    private static final int INSIDE;
    private final Vector2 centroid;
    private final BooleanArray complete;
    private final IntArray edges;
    private final ShortArray originalIndices;
    private final IntArray quicksortStack;
    private float[] sortedPoints;
    private final float[] superTriangle;
    private final ShortArray triangles;

    public DelaunayTriangulator() {
        this.quicksortStack = new IntArray();
        this.triangles = new ShortArray(false, 16);
        this.originalIndices = new ShortArray(false, 0);
        this.edges = new IntArray();
        this.complete = new BooleanArray(false, 16);
        this.superTriangle = new float[6];
        this.centroid = new Vector2();
    }

    private int circumCircle(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f11;
        float f10;
        float f8 = Math.abs(f5 - f7);
        if(Math.abs(f3 - f5) < 0.000001f) {
            if(f8 < 0.000001f) {
                return 2;
            }
            float f9 = (f2 + f4) / 2.0f;
            f10 = -(f6 - f4) / (f7 - f5) * (f9 - (f6 + f4) / 2.0f) + (f7 + f5) / 2.0f;
            f11 = f9;
        }
        else {
            float f12 = -(f4 - f2) / (f5 - f3);
            float f13 = (f2 + f4) / 2.0f;
            float f14 = (f3 + f5) / 2.0f;
            if(f8 < 0.000001f) {
                f11 = (f6 + f4) / 2.0f;
            }
            else {
                float f15 = -(f6 - f4) / (f7 - f5);
                f11 = (f12 * f13 - (f6 + f4) / 2.0f * f15 + (f7 + f5) / 2.0f - f14) / (f12 - f15);
            }
            f10 = f14 + f12 * (f11 - f13);
        }
        float f16 = (f4 - f11) * (f4 - f11) + (f5 - f10) * (f5 - f10);
        float f17 = (f - f11) * (f - f11);
        if((f1 - f10) * (f1 - f10) + f17 - f16 <= 0.000001f) {
            return 0;
        }
        return f <= f11 || f17 <= f16 ? 2 : 1;
    }

    public ShortArray computeTriangles(FloatArray floatArray0, boolean z) {
        return this.computeTriangles(floatArray0.items, 0, floatArray0.size, z);
    }

    public ShortArray computeTriangles(float[] arr_f, int v, int v1, boolean z) {
        int v22;
        int v17;
        float f21;
        float f20;
        float f18;
        float f17;
        float f15;
        float f14;
        IntArray intArray1;
        int v10;
        int v9;
        float[] arr_f5;
        int v8;
        ShortArray shortArray1;
        float[] arr_f4;
        BooleanArray booleanArray1;
        int v7;
        short[] arr_v1;
        boolean[] arr_z1;
        int v2;
        float[] arr_f2;
        if(v1 > 0x7FFF) {
            throw new IllegalArgumentException("count must be <= 32767");
        }
        ShortArray shortArray0 = this.triangles;
        shortArray0.clear();
        if(v1 < 6) {
            return shortArray0;
        }
        shortArray0.ensureCapacity(v1);
        if(z) {
            arr_f2 = arr_f;
            v2 = v;
        }
        else {
            if(this.sortedPoints == null || this.sortedPoints.length < v1) {
                this.sortedPoints = new float[v1];
            }
            System.arraycopy(arr_f, v, this.sortedPoints, 0, v1);
            float[] arr_f1 = this.sortedPoints;
            this.sort(arr_f1, v1);
            arr_f2 = arr_f1;
            v2 = 0;
        }
        int v3 = v2 + v1;
        float f = arr_f2[0];
        int v4 = v2 + 2;
        float f1 = arr_f2[1];
        float f2 = f1;
        float f3 = f;
        while(v4 < v3) {
            float f4 = arr_f2[v4];
            if(f4 < f3) {
                f3 = f4;
            }
            if(f4 > f) {
                f = f4;
            }
            float f5 = arr_f2[v4 + 1];
            if(f5 < f2) {
                f2 = f5;
            }
            if(f5 > f1) {
                f1 = f5;
            }
            v4 += 2;
        }
        float f6 = f - f3;
        float f7 = f1 - f2;
        if(f6 <= f7) {
            f6 = f7;
        }
        float f8 = (f + f3) / 2.0f;
        float f9 = (f1 + f2) / 2.0f;
        float[] arr_f3 = this.superTriangle;
        arr_f3[0] = f8 - f6 * 20.0f;
        float f10 = f9 - f6 * 20.0f;
        arr_f3[1] = f10;
        arr_f3[2] = f8;
        arr_f3[3] = f9 + f6 * 20.0f;
        arr_f3[4] = f8 + f6 * 20.0f;
        arr_f3[5] = f10;
        IntArray intArray0 = this.edges;
        intArray0.ensureCapacity(v1 / 2);
        BooleanArray booleanArray0 = this.complete;
        booleanArray0.clear();
        booleanArray0.ensureCapacity(v1);
        shortArray0.add(v3);
        shortArray0.add(v3 + 2);
        shortArray0.add(v3 + 4);
        booleanArray0.add(false);
        for(int v5 = v2; v5 < v3; v5 = v18 + 2) {
            float f11 = arr_f2[v5];
            float f12 = arr_f2[v5 + 1];
            short[] arr_v = shortArray0.items;
            boolean[] arr_z = booleanArray0.items;
            int v6 = shortArray0.size - 1;
            while(v6 >= 0) {
                if(arr_z[v6 / 3]) {
                    arr_z1 = arr_z;
                    arr_v1 = arr_v;
                    v7 = v5;
                    booleanArray1 = booleanArray0;
                    arr_f4 = arr_f3;
                    shortArray1 = shortArray0;
                    v8 = v2;
                    arr_f5 = arr_f2;
                    v9 = v3;
                    v10 = v6;
                    intArray1 = intArray0;
                }
                else {
                    int v11 = arr_v[v6 - 2];
                    int v12 = arr_v[v6 - 1];
                    v8 = v2;
                    int v13 = arr_v[v6];
                    if(v11 >= v3) {
                        int v14 = v11 - v3;
                        float f13 = arr_f3[v14];
                        f14 = arr_f3[v14 + 1];
                        f15 = f13;
                    }
                    else {
                        f15 = arr_f2[v11];
                        f14 = arr_f2[v11 + 1];
                    }
                    if(v12 >= v3) {
                        int v15 = v12 - v3;
                        float f16 = arr_f3[v15];
                        f17 = arr_f3[v15 + 1];
                        f18 = f16;
                    }
                    else {
                        f18 = arr_f2[v12];
                        f17 = arr_f2[v12 + 1];
                    }
                    if(v13 >= v3) {
                        int v16 = v13 - v3;
                        float f19 = arr_f3[v16];
                        f20 = arr_f3[v16 + 1];
                        f21 = f19;
                    }
                    else {
                        f21 = arr_f2[v13];
                        f20 = arr_f2[v13 + 1];
                    }
                    arr_f5 = arr_f2;
                    v17 = v6 - 2;
                    v9 = v3;
                    v10 = v6;
                    arr_z1 = arr_z;
                    arr_v1 = arr_v;
                    v7 = v5;
                    intArray1 = intArray0;
                    arr_f4 = arr_f3;
                    switch(this.circumCircle(f11, f12, f15, f14, f18, f17, f21, f20)) {
                        case 0: {
                            intArray1.add(v11, v12, v12, v13);
                            intArray1.add(v13, v11);
                            shortArray1 = shortArray0;
                            shortArray1.removeRange(v17, v10);
                            booleanArray1 = booleanArray0;
                            booleanArray1.removeIndex(v6 / 3);
                            break;
                        }
                        case 1: {
                            arr_z1[v6 / 3] = true;
                            shortArray1 = shortArray0;
                            booleanArray1 = booleanArray0;
                            break;
                        }
                        default: {
                            shortArray1 = shortArray0;
                            booleanArray1 = booleanArray0;
                        }
                    }
                }
                v6 = v10 - 3;
                v2 = v8;
                booleanArray0 = booleanArray1;
                intArray0 = intArray1;
                arr_v = arr_v1;
                arr_f3 = arr_f4;
                arr_f2 = arr_f5;
                v3 = v9;
                arr_z = arr_z1;
                v5 = v7;
                shortArray0 = shortArray1;
            }
            int v18 = v5;
            BooleanArray booleanArray2 = booleanArray0;
            ShortArray shortArray2 = shortArray0;
            int[] arr_v2 = intArray0.items;
            int v19 = intArray0.size;
            int v20 = 0;
            while(v20 < v19) {
                int v21 = arr_v2[v20];
                if(v21 == -1) {
                    v22 = v18;
                }
                else {
                    int v23 = arr_v2[v20 + 1];
                    int v24 = v20 + 2;
                    boolean z1 = false;
                    while(v24 < v19) {
                        if(v21 == arr_v2[v24 + 1] && v23 == arr_v2[v24]) {
                            arr_v2[v24] = -1;
                            z1 = true;
                        }
                        v24 += 2;
                    }
                    if(z1) {
                        v22 = v18;
                    }
                    else {
                        shortArray2.add(v21);
                        shortArray2.add(arr_v2[v20 + 1]);
                        v22 = v18;
                        shortArray2.add(v22);
                        booleanArray2.add(false);
                    }
                }
                v20 += 2;
                v18 = v22;
            }
            intArray0.clear();
        }
        int v25 = 0;
        short[] arr_v3 = shortArray0.items;
        for(int v26 = shortArray0.size - 1; v26 >= 0; v26 -= 3) {
            if(arr_v3[v26] >= v3 || arr_v3[v26 - 1] >= v3 || arr_v3[v26 - 2] >= v3) {
                shortArray0.removeIndex(v26);
                shortArray0.removeIndex(v26 - 1);
                shortArray0.removeIndex(v26 - 2);
            }
        }
        if(!z) {
            short[] arr_v4 = this.originalIndices.items;
            int v27 = shortArray0.size;
            for(int v28 = 0; v28 < v27; ++v28) {
                arr_v3[v28] = (short)(arr_v4[arr_v3[v28] / 2] * 2);
            }
        }
        if(v2 == 0) {
            int v29 = shortArray0.size;
            while(v25 < v29) {
                arr_v3[v25] = (short)(arr_v3[v25] / 2);
                ++v25;
            }
            return shortArray0;
        }
        int v30 = shortArray0.size;
        while(v25 < v30) {
            arr_v3[v25] = (short)((arr_v3[v25] - v2) / 2);
            ++v25;
        }
        return shortArray0;
    }

    public ShortArray computeTriangles(float[] arr_f, boolean z) {
        return this.computeTriangles(arr_f, 0, arr_f.length, z);
    }

    private int quicksortPartition(float[] arr_f, int v, int v1, short[] arr_v) {
        float f = arr_f[v];
        int v2 = v + 2;
        while(v2 < v1) {
            while(v2 < v1 && arr_f[v2] <= f) {
                v2 += 2;
            }
            while(arr_f[v1] > f) {
                v1 -= 2;
            }
            if(v2 < v1) {
                float f1 = arr_f[v2];
                arr_f[v2] = arr_f[v1];
                arr_f[v1] = f1;
                float f2 = arr_f[v2 + 1];
                arr_f[v2 + 1] = arr_f[v1 + 1];
                arr_f[v1 + 1] = f2;
                short v3 = arr_v[v2 / 2];
                arr_v[v2 / 2] = arr_v[v1 / 2];
                arr_v[v1 / 2] = v3;
            }
        }
        if(f > arr_f[v1]) {
            arr_f[v] = arr_f[v1];
            arr_f[v1] = f;
            float f3 = arr_f[v + 1];
            arr_f[v + 1] = arr_f[v1 + 1];
            arr_f[v1 + 1] = f3;
            short v4 = arr_v[v / 2];
            arr_v[v / 2] = arr_v[v1 / 2];
            arr_v[v1 / 2] = v4;
        }
        return v1;
    }

    private void sort(float[] arr_f, int v) {
        this.originalIndices.clear();
        this.originalIndices.ensureCapacity(v / 2);
        short[] arr_v = this.originalIndices.items;
        for(short v1 = 0; v1 < v / 2; v1 = (short)(v1 + 1)) {
            arr_v[v1] = v1;
        }
        IntArray intArray0 = this.quicksortStack;
        intArray0.add(0);
        intArray0.add(v - 2);
        while(intArray0.size > 0) {
            int v2 = intArray0.pop();
            int v3 = intArray0.pop();
            if(v2 > v3) {
                int v4 = this.quicksortPartition(arr_f, v3, v2, arr_v);
                int v5 = v4 - v3;
                int v6 = v2 - v4;
                if(v5 > v6) {
                    intArray0.add(v3);
                    intArray0.add(v4 - 2);
                }
                intArray0.add(v4 + 2);
                intArray0.add(v2);
                if(v6 >= v5) {
                    intArray0.add(v3);
                    intArray0.add(v4 - 2);
                }
            }
        }
    }

    public void trim(ShortArray shortArray0, float[] arr_f, float[] arr_f1, int v, int v1) {
        short[] arr_v = shortArray0.items;
        for(int v2 = shortArray0.size - 1; v2 >= 0; v2 -= 3) {
            int v3 = arr_v[v2 - 2] * 2;
            int v4 = arr_v[v2 - 1] * 2;
            int v5 = arr_v[v2] * 2;
            GeometryUtils.triangleCentroid(arr_f[v3], arr_f[v3 + 1], arr_f[v4], arr_f[v4 + 1], arr_f[v5], arr_f[v5 + 1], this.centroid);
            if(!Intersector.isPointInPolygon(arr_f1, v, v1, this.centroid.x, this.centroid.y)) {
                shortArray0.removeIndex(v2);
                shortArray0.removeIndex(v2 - 1);
                shortArray0.removeIndex(v2 - 2);
            }
        }
    }
}

