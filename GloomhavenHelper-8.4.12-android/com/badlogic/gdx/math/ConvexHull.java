package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

public class ConvexHull {
    private final FloatArray hull;
    private final IntArray indices;
    private final ShortArray originalIndices;
    private final IntArray quicksortStack;
    private float[] sortedPoints;

    public ConvexHull() {
        this.quicksortStack = new IntArray();
        this.hull = new FloatArray();
        this.indices = new IntArray();
        this.originalIndices = new ShortArray(false, 0);
    }

    private float ccw(float f, float f1) {
        int v = this.hull.size;
        float f2 = this.hull.get(v - 4);
        float f3 = this.hull.get(v - 3);
        return (this.hull.get(v - 2) - f2) * (f1 - f3) - (this.hull.peek() - f3) * (f - f2);
    }

    public IntArray computeIndices(FloatArray floatArray0, boolean z, boolean z1) {
        return this.computeIndices(floatArray0.items, 0, floatArray0.size, z, z1);
    }

    public IntArray computeIndices(float[] arr_f, int v, int v1, boolean z, boolean z1) {
        if(v1 > 0x7FFF) {
            throw new IllegalArgumentException("count must be <= 32767");
        }
        int v2 = v + v1;
        if(!z) {
            if(this.sortedPoints == null || this.sortedPoints.length < v1) {
                this.sortedPoints = new float[v1];
            }
            System.arraycopy(arr_f, v, this.sortedPoints, 0, v1);
            arr_f = this.sortedPoints;
            this.sortWithIndices(arr_f, v1, z1);
            v = 0;
        }
        IntArray intArray0 = this.indices;
        intArray0.clear();
        FloatArray floatArray0 = this.hull;
        floatArray0.clear();
        int v4 = v / 2;
        int v5 = v;
        while(v5 < v2) {
            float f = arr_f[v5];
            float f1 = arr_f[v5 + 1];
            while(floatArray0.size >= 4 && this.ccw(f, f1) <= 0.0f) {
                floatArray0.size -= 2;
                --intArray0.size;
            }
            floatArray0.add(f);
            floatArray0.add(f1);
            intArray0.add(v4);
            v5 += 2;
            ++v4;
        }
        int v6 = v2 - 4;
        int v7 = v6 / 2;
        int v8 = floatArray0.size + 2;
        while(v6 >= v) {
            float f2 = arr_f[v6];
            float f3 = arr_f[v6 + 1];
            while(floatArray0.size >= v8 && this.ccw(f2, f3) <= 0.0f) {
                floatArray0.size -= 2;
                --intArray0.size;
            }
            floatArray0.add(f2);
            floatArray0.add(f3);
            intArray0.add(v7);
            v6 -= 2;
            --v7;
        }
        if(!z) {
            short[] arr_v = this.originalIndices.items;
            int[] arr_v1 = intArray0.items;
            int v9 = intArray0.size;
            for(int v3 = 0; v3 < v9; ++v3) {
                arr_v1[v3] = arr_v[arr_v1[v3]];
            }
        }
        return intArray0;
    }

    public IntArray computeIndices(float[] arr_f, boolean z, boolean z1) {
        return this.computeIndices(arr_f, 0, arr_f.length, z, z1);
    }

    public FloatArray computePolygon(FloatArray floatArray0, boolean z) {
        return this.computePolygon(floatArray0.items, 0, floatArray0.size, z);
    }

    public FloatArray computePolygon(float[] arr_f, int v, int v1, boolean z) {
        int v2 = v + v1;
        if(!z) {
            if(this.sortedPoints == null || this.sortedPoints.length < v1) {
                this.sortedPoints = new float[v1];
            }
            System.arraycopy(arr_f, v, this.sortedPoints, 0, v1);
            arr_f = this.sortedPoints;
            this.sort(arr_f, v1);
            v = 0;
        }
        FloatArray floatArray0 = this.hull;
        floatArray0.clear();
        for(int v3 = v; v3 < v2; v3 += 2) {
            float f = arr_f[v3];
            float f1 = arr_f[v3 + 1];
            while(floatArray0.size >= 4 && this.ccw(f, f1) <= 0.0f) {
                floatArray0.size -= 2;
            }
            floatArray0.add(f);
            floatArray0.add(f1);
        }
        int v4 = v2 - 4;
        int v5 = floatArray0.size + 2;
        while(v4 >= v) {
            float f2 = arr_f[v4];
            float f3 = arr_f[v4 + 1];
            while(floatArray0.size >= v5 && this.ccw(f2, f3) <= 0.0f) {
                floatArray0.size -= 2;
            }
            floatArray0.add(f2);
            floatArray0.add(f3);
            v4 -= 2;
        }
        return floatArray0;
    }

