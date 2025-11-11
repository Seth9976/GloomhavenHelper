package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;

public class BSpline implements Path {
    public boolean continuous;
    public Vector[] controlPoints;
    private static final float d6 = 0.166667f;
    public int degree;
    public Array knots;
    public int spanCount;
    private Vector tmp;
    private Vector tmp2;
    private Vector tmp3;

    public BSpline() {
    }

    public BSpline(Vector[] arr_vector, int v, boolean z) {
        this.set(arr_vector, v, z);
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
        Vector vector1 = (Vector)this.knots.get(v);
        Vector vector2 = (Vector)this.knots.get((v <= 0 ? this.spanCount - 1 : v - 1));
        Vector vector3 = (Vector)this.knots.get((v + 1) % this.spanCount);
        float f = vector0.dst2(vector2);
        if(vector0.dst2(vector3) >= f) {
            if(v <= 0) {
                v = this.spanCount;
            }
            --v;
            vector3 = vector1;
            vector1 = vector2;
        }
        float f1 = vector1.dst2(vector3);
        float f2 = (float)Math.sqrt(f1);
        return (((float)v) + MathUtils.clamp((f2 - (vector0.dst2(vector3) + f1 - vector0.dst2(vector1)) / (2.0f * f2)) / f2, 0.0f, 1.0f)) / ((float)this.spanCount);
    }

    public float approximate(Vector vector0, int v, int v1) {
        return this.approximate(vector0, this.nearest(vector0, v, v1));
    }

    @Override  // com.badlogic.gdx.math.Path
    public float approximate(Object object0) {
        return this.approximate(((Vector)object0));
    }

    public static Vector calculate(Vector vector0, float f, Vector[] arr_vector, int v, boolean z, Vector vector1) {
        int v1 = z ? arr_vector.length : arr_vector.length - v;
        float f1 = ((float)v1) * f;
        return f >= 1.0f ? BSpline.calculate(vector0, v1 - 1, f1 - ((float)(v1 - 1)), arr_vector, v, z, vector1) : BSpline.calculate(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, v, z, vector1);
    }

    public static Vector calculate(Vector vector0, int v, float f, Vector[] arr_vector, int v1, boolean z, Vector vector1) {
        if(v1 != 3) {
            throw new IllegalArgumentException();
        }
        return BSpline.cubic(vector0, v, f, arr_vector, z, vector1);
    }

    public static Vector cubic(Vector vector0, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        int v = z ? arr_vector.length : arr_vector.length - 3;
        float f1 = ((float)v) * f;
        return f >= 1.0f ? BSpline.cubic(vector0, v - 1, f1 - ((float)(v - 1)), arr_vector, z, vector1) : BSpline.cubic(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, z, vector1);
    }

    public static Vector cubic(Vector vector0, int v, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        float f1 = f * f;
        float f2 = f1 * f;
        vector0.set(arr_vector[v]).scl((f2 * 3.0f - 6.0f * f1 + 4.0f) * 0.166667f);
        if(z || v > 0) {
            vector0.add(vector1.set(arr_vector[(arr_vector.length + v - 1) % arr_vector.length]).scl((1.0f - f) * (1.0f - f) * (1.0f - f) * 0.166667f));
        }
        if(z || v < arr_vector.length - 1) {
            vector0.add(vector1.set(arr_vector[(v + 1) % arr_vector.length]).scl((-3.0f * f2 + f1 * 3.0f + f * 3.0f + 1.0f) * 0.166667f));
        }
        if(z || v < arr_vector.length - 2) {
            vector0.add(vector1.set(arr_vector[(v + 2) % arr_vector.length]).scl(f2 * 0.166667f));
        }
        return vector0;
    }

    public static Vector cubic_derivative(Vector vector0, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        int v = z ? arr_vector.length : arr_vector.length - 3;
        float f1 = ((float)v) * f;
        return f >= 1.0f ? BSpline.cubic(vector0, v - 1, f1 - ((float)(v - 1)), arr_vector, z, vector1) : BSpline.cubic(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, z, vector1);
    }

