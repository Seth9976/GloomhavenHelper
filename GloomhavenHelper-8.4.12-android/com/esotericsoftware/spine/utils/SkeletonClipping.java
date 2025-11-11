package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.attachments.ClippingAttachment;

public class SkeletonClipping {
    private ClippingAttachment clipAttachment;
    private final FloatArray clipOutput;
    private final ShortArray clippedTriangles;
    private final FloatArray clippedVertices;
    private final FloatArray clippingPolygon;
    private Array clippingPolygons;
    private final FloatArray scratch;
    private final Triangulator triangulator;

    public SkeletonClipping() {
        this.triangulator = new Triangulator();
        this.clippingPolygon = new FloatArray();
        this.clipOutput = new FloatArray(0x80);
        this.clippedVertices = new FloatArray(0x80);
        this.clippedTriangles = new ShortArray(0x80);
        this.scratch = new FloatArray();
    }

    boolean clip(float f, float f1, float f2, float f3, float f4, float f5, FloatArray floatArray0, FloatArray floatArray1) {
        float[] arr_f2;
        FloatArray floatArray3;
        FloatArray floatArray2;
        if(floatArray0.size % 4 >= 2) {
            floatArray2 = this.scratch;
            floatArray3 = floatArray1;
        }
        else {
            floatArray3 = this.scratch;
            floatArray2 = floatArray1;
        }
        floatArray3.clear();
        floatArray3.add(f);
        floatArray3.add(f1);
        floatArray3.add(f2);
        floatArray3.add(f3);
        floatArray3.add(f4);
        floatArray3.add(f5);
        floatArray3.add(f);
        floatArray3.add(f1);
        floatArray2.clear();
        float[] arr_f = floatArray0.items;
        int v = floatArray0.size - 4;
        FloatArray floatArray4 = floatArray2;
        boolean z = false;
        FloatArray floatArray5 = floatArray3;
        int v1 = 0;
        while(true) {
            float f6 = arr_f[v1];
            float f7 = arr_f[v1 + 1];
            float f8 = arr_f[v1 + 2];
            float f9 = arr_f[v1 + 3];
            float f10 = f6 - f8;
            float f11 = f7 - f9;
            float[] arr_f1 = floatArray5.items;
            int v2 = floatArray5.size - 2;
            int v3 = floatArray4.size;
            boolean z1 = z;
            int v4 = 0;
            while(v4 < v2) {
                float f12 = arr_f1[v4];
                float f13 = arr_f1[v4 + 1];
                float f14 = arr_f1[v4 + 2];
                float f15 = arr_f1[v4 + 3];
                boolean z2 = (f15 - f9) * f10 - (f14 - f8) * f11 > 0.0f;
                if((f13 - f9) * f10 - (f12 - f8) * f11 > 0.0f) {
                    if(z2) {
                        floatArray4.add(f14);
                        floatArray4.add(f15);
                        arr_f2 = arr_f;
                        goto label_78;
                    }
                    else {
                        float f16 = f15 - f13;
                        float f17 = f14 - f12;
                        float f18 = f8 - f6;
                        float f19 = f9 - f7;
                        float f20 = f16 * f18 - f17 * f19;
                        if(Math.abs(f20) > 0.000001f) {
                            float f21 = (f17 * (f7 - f13) - f16 * (f6 - f12)) / f20;
                            floatArray4.add(f6 + f18 * f21);
                            floatArray4.add(f7 + f19 * f21);
                        }
                        else {
                            floatArray4.add(f6);
                            floatArray4.add(f7);
                        }
                        goto label_76;
                    }
                    goto label_58;
                }
                else {
                label_58:
                    if(z2) {
                        float f22 = f15 - f13;
                        float f23 = f14 - f12;
                        float f24 = f8 - f6;
                        float f25 = f9 - f7;
                        float f26 = f22 * f24 - f23 * f25;
                        if(Math.abs(f26) > 0.000001f) {
                            float f27 = (f23 * (f7 - f13) - f22 * (f6 - f12)) / f26;
                            arr_f2 = arr_f;
                            floatArray4.add(f6 + f24 * f27);
                            floatArray4.add(f7 + f25 * f27);
                        }
                        else {
                            arr_f2 = arr_f;
                            floatArray4.add(f6);
                            floatArray4.add(f7);
                        }
                        floatArray4.add(f14);
                        floatArray4.add(f15);
                        goto label_77;
                    }
                }
            label_76:
                arr_f2 = arr_f;
            label_77:
                z1 = true;
            label_78:
                arr_f = arr_f2;
                v4 += 2;
            }
            if(v3 == floatArray4.size) {
                floatArray1.clear();
                return true;
            }
            floatArray4.add(floatArray4.items[0]);
            floatArray4.add(floatArray4.items[1]);
            if(v1 == v) {
                if(floatArray1 != floatArray4) {
                    floatArray1.clear();
                    floatArray1.addAll(floatArray4.items, 0, floatArray4.size - 2);
                    return z1;
                }
                floatArray1.setSize(floatArray1.size - 2);
                return z1;
            }
            floatArray5.clear();
            v1 += 2;
            z = z1;
            FloatArray floatArray6 = floatArray4;
            floatArray4 = floatArray5;
            floatArray5 = floatArray6;
        }
    }