    public FloatArray computePolygon(float[] arr_f, boolean z) {
        return this.computePolygon(arr_f, 0, arr_f.length, z);
    }

    private int quicksortPartition(float[] arr_f, int v, int v1) {
        float f = arr_f[v];
        float f1 = arr_f[v + 1];
        int v2 = v1;
        int v3 = v;
        while(v3 < v2) {
            while(v3 < v2 && arr_f[v3] <= f) {
                v3 += 2;
            }
            while(arr_f[v2] > f || arr_f[v2] == f && arr_f[v2 + 1] < f1) {
                v2 -= 2;
            }
            if(v3 < v2) {
                float f2 = arr_f[v3];
                arr_f[v3] = arr_f[v2];
                arr_f[v2] = f2;
                float f3 = arr_f[v3 + 1];
                arr_f[v3 + 1] = arr_f[v2 + 1];
                arr_f[v2 + 1] = f3;
            }
        }
        if(f > arr_f[v2] || f == arr_f[v2] && f1 < arr_f[v2 + 1]) {
            arr_f[v] = arr_f[v2];
            arr_f[v2] = f;
            arr_f[v + 1] = arr_f[v2 + 1];
            arr_f[v2 + 1] = f1;
        }
        return v2;
    }

    private int quicksortPartitionWithIndices(float[] arr_f, int v, int v1, boolean z, short[] arr_v) {
        float f = arr_f[v];
        float f1 = arr_f[v + 1];
        int v2 = v1;
        int v3 = v;
        while(v3 < v2) {
            while(v3 < v2 && arr_f[v3] <= f) {
                v3 += 2;
            }
            if(z) {
                while(arr_f[v2] > f || arr_f[v2] == f && arr_f[v2 + 1] < f1) {
                    v2 -= 2;
                }
            }
            else {
                while(arr_f[v2] > f || arr_f[v2] == f && arr_f[v2 + 1] > f1) {
                    v2 -= 2;
                }
            }
            if(v3 < v2) {
                float f2 = arr_f[v3];
                arr_f[v3] = arr_f[v2];
                arr_f[v2] = f2;
                float f3 = arr_f[v3 + 1];
                arr_f[v3 + 1] = arr_f[v2 + 1];
                arr_f[v2 + 1] = f3;
                short v4 = arr_v[v3 / 2];
                arr_v[v3 / 2] = arr_v[v2 / 2];
                arr_v[v2 / 2] = v4;
            }
        }
        if(f > arr_f[v2]) {
        label_33:
            arr_f[v] = arr_f[v2];
            arr_f[v2] = f;
            arr_f[v + 1] = arr_f[v2 + 1];
            arr_f[v2 + 1] = f1;
            short v5 = arr_v[v / 2];
            arr_v[v / 2] = arr_v[v2 / 2];
            arr_v[v2 / 2] = v5;
        }
        else if(f == arr_f[v2]) {
            if(!z) {
                if(f1 > arr_f[v2 + 1]) {
                    goto label_33;
                }
            }
            else if(f1 < arr_f[v2 + 1]) {
                goto label_33;
            }
        }
        return v2;
    }

    private void sort(float[] arr_f, int v) {
        IntArray intArray0 = this.quicksortStack;
        intArray0.add(0);
        intArray0.add(v - 2);
        while(intArray0.size > 0) {
            int v1 = intArray0.pop();
            int v2 = intArray0.pop();
            if(v1 > v2) {
                int v3 = this.quicksortPartition(arr_f, v2, v1);
                int v4 = v3 - v2;
                int v5 = v1 - v3;
                if(v4 > v5) {
                    intArray0.add(v2);
                    intArray0.add(v3 - 2);
                }
                intArray0.add(v3 + 2);
                intArray0.add(v1);
                if(v5 >= v4) {
                    intArray0.add(v2);
                    intArray0.add(v3 - 2);
                }
            }
        }
    }

    private void sortWithIndices(float[] arr_f, int v, boolean z) {
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
                int v4 = this.quicksortPartitionWithIndices(arr_f, v3, v2, z, arr_v);
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
}

