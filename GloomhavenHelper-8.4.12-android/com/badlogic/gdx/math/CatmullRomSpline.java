package com.badlogic.gdx.math;

public class CatmullRomSpline implements Path {
    public boolean continuous;
    public Vector[] controlPoints;
    public int spanCount;
    private Vector tmp;
    private Vector tmp2;
    private Vector tmp3;

    public CatmullRomSpline() {
    }

    public CatmullRomSpline(Vector[] arr_vector, boolean z) {
        this.set(arr_vector, z);
    }

    @Override  // com.badlogic.gdx.math.Path
    public float approxLength(int v) {
        float f = 0.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            this.tmp2.set(this.tmp3);
            this.valueAt(this.tmp3, ((float)v1) / (((float)v) - 1.0f));
            if(v1 > 0) {
                f += this.tmp2.dst(this.tmp3);
            }
        }
        return f;
    }

    public float approximate(Vector vector0) {
        return this.approximate(vector0, this.nearest(vector0));
    }

    public float approximate(Vector vector0, int v) {
        Vector[] arr_vector = this.controlPoints;
        Vector vector1 = arr_vector[v];
        Vector vector2 = arr_vector[(v <= 0 ? this.spanCount - 1 : v - 1)];
        Vector vector3 = this.controlPoints[(v + 1) % this.spanCount];
        float f = vector0.dst2(vector2);
        if(vector0.dst2(vector3) < f) {
            vector2 = vector1;
            vector1 = vector3;
        }
        else {
            if(v <= 0) {
                v = this.spanCount;
            }
            --v;
        }
        float f1 = vector2.dst2(vector1);
        float f2 = (float)Math.sqrt(f1);
        return (((float)v) + MathUtils.clamp((f2 - (vector0.dst2(vector1) + f1 - vector0.dst2(vector2)) / (2.0f * f2)) / f2, 0.0f, 1.0f)) / ((float)this.spanCount);
    }

    public float approximate(Vector vector0, int v, int v1) {
        return this.approximate(vector0, this.nearest(vector0, v, v1));
    }

    @Override  // com.badlogic.gdx.math.Path
    public float approximate(Object object0) {
        return this.approximate(((Vector)object0));
    }

    public static Vector calculate(Vector vector0, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        int v = z ? arr_vector.length : arr_vector.length - 3;
        float f1 = ((float)v) * f;
        return f >= 1.0f ? CatmullRomSpline.calculate(vector0, v - 1, f1 - ((float)(v - 1)), arr_vector, z, vector1) : CatmullRomSpline.calculate(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, z, vector1);
    }

    public static Vector calculate(Vector vector0, int v, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        float f1 = f * f;
        float f2 = f1 * f;
        vector0.set(arr_vector[v]).scl(1.5f * f2 - 2.5f * f1 + 1.0f);
        if(z || v > 0) {
            vector0.add(vector1.set(arr_vector[(arr_vector.length + v - 1) % arr_vector.length]).scl(-0.5f * f2 + f1 - f * 0.5f));
        }
        if(z || v < arr_vector.length - 1) {
            vector0.add(vector1.set(arr_vector[(v + 1) % arr_vector.length]).scl(-1.5f * f2 + 2.0f * f1 + f * 0.5f));
        }
        if(z || v < arr_vector.length - 2) {
            vector0.add(vector1.set(arr_vector[(v + 2) % arr_vector.length]).scl(f2 * 0.5f - f1 * 0.5f));
        }
        return vector0;
    }

    public static Vector derivative(Vector vector0, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        int v = z ? arr_vector.length : arr_vector.length - 3;
        float f1 = ((float)v) * f;
        return f >= 1.0f ? CatmullRomSpline.derivative(vector0, v - 1, f1 - ((float)(v - 1)), arr_vector, z, vector1) : CatmullRomSpline.derivative(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, z, vector1);
    }

    public static Vector derivative(Vector vector0, int v, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        float f1 = f * f;
        vector0.set(arr_vector[v]).scl(5.0f * -f + 4.5f * f1);
        if(z || v > 0) {
            vector0.add(vector1.set(arr_vector[(arr_vector.length + v - 1) % arr_vector.length]).scl(2.0f * f - 0.5f - f1 * 1.5f));
        }
        if(z || v < arr_vector.length - 1) {
            vector0.add(vector1.set(arr_vector[(v + 1) % arr_vector.length]).scl(f * 4.0f + 0.5f - 4.5f * f1));
        }
        if(z || v < arr_vector.length - 2) {
            vector0.add(vector1.set(arr_vector[(v + 2) % arr_vector.length]).scl(f1 * 1.5f - f));
        }
        return vector0;
    }

    public Vector derivativeAt(Vector vector0, float f) {
        float f1 = ((float)this.spanCount) * f;
        return f >= 1.0f ? this.derivativeAt(vector0, this.spanCount - 1, f1 - ((float)(this.spanCount - 1))) : this.derivativeAt(vector0, ((int)f1), f1 - ((float)(((int)f1))));
    }

    public Vector derivativeAt(Vector vector0, int v, float f) {
        if(!this.continuous) {
            ++v;
        }
        return CatmullRomSpline.derivative(vector0, v, f, this.controlPoints, this.continuous, this.tmp);
    }

    @Override  // com.badlogic.gdx.math.Path
    public Object derivativeAt(Object object0, float f) {
        return this.derivativeAt(((Vector)object0), f);
    }

    public float locate(Vector vector0) {
        return this.approximate(vector0);
    }

    @Override  // com.badlogic.gdx.math.Path
    public float locate(Object object0) {
        return this.locate(((Vector)object0));
    }

    public int nearest(Vector vector0) {
        return this.nearest(vector0, 0, this.spanCount);
    }

    public int nearest(Vector vector0, int v, int v1) {
        while(v < 0) {
            v += this.spanCount;
        }
        int v2 = v % this.spanCount;
        float f = vector0.dst2(this.controlPoints[v2]);
        for(int v3 = 1; v3 < v1; ++v3) {
            int v4 = (v + v3) % this.spanCount;
            float f1 = vector0.dst2(this.controlPoints[v4]);
            if(f1 < f) {
                v2 = v4;
                f = f1;
            }
        }
        return v2;
    }

    public CatmullRomSpline set(Vector[] arr_vector, boolean z) {
        if(this.tmp == null) {
            this.tmp = arr_vector[0].cpy();
        }
        if(this.tmp2 == null) {
            this.tmp2 = arr_vector[0].cpy();
        }
        if(this.tmp3 == null) {
            this.tmp3 = arr_vector[0].cpy();
        }
        this.controlPoints = arr_vector;
        this.continuous = z;
        this.spanCount = z ? arr_vector.length : arr_vector.length - 3;
        return this;
    }

    public Vector valueAt(Vector vector0, float f) {
        float f1 = ((float)this.spanCount) * f;
        return f >= 1.0f ? this.valueAt(vector0, this.spanCount - 1, f1 - ((float)(this.spanCount - 1))) : this.valueAt(vector0, ((int)f1), f1 - ((float)(((int)f1))));
    }

    public Vector valueAt(Vector vector0, int v, float f) {
        if(!this.continuous) {
            ++v;
        }
        return CatmullRomSpline.calculate(vector0, v, f, this.controlPoints, this.continuous, this.tmp);
    }

    @Override  // com.badlogic.gdx.math.Path
    public Object valueAt(Object object0, float f) {
        return this.valueAt(((Vector)object0), f);
    }
}