    public void clipEnd() {
        if(this.clipAttachment == null) {
            return;
        }
        this.clipAttachment = null;
        this.clippingPolygons = null;
        this.clippedVertices.clear();
        this.clippedTriangles.clear();
        this.clippingPolygon.clear();
    }

    public void clipEnd(Slot slot0) {
        if(this.clipAttachment != null && this.clipAttachment.getEndSlot() == slot0.getData()) {
            this.clipEnd();
        }
    }

    public void clipStart(Slot slot0, ClippingAttachment clippingAttachment0) {
        if(this.clipAttachment != null) {
            return;
        }
        int v = clippingAttachment0.getWorldVerticesLength();
        if(v < 6) {
            return;
        }
        this.clipAttachment = clippingAttachment0;
        clippingAttachment0.computeWorldVertices(slot0, 0, v, this.clippingPolygon.setSize(v), 0, 2);
        SkeletonClipping.makeClockwise(this.clippingPolygon);
        ShortArray shortArray0 = this.triangulator.triangulate(this.clippingPolygon);
        this.clippingPolygons = this.triangulator.decompose(this.clippingPolygon, shortArray0);
        for(Object object0: this.clippingPolygons) {
            SkeletonClipping.makeClockwise(((FloatArray)object0));
            ((FloatArray)object0).add(((FloatArray)object0).items[0]);
            ((FloatArray)object0).add(((FloatArray)object0).items[1]);
        }
    }

