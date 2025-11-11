package com.badlogic.gdx.utils;

import java.util.Arrays;

public class QuadTreeFloat implements Poolable {
    public static final int DISTSQR = 3;
    public static final int VALUE = 0;
    public static final int X = 1;
    public static final int Y = 2;
    public int count;
    public int depth;
    public float height;
    public final int maxDepth;
    public final int maxValues;
    @Null
    public QuadTreeFloat ne;
    @Null
    public QuadTreeFloat nw;
    private static final Pool pool;
    @Null
    public QuadTreeFloat se;
    @Null
    public QuadTreeFloat sw;
    public float[] values;
    public float width;
    public float x;
    public float y;

    static {
        QuadTreeFloat.pool = new Pool(0x80, 0x1000) {
            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return new QuadTreeFloat();
            }
        };
    }

    public QuadTreeFloat() {
        this(16, 8);
    }

    public QuadTreeFloat(int v, int v1) {
        this.maxValues = v * 3;
        this.maxDepth = v1;
        this.values = new float[this.maxValues];
    }

    public void add(float f, float f1, float f2) {
        int v = this.count;
        if(v == -1) {
            this.addToChild(f, f1, f2);
            return;
        }
        if(this.depth >= this.maxDepth) {
            float[] arr_f = this.values;
            if(v == arr_f.length) {
                this.values = Arrays.copyOf(arr_f, this.growValues());
            }
        }
        else if(v == this.maxValues) {
            this.split(f, f1, f2);
            return;
        }
        float[] arr_f1 = this.values;
        arr_f1[v] = f;
        arr_f1[v + 1] = f1;
        arr_f1[v + 2] = f2;
        this.count += 3;
    }

    private void addToChild(float f, float f1, float f2) {
        QuadTreeFloat quadTreeFloat0;
        float f3 = this.width / 2.0f;
        float f4 = this.height / 2.0f;
        float f5 = this.x;
        if(f1 < f5 + f3) {
            float f6 = this.y;
            if(f2 < f6 + f4) {
                quadTreeFloat0 = this.sw;
                if(quadTreeFloat0 == null) {
                    quadTreeFloat0 = this.obtainChild(f5, f6, f3, f4, this.depth + 1);
                    this.sw = quadTreeFloat0;
                }
            }
            else {
                quadTreeFloat0 = this.nw;
                if(quadTreeFloat0 == null) {
                    quadTreeFloat0 = this.obtainChild(f5, f6 + f4, f3, f4, this.depth + 1);
                    this.nw = quadTreeFloat0;
                }
            }
        }
        else {
            float f7 = this.y;
            if(f2 < f7 + f4) {
                quadTreeFloat0 = this.se;
                if(quadTreeFloat0 == null) {
                    quadTreeFloat0 = this.obtainChild(f5 + f3, f7, f3, f4, this.depth + 1);
                    this.se = quadTreeFloat0;
                }
            }
            else {
                quadTreeFloat0 = this.ne;
                if(quadTreeFloat0 == null) {
                    quadTreeFloat0 = this.obtainChild(f5 + f3, f7 + f4, f3, f4, this.depth + 1);
                    this.ne = quadTreeFloat0;
                }
            }
        }
        quadTreeFloat0.add(f, f1, f2);
    }

    private void findNearestInternal(float f, float f1, FloatArray floatArray0) {
        if(this.x < f && this.x + this.width > f && (this.y < f1 && this.y + this.height > f1)) {
            int v = this.count;
            if(v != -1) {
                float f2 = floatArray0.first();
                float f3 = floatArray0.get(1);
                float f4 = floatArray0.get(2);
                float f5 = floatArray0.get(3);
                float[] arr_f = this.values;
                float f6 = f5;
                float f7 = f4;
                float f8 = f3;
                float f9 = f2;
                for(int v1 = 1; v1 < v; v1 += 3) {
                    float f10 = arr_f[v1];
                    float f11 = arr_f[v1 + 1];
                    float f12 = (f10 - f) * (f10 - f) + (f11 - f1) * (f11 - f1);
                    if(f12 < f6) {
                        f9 = arr_f[v1 - 1];
                        f8 = f10;
                        f7 = f11;
                        f6 = f12;
                    }
                }
                floatArray0.set(0, f9);
                floatArray0.set(1, f8);
                floatArray0.set(2, f7);
                floatArray0.set(3, f6);
                return;
            }
            QuadTreeFloat quadTreeFloat0 = this.nw;
            if(quadTreeFloat0 != null) {
                quadTreeFloat0.findNearestInternal(f, f1, floatArray0);
            }
            QuadTreeFloat quadTreeFloat1 = this.sw;
            if(quadTreeFloat1 != null) {
                quadTreeFloat1.findNearestInternal(f, f1, floatArray0);
            }
            QuadTreeFloat quadTreeFloat2 = this.ne;
            if(quadTreeFloat2 != null) {
                quadTreeFloat2.findNearestInternal(f, f1, floatArray0);
            }
            QuadTreeFloat quadTreeFloat3 = this.se;
            if(quadTreeFloat3 != null) {
                quadTreeFloat3.findNearestInternal(f, f1, floatArray0);
            }
        }
    }

    protected int growValues() {
        return this.count + 30;
    }

    public boolean nearest(float f, float f1, FloatArray floatArray0) {
        floatArray0.clear();
        floatArray0.add(0.0f);
        floatArray0.add(0.0f);
        floatArray0.add(0.0f);
        floatArray0.add(Infinityf);
        this.findNearestInternal(f, f1, floatArray0);
        float f2 = floatArray0.first();
        float f3 = floatArray0.get(1);
        float f4 = floatArray0.get(2);
        float f5 = floatArray0.get(3);
        boolean z = f5 != Infinityf;
        if(!z) {
            float f6 = Math.max(this.width, this.height);
            f5 = f6 * f6;
        }
        floatArray0.clear();
        this.query(f, f1, ((float)Math.sqrt(f5)), floatArray0);
        int v1 = floatArray0.size;
        for(int v = 3; v < v1; v += 4) {
            float f7 = floatArray0.get(v);
            if(f7 < f5) {
                f2 = floatArray0.get(v - 3);
                f3 = floatArray0.get(v - 2);
                f4 = floatArray0.get(v - 1);
                f5 = f7;
            }
        }
        if(!z && floatArray0.isEmpty()) {
            return false;
        }
        floatArray0.clear();
        floatArray0.add(f2);
        floatArray0.add(f3);
        floatArray0.add(f4);
        floatArray0.add(f5);
        return true;
    }

    private QuadTreeFloat obtainChild(float f, float f1, float f2, float f3, int v) {
        QuadTreeFloat quadTreeFloat0 = (QuadTreeFloat)QuadTreeFloat.pool.obtain();
        quadTreeFloat0.x = f;
        quadTreeFloat0.y = f1;
        quadTreeFloat0.width = f2;
        quadTreeFloat0.height = f3;
        quadTreeFloat0.depth = v;
        return quadTreeFloat0;
    }

    private void query(float f, float f1, float f2, float f3, float f4, float f5, FloatArray floatArray0) {
        if(this.x < f3 + f5 && this.x + this.width > f3 && (this.y < f4 + f5 && this.y + this.height > f4)) {
            int v = this.count;
            if(v != -1) {
                float[] arr_f = this.values;
                for(int v1 = 1; v1 < v; v1 += 3) {
                    float f6 = arr_f[v1];
                    float f7 = arr_f[v1 + 1];
                    float f8 = (f6 - f) * (f6 - f) + (f7 - f1) * (f7 - f1);
                    if(f8 <= f2) {
                        floatArray0.add(arr_f[v1 - 1]);
                        floatArray0.add(f6);
                        floatArray0.add(f7);
                        floatArray0.add(f8);
                    }
                }
                return;
            }
            QuadTreeFloat quadTreeFloat0 = this.nw;
            if(quadTreeFloat0 != null) {
                quadTreeFloat0.query(f, f1, f2, f3, f4, f5, floatArray0);
            }
            QuadTreeFloat quadTreeFloat1 = this.sw;
            if(quadTreeFloat1 != null) {
                quadTreeFloat1.query(f, f1, f2, f3, f4, f5, floatArray0);
            }
            QuadTreeFloat quadTreeFloat2 = this.ne;
            if(quadTreeFloat2 != null) {
                quadTreeFloat2.query(f, f1, f2, f3, f4, f5, floatArray0);
            }
            QuadTreeFloat quadTreeFloat3 = this.se;
            if(quadTreeFloat3 != null) {
                quadTreeFloat3.query(f, f1, f2, f3, f4, f5, floatArray0);
            }
        }
    }

    public void query(float f, float f1, float f2, FloatArray floatArray0) {
        this.query(f, f1, f2 * f2, f - f2, f1 - f2, f2 * 2.0f, floatArray0);
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        if(this.count == -1) {
            QuadTreeFloat quadTreeFloat0 = this.nw;
            if(quadTreeFloat0 != null) {
                QuadTreeFloat.pool.free(quadTreeFloat0);
                this.nw = null;
            }
            QuadTreeFloat quadTreeFloat1 = this.sw;
            if(quadTreeFloat1 != null) {
                QuadTreeFloat.pool.free(quadTreeFloat1);
                this.sw = null;
            }
            QuadTreeFloat quadTreeFloat2 = this.ne;
            if(quadTreeFloat2 != null) {
                QuadTreeFloat.pool.free(quadTreeFloat2);
                this.ne = null;
            }
            QuadTreeFloat quadTreeFloat3 = this.se;
            if(quadTreeFloat3 != null) {
                QuadTreeFloat.pool.free(quadTreeFloat3);
                this.se = null;
            }
        }
        this.count = 0;
        int v = this.maxValues;
        if(this.values.length > v) {
            this.values = new float[v];
        }
    }

    public void setBounds(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
    }

    private void split(float f, float f1, float f2) {
        float[] arr_f = this.values;
        for(int v = 0; v < this.maxValues; v += 3) {
            this.addToChild(arr_f[v], arr_f[v + 1], arr_f[v + 2]);
        }
        this.count = -1;
        this.addToChild(f, f1, f2);
    }
}

