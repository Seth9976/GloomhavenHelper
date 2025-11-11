package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;

public class EarClippingTriangulator {
    private static final int CONCAVE = -1;
    private static final int CONVEX = 1;
    private short[] indices;
    private final ShortArray indicesArray;
    private final ShortArray triangles;
    private int vertexCount;
    private final IntArray vertexTypes;
    private float[] vertices;

    public EarClippingTriangulator() {
        this.indicesArray = new ShortArray();
        this.vertexTypes = new IntArray();
        this.triangles = new ShortArray();
    }

    private int classifyVertex(int v) {
        short[] arr_v = this.indices;
        int v1 = arr_v[this.previousIndex(v)] * 2;
        int v2 = arr_v[v] * 2;
        int v3 = arr_v[this.nextIndex(v)] * 2;
        return EarClippingTriangulator.computeSpannedAreaSign(this.vertices[v1], this.vertices[v1 + 1], this.vertices[v2], this.vertices[v2 + 1], this.vertices[v3], this.vertices[v3 + 1]);
    }

    private static int computeSpannedAreaSign(float f, float f1, float f2, float f3, float f4, float f5) {
        return (int)Math.signum(f * (f5 - f3) + f2 * (f1 - f5) + f4 * (f3 - f1));
    }

    public ShortArray computeTriangles(FloatArray floatArray0) {
        return this.computeTriangles(floatArray0.items, 0, floatArray0.size);
    }

    public ShortArray computeTriangles(float[] arr_f) {
        return this.computeTriangles(arr_f, 0, arr_f.length);
    }

    public ShortArray computeTriangles(float[] arr_f, int v, int v1) {
        this.vertices = arr_f;
        this.vertexCount = v1 / 2;
        this.indicesArray.clear();
        this.indicesArray.ensureCapacity(v1 / 2);
        this.indicesArray.size = v1 / 2;
        short[] arr_v = this.indicesArray.items;
        this.indices = arr_v;
        if(GeometryUtils.isClockwise(arr_f, v, v1)) {
            for(int v2 = 0; v2 < v1 / 2; v2 = (short)(v2 + 1)) {
                arr_v[v2] = (short)(v / 2 + v2);
            }
        }
        else {
            for(int v3 = 0; v3 < v1 / 2; ++v3) {
                arr_v[v3] = (short)(v / 2 + (v1 / 2 - 1) - v3);
            }
        }
        IntArray intArray0 = this.vertexTypes;
        intArray0.clear();
        intArray0.ensureCapacity(v1 / 2);
        for(int v4 = 0; v4 < v1 / 2; ++v4) {
            intArray0.add(this.classifyVertex(v4));
        }
        this.triangles.clear();
        this.triangles.ensureCapacity(Math.max(0, v1 / 2 - 2) * 3);
        this.triangulate();
        return this.triangles;
    }

    private void cutEarTip(int v) {
        short[] arr_v = this.indices;
        int v1 = this.previousIndex(v);
        this.triangles.add(arr_v[v1]);
        this.triangles.add(arr_v[v]);
        int v2 = this.nextIndex(v);
        this.triangles.add(arr_v[v2]);
        this.indicesArray.removeIndex(v);
        this.vertexTypes.removeIndex(v);
        --this.vertexCount;
    }

    private int findEarTip() {
        int v = this.vertexCount;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.isEarTip(v1)) {
                return v1;
            }
        }
        int[] arr_v = this.vertexTypes.items;
        for(int v2 = 0; v2 < v; ++v2) {
            if(arr_v[v2] != -1) {
                return v2;
            }
        }
        return 0;
    }

    private boolean isEarTip(int v) {
        int[] arr_v = this.vertexTypes.items;
        if(arr_v[v] == -1) {
            return false;
        }
        int v1 = this.previousIndex(v);
        int v2 = this.nextIndex(v);
        short[] arr_v1 = this.indices;
        int v3 = arr_v1[v1] * 2;
        int v4 = arr_v1[v] * 2;
        int v5 = arr_v1[v2] * 2;
        float[] arr_f = this.vertices;
        float f = arr_f[v3];
        float f1 = arr_f[v3 + 1];
        float f2 = arr_f[v4];
        float f3 = arr_f[v4 + 1];
        float f4 = arr_f[v5];
        float f5 = arr_f[v5 + 1];
        for(int v6 = this.nextIndex(v2); v6 != v1; v6 = this.nextIndex(v6)) {
            if(arr_v[v6] != 1) {
                int v7 = arr_v1[v6] * 2;
                float f6 = arr_f[v7];
                float f7 = arr_f[v7 + 1];
                if(EarClippingTriangulator.computeSpannedAreaSign(f4, f5, f, f1, f6, f7) >= 0 && EarClippingTriangulator.computeSpannedAreaSign(f, f1, f2, f3, f6, f7) >= 0 && EarClippingTriangulator.computeSpannedAreaSign(f2, f3, f4, f5, f6, f7) >= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int nextIndex(int v) {
        return (v + 1) % this.vertexCount;
    }

    private int previousIndex(int v) {
        if(v == 0) {
            v = this.vertexCount;
        }
        return v - 1;
    }

    private void triangulate() {
        int[] arr_v = this.vertexTypes.items;
        int v;
        while((v = this.vertexCount) > 3) {
            int v1 = this.findEarTip();
            this.cutEarTip(v1);
            int v2 = this.previousIndex(v1);
            if(v1 == this.vertexCount) {
                v1 = 0;
            }
            arr_v[v2] = this.classifyVertex(v2);
            arr_v[v1] = this.classifyVertex(v1);
        }
        if(v == 3) {
            short[] arr_v1 = this.indices;
            this.triangles.add(arr_v1[0]);
            this.triangles.add(arr_v1[1]);
            this.triangles.add(arr_v1[2]);
        }
    }
}

