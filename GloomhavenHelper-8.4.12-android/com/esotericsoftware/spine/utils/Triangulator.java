package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ShortArray;

class Triangulator {
    private final Array convexPolygons;
    private final Array convexPolygonsIndices;
    private final ShortArray indicesArray;
    private final BooleanArray isConcaveArray;
    private final Pool polygonIndicesPool;
    private final Pool polygonPool;
    private final ShortArray triangles;

    Triangulator() {
        this.convexPolygons = new Array(false, 16);
        this.convexPolygonsIndices = new Array(false, 16);
        this.indicesArray = new ShortArray();
        this.isConcaveArray = new BooleanArray();
        this.triangles = new ShortArray();
        this.polygonPool = new Pool() {
            protected FloatArray newObject() {
                return new FloatArray(16);
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.polygonIndicesPool = new Pool() {
            protected ShortArray newObject() {
                return new ShortArray(16);
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
    }

    public Array decompose(FloatArray floatArray0, ShortArray shortArray0) {
        Array array7;
        int v19;
        int v18;
        Array array5;
        Array array4;
        Object[] arr_object5;
        Object[] arr_object4;
        int v12;
        Array array3;
        Array array2;
        Object[] arr_object3;
        Object[] arr_object2;
        boolean z;
        int v9;
        float[] arr_f = floatArray0.items;
        Array array0 = this.convexPolygons;
        this.polygonPool.freeAll(array0);
        array0.clear();
        Array array1 = this.convexPolygonsIndices;
        this.polygonIndicesPool.freeAll(array1);
        array1.clear();
        ShortArray shortArray1 = (ShortArray)this.polygonIndicesPool.obtain();
        shortArray1.clear();
        FloatArray floatArray1 = (FloatArray)this.polygonPool.obtain();
        floatArray1.clear();
        short[] arr_v = shortArray0.items;
        int v = shortArray0.size;
        int v1 = -1;
        ShortArray shortArray2 = shortArray1;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v) {
            int v4 = arr_v[v2] << 1;
            int v5 = arr_v[v2 + 1] << 1;
            int v6 = arr_v[v2 + 2] << 1;
            float f = arr_f[v4];
            float f1 = arr_f[v4 + 1];
            float f2 = arr_f[v5];
            float f3 = arr_f[v5 + 1];
            float f4 = arr_f[v6];
            int v7 = v5;
            float f5 = arr_f[v6 + 1];
            if(v1 == v4) {
                int v8 = floatArray1.size - 4;
                v9 = v1;
                if(Triangulator.winding(floatArray1.items[v8], floatArray1.items[v8 + 1], floatArray1.items[v8 + 2], floatArray1.items[v8 + 3], f4, f5) == v3 && Triangulator.winding(f4, f5, floatArray1.items[0], floatArray1.items[1], floatArray1.items[2], floatArray1.items[3]) == v3) {
                    floatArray1.add(f4);
                    floatArray1.add(f5);
                    shortArray2.add(v6);
                    z = true;
                    goto label_39;
                }
            }
            else {
                v9 = v1;
            }
            z = false;
        label_39:
            if(z) {
                v1 = v9;
            }
            else {
                if(floatArray1.size > 0) {
                    array0.add(floatArray1);
                    array1.add(shortArray2);
                    floatArray1 = (FloatArray)this.polygonPool.obtain();
                    shortArray2 = (ShortArray)this.polygonIndicesPool.obtain();
                }
                floatArray1.clear();
                floatArray1.add(f);
                floatArray1.add(f1);
                floatArray1.add(f2);
                floatArray1.add(f3);
                floatArray1.add(f4);
                floatArray1.add(f5);
                shortArray2.clear();
                shortArray2.add(v4);
                shortArray2.add(v7);
                shortArray2.add(v6);
                v3 = Triangulator.winding(f, f1, f2, f3, f4, f5);
                v1 = v4;
            }
            v2 += 3;
        }
        if(floatArray1.size > 0) {
            array0.add(floatArray1);
            array1.add(shortArray2);
        }
        Object[] arr_object = array1.items;
        Object[] arr_object1 = array0.items;
        int v10 = array0.size;
        int v11 = 0;
        while(v11 < v10) {
            ShortArray shortArray3 = (ShortArray)arr_object[v11];
            if(shortArray3.size != 0) {
                int v13 = shortArray3.first();
                int v14 = shortArray3.get(shortArray3.size - 1);
                FloatArray floatArray2 = (FloatArray)arr_object1[v11];
                int v15 = floatArray2.size - 4;
                float[] arr_f1 = floatArray2.items;
                float f6 = arr_f1[v15];
                float f7 = arr_f1[v15 + 1];
                float f8 = arr_f1[v15 + 2];
                float f9 = arr_f1[v15 + 3];
                float f10 = arr_f1[0];
                float f11 = arr_f1[1];
                float f12 = arr_f1[2];
                float f13 = arr_f1[3];
                int v16 = Triangulator.winding(f6, f7, f8, f9, f10, f11);
                float f14 = f9;
                float f15 = f8;
                float f16 = f7;
                float f17 = f6;
                int v17 = 0;
                while(v17 < v10) {
                    if(v17 == v11) {
                        arr_object4 = arr_object;
                        arr_object5 = arr_object1;
                        array4 = array0;
                        array5 = array1;
                        v18 = v10;
                        v19 = v17;
                    }
                    else {
                        arr_object4 = arr_object;
                        ShortArray shortArray4 = (ShortArray)arr_object[v17];
                        v18 = v10;
                        array5 = array1;
                        if(shortArray4.size == 3) {
                            int v20 = shortArray4.first();
                            int v21 = shortArray4.get(1);
                            array4 = array0;
                            int v22 = shortArray4.get(2);
                            v19 = v17;
                            FloatArray floatArray3 = (FloatArray)arr_object1[v17];
                            arr_object5 = arr_object1;
                            float f18 = floatArray3.get(floatArray3.size - 2);
                            float f19 = floatArray3.get(floatArray3.size - 1);
                            if(v20 == v13 && v21 == v14 && (Triangulator.winding(f17, f16, f15, f14, f18, f19) == v16 && Triangulator.winding(f18, f19, f10, f11, f12, f13) == v16)) {
                                floatArray3.clear();
                                shortArray4.clear();
                                floatArray2.add(f18);
                                floatArray2.add(f19);
                                shortArray3.add(v22);
                                f17 = f15;
                                f16 = f14;
                                v19 = 0;
                                f15 = f18;
                                f14 = f19;
                            }
                        }
                        else {
                            arr_object5 = arr_object1;
                            array4 = array0;
                            v19 = v17;
                        }
                    }
                    v17 = v19 + 1;
                    array0 = array4;
                    arr_object = arr_object4;
                    v10 = v18;
                    array1 = array5;
                    arr_object1 = arr_object5;
                }
            }
            arr_object2 = arr_object;
            arr_object3 = arr_object1;
            array2 = array0;
            array3 = array1;
            v12 = v10;
            ++v11;
            array0 = array2;
            arr_object = arr_object2;
            v10 = v12;
            array1 = array3;
            arr_object1 = arr_object3;
        }
        Array array6 = array1;
        int v23 = array0.size - 1;
        while(v23 >= 0) {
            FloatArray floatArray4 = (FloatArray)arr_object1[v23];
            if(floatArray4.size == 0) {
                array0.removeIndex(v23);
                this.polygonPool.free(floatArray4);
                array7 = array6;
                ShortArray shortArray5 = (ShortArray)array7.removeIndex(v23);
                this.polygonIndicesPool.free(shortArray5);
            }
            else {
                array7 = array6;
            }
            --v23;
            array6 = array7;
        }
        return array0;
    }

    private static boolean isConcave(int v, int v1, float[] arr_f, short[] arr_v) {
        int v2 = arr_v[(v1 + v - 1) % v1] << 1;
        int v3 = arr_v[v] << 1;
        int v4 = arr_v[(v + 1) % v1] << 1;
        return !Triangulator.positiveArea(arr_f[v2], arr_f[v2 + 1], arr_f[v3], arr_f[v3 + 1], arr_f[v4], arr_f[v4 + 1]);
    }

    private static boolean positiveArea(float f, float f1, float f2, float f3, float f4, float f5) {
        return f * (f5 - f3) + f2 * (f1 - f5) + f4 * (f3 - f1) >= 0.0f;
    }

    public ShortArray triangulate(FloatArray floatArray0) {
        int v11;
        float[] arr_f = floatArray0.items;
        int v = floatArray0.size >> 1;
        ShortArray shortArray0 = this.indicesArray;
        shortArray0.clear();
        short[] arr_v = shortArray0.setSize(v);
        for(short v1 = 0; v1 < v; v1 = (short)(v1 + 1)) {
            arr_v[v1] = v1;
        }
        BooleanArray booleanArray0 = this.isConcaveArray;
        boolean[] arr_z = booleanArray0.setSize(v);
        for(int v2 = 0; v2 < v; ++v2) {
            arr_z[v2] = Triangulator.isConcave(v2, v, arr_f, arr_v);
        }
        ShortArray shortArray1 = this.triangles;
        shortArray1.clear();
        shortArray1.ensureCapacity(Math.max(0, v - 2) << 2);
        while(v > 3) {
            int v3 = v - 1;
            int v4 = 0;
            int v5 = 1;
            while(true) {
                if(!arr_z[v4]) {
                    int v6 = arr_v[v3] << 1;
                    int v7 = arr_v[v4] << 1;
                    int v8 = arr_v[v5] << 1;
                    float f = arr_f[v6];
                    float f1 = arr_f[v6 + 1];
                    float f2 = arr_f[v7];
                    float f3 = arr_f[v7 + 1];
                    float f4 = arr_f[v8];
                    float f5 = arr_f[v8 + 1];
                    int v9 = (v5 + 1) % v;
                    while(v9 != v3) {
                        if(arr_z[v9]) {
                            int v10 = arr_v[v9] << 1;
                            float f6 = arr_f[v10];
                            float f7 = arr_f[v10 + 1];
                            if(!Triangulator.positiveArea(f4, f5, f, f1, f6, f7) || !Triangulator.positiveArea(f, f1, f2, f3, f6, f7) || !Triangulator.positiveArea(f2, f3, f4, f5, f6, f7)) {
                                goto label_41;
                            }
                            goto label_45;
                        }
                    label_41:
                        v9 = (v9 + 1) % v;
                    }
                    v11 = v4;
                    break;
                }
            label_45:
                if(v5 == 0) {
                    while(true) {
                        if(!arr_z[v4]) {
                            v11 = v4;
                            break;
                        }
                        --v4;
                        if(v4 <= 0) {
                            v11 = v4;
                            break;
                        }
                    }
                    break;
                }
                v3 = v4;
                v4 = v5;
                v5 = (v5 + 1) % v;
            }
            shortArray1.add(arr_v[(v + v11 - 1) % v]);
            shortArray1.add(arr_v[v11]);
            shortArray1.add(arr_v[(v11 + 1) % v]);
            shortArray0.removeIndex(v11);
            booleanArray0.removeIndex(v11);
            --v;
            int v12 = (v + v11 - 1) % v;
            if(v11 == v) {
                v11 = 0;
            }
            arr_z[v12] = Triangulator.isConcave(v12, v, arr_f, arr_v);
            arr_z[v11] = Triangulator.isConcave(v11, v, arr_f, arr_v);
        }
        if(v == 3) {
            shortArray1.add(arr_v[2]);
            shortArray1.add(arr_v[0]);
            shortArray1.add(arr_v[1]);
        }
        return shortArray1;
    }

    private static int winding(float f, float f1, float f2, float f3, float f4, float f5) {
        return f4 * (f3 - f1) - f5 * (f2 - f) + (f2 - f) * f1 - f * (f3 - f1) >= 0.0f ? 1 : -1;
    }
}