    public void clipTriangles(float[] arr_f, int v, short[] arr_v, int v1, float[] arr_f1, float f, float f1, boolean z) {
        int v14;
        int v11;
        FloatArray floatArray0 = this.clipOutput;
        FloatArray floatArray1 = this.clippedVertices;
        ShortArray shortArray0 = this.clippedTriangles;
        Object[] arr_object = this.clippingPolygons.items;
        int v2 = this.clippingPolygons.size;
        floatArray1.clear();
        shortArray0.clear();
        short v3 = 0;
        int v4 = 0;
        while(v4 < v1) {
            int v5 = arr_v[v4] << 1;
            float f2 = arr_f[v5];
            float f3 = arr_f[v5 + 1];
            float f4 = arr_f1[v5];
            float f5 = arr_f1[v5 + 1];
            int v6 = arr_v[v4 + 1] << 1;
            float f6 = arr_f[v6];
            float f7 = arr_f[v6 + 1];
            float f8 = arr_f1[v6];
            float f9 = arr_f1[v6 + 1];
            int v7 = arr_v[v4 + 2] << 1;
            float f10 = arr_f[v7];
            float f11 = arr_f[v7 + 1];
            float f12 = arr_f1[v7];
            float f13 = arr_f1[v7 + 1];
            short v8 = v3;
            int v9 = 0;
            while(v9 < v2) {
                int v10 = floatArray1.size;
                v11 = v4;
                if(this.clip(f2, f3, f6, f7, f10, f11, ((FloatArray)arr_object[v9]), floatArray0)) {
                    int v12 = floatArray0.size;
                    if(v12 != 0) {
                        float f14 = f7 - f11;
                        float f15 = f10 - f6;
                        float f16 = f2 - f10;
                        float f17 = f11 - f3;
                        float f18 = 1.0f / (f14 * f16 + (f3 - f11) * f15);
                        float[] arr_f2 = floatArray0.items;
                        float[] arr_f3 = floatArray1.setSize(v10 + (v12 >> 1) * (z ? 6 : 5));
                        for(int v13 = 0; v13 < v12; v13 += 2) {
                            float f19 = arr_f2[v13];
                            float f20 = arr_f2[v13 + 1];
                            arr_f3[v10] = f19;
                            arr_f3[v10 + 1] = f20;
                            arr_f3[v10 + 2] = f;
                            if(z) {
                                arr_f3[v10 + 3] = f1;
                                v14 = v10 + 4;
                            }
                            else {
                                v14 = v10 + 3;
                            }
                            float f21 = f19 - f10;
                            float f22 = f20 - f11;
                            float f23 = (f14 * f21 + f15 * f22) * f18;
                            float f24 = (f21 * f17 + f22 * f16) * f18;
                            float f25 = 1.0f - f23 - f24;
                            arr_f3[v14] = f4 * f23 + f8 * f24 + f12 * f25;
                            arr_f3[v14 + 1] = f23 * f5 + f24 * f9 + f25 * f13;
                            v10 = v14 + 2;
                        }
                        int v15 = shortArray0.size;
                        short[] arr_v1 = shortArray0.setSize(((v12 >> 1) - 2) * 3 + v15);
                        int v16 = (v12 >> 1) - 1;
                        int v17 = v15;
                        for(int v18 = 1; v18 < v16; ++v18) {
                            arr_v1[v17] = v8;
                            int v19 = v8 + v18;
                            arr_v1[v17 + 1] = (short)v19;
                            arr_v1[v17 + 2] = (short)(v19 + 1);
                            v17 += 3;
                        }
                        v8 = (short)(v8 + (v16 + 1));
                    }
                    ++v9;
                    v4 = v11;
                    continue;
                }
                float[] arr_f4 = floatArray1.setSize(v10 + (z ? 6 : 5) * 3);
                arr_f4[v10] = f2;
                arr_f4[v10 + 1] = f3;
                arr_f4[v10 + 2] = f;
                if(z) {
                    arr_f4[v10 + 3] = f1;
                    arr_f4[v10 + 4] = f4;
                    arr_f4[v10 + 5] = f5;
                    arr_f4[v10 + 6] = f6;
                    arr_f4[v10 + 7] = f7;
                    arr_f4[v10 + 8] = f;
                    arr_f4[v10 + 9] = f1;
                    arr_f4[v10 + 10] = f8;
                    arr_f4[v10 + 11] = f9;
                    arr_f4[v10 + 12] = f10;
                    arr_f4[v10 + 13] = f11;
                    arr_f4[v10 + 14] = f;
                    arr_f4[v10 + 15] = f1;
                    arr_f4[v10 + 16] = f12;
                    arr_f4[v10 + 17] = f13;
                }
                else {
                    arr_f4[v10 + 3] = f4;
                    arr_f4[v10 + 4] = f5;
                    arr_f4[v10 + 5] = f6;
                    arr_f4[v10 + 6] = f7;
                    arr_f4[v10 + 7] = f;
                    arr_f4[v10 + 8] = f8;
                    arr_f4[v10 + 9] = f9;
                    arr_f4[v10 + 10] = f10;
                    arr_f4[v10 + 11] = f11;
                    arr_f4[v10 + 12] = f;
                    arr_f4[v10 + 13] = f12;
                    arr_f4[v10 + 14] = f13;
                }
                int v20 = shortArray0.size;
                short[] arr_v2 = shortArray0.setSize(v20 + 3);
                arr_v2[v20] = v8;
                arr_v2[v20 + 1] = (short)(v8 + 1);
                arr_v2[v20 + 2] = (short)(v8 + 2);
                v3 = (short)(v8 + 3);
                goto label_121;
            }
            v11 = v4;
            v3 = v8;
        label_121:
            v4 = v11 + 3;
        }
    }

    public ShortArray getClippedTriangles() {
        return this.clippedTriangles;
    }

    public FloatArray getClippedVertices() {
        return this.clippedVertices;
    }

    public boolean isClipping() {
        return this.clipAttachment != null;
    }

    static void makeClockwise(FloatArray floatArray0) {
        float[] arr_f = floatArray0.items;
        int v = floatArray0.size;
        float f = arr_f[v - 2] * arr_f[1] - arr_f[0] * arr_f[v - 1];
        for(int v2 = 0; v2 < v - 3; v2 += 2) {
            f += arr_f[v2] * arr_f[v2 + 3] - arr_f[v2 + 2] * arr_f[v2 + 1];
        }
        if(f < 0.0f) {
            return;
        }
        for(int v1 = 0; v1 < v >> 1; v1 += 2) {
            float f1 = arr_f[v1];
            float f2 = arr_f[v1 + 1];
            int v3 = v - 2 - v1;
            arr_f[v1] = arr_f[v3];
            arr_f[v1 + 1] = arr_f[v3 + 1];
            arr_f[v3] = f1;
            arr_f[v3 + 1] = f2;
        }
    }
}