    public static Vector cubic_derivative(Vector vector0, int v, float f, Vector[] arr_vector, boolean z, Vector vector1) {
        float f1 = f * f;
        vector0.set(arr_vector[v]).scl(1.5f * f1 - 2.0f * f);
        if(z || v > 0) {
            vector0.add(vector1.set(arr_vector[(arr_vector.length + v - 1) % arr_vector.length]).scl(-0.5f * (1.0f - f) * (1.0f - f)));
        }
        if(z || v < arr_vector.length - 1) {
            vector0.add(vector1.set(arr_vector[(v + 1) % arr_vector.length]).scl(-1.5f * f1 + f + 0.5f));
        }
        if(z || v < arr_vector.length - 2) {
            vector0.add(vector1.set(arr_vector[(v + 2) % arr_vector.length]).scl(f1 * 0.5f));
        }
        return vector0;
    }

    public static Vector derivative(Vector vector0, float f, Vector[] arr_vector, int v, boolean z, Vector vector1) {
        int v1 = z ? arr_vector.length : arr_vector.length - v;
        float f1 = ((float)v1) * f;
        return f >= 1.0f ? BSpline.derivative(vector0, v1 - 1, f1 - ((float)(v1 - 1)), arr_vector, v, z, vector1) : BSpline.derivative(vector0, ((int)f1), f1 - ((float)(((int)f1))), arr_vector, v, z, vector1);
    }

    public static Vector derivative(Vector vector0, int v, float f, Vector[] arr_vector, int v1, boolean z, Vector vector1) {
        if(v1 != 3) {
            throw new IllegalArgumentException();
        }
        return BSpline.cubic_derivative(vector0, v, f, arr_vector, z, vector1);
    }

    public Vector derivativeAt(Vector vector0, float f) {
        float f1 = ((float)this.spanCount) * f;
        return f >= 1.0f ? this.derivativeAt(vector0, this.spanCount - 1, f1 - ((float)(this.spanCount - 1))) : this.derivativeAt(vector0, ((int)f1), f1 - ((float)(((int)f1))));
    }

    public Vector derivativeAt(Vector vector0, int v, float f) {
        if(!this.continuous) {
            v += (int)(((float)this.degree) * 0.5f);
        }
        return BSpline.derivative(vector0, v, f, this.controlPoints, this.degree, this.continuous, this.tmp);
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
        float f = vector0.dst2(((Vector)this.knots.get(v2)));
        for(int v3 = 1; v3 < v1; ++v3) {
            int v4 = (v + v3) % this.spanCount;
            float f1 = vector0.dst2(((Vector)this.knots.get(v4)));
            if(f1 < f) {
                v2 = v4;
                f = f1;
            }
        }
        return v2;
    }

    public BSpline set(Vector[] arr_vector, int v, boolean z) {
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
        this.degree = v;
        this.continuous = z;
        this.spanCount = z ? arr_vector.length : arr_vector.length - v;
        Array array0 = this.knots;
        if(array0 == null) {
            this.knots = new Array(this.spanCount);
        }
        else {
            array0.clear();
            this.knots.ensureCapacity(this.spanCount);
        }
        for(int v1 = 0; v1 < this.spanCount; ++v1) {
            this.knots.add(BSpline.calculate(arr_vector[0].cpy(), (z ? v1 : ((int)(((float)v1) + ((float)v) * 0.5f))), 0.0f, arr_vector, v, z, this.tmp));
        }
        return this;
    }

    public Vector valueAt(Vector vector0, float f) {
        float f1 = ((float)this.spanCount) * f;
        return f >= 1.0f ? this.valueAt(vector0, this.spanCount - 1, f1 - ((float)(this.spanCount - 1))) : this.valueAt(vector0, ((int)f1), f1 - ((float)(((int)f1))));
    }

    public Vector valueAt(Vector vector0, int v, float f) {
        if(!this.continuous) {
            v += (int)(((float)this.degree) * 0.5f);
        }
        return BSpline.calculate(vector0, v, f, this.controlPoints, this.degree, this.continuous, this.tmp);
    }

    @Override  // com.badlogic.gdx.math.Path
    public Object valueAt(Object object0, float f) {
        return this.valueAt(((Vector)object0), f);
    }
}

