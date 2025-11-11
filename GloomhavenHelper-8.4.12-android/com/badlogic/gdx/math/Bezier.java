package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Bezier implements Path {
    public Array points;
    private Vector tmp;
    private Vector tmp2;
    private Vector tmp3;

    public Bezier() {
        this.points = new Array();
    }

    public Bezier(Array array0, int v, int v1) {
        this.points = new Array();
        this.set(array0, v, v1);
    }

    public Bezier(Vector[] arr_vector) {
        this.points = new Array();
        this.set(arr_vector);
    }

    public Bezier(Vector[] arr_vector, int v, int v1) {
        this.points = new Array();
        this.set(arr_vector, v, v1);
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
        Vector vector1 = (Vector)this.points.get(0);
        Vector vector2 = (Vector)this.points.get(this.points.size - 1);
        float f = vector1.dst2(vector2);
        float f1 = (float)Math.sqrt(f);
        return MathUtils.clamp((f1 - (vector0.dst2(vector2) + f - vector0.dst2(vector1)) / (2.0f * f1)) / f1, 0.0f, 1.0f);
    }

    @Override  // com.badlogic.gdx.math.Path
    public float approximate(Object object0) {
        return this.approximate(((Vector)object0));
    }

    public static Vector cubic(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3, Vector vector4, Vector vector5) {
        float f1 = (1.0f - f) * (1.0f - f);
        return vector0.set(vector1).scl(f1 * (1.0f - f)).add(vector5.set(vector2).scl(f1 * 3.0f * f)).add(vector5.set(vector3).scl((1.0f - f) * 3.0f * (f * f))).add(vector5.set(vector4).scl(f * f * f));
    }

    public static Vector cubic_derivative(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3, Vector vector4, Vector vector5) {
        return vector0.set(vector2).sub(vector1).scl((1.0f - f) * (1.0f - f) * 3.0f).add(vector5.set(vector3).sub(vector2).scl((1.0f - f) * f * 6.0f)).add(vector5.set(vector4).sub(vector3).scl(f * f * 3.0f));
    }

    public Vector derivativeAt(Vector vector0, float f) {
        int v = this.points.size;
        if(v == 2) {
            Bezier.linear_derivative(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), this.tmp);
            return vector0;
        }
        switch(v) {
            case 3: {
                Bezier.quadratic_derivative(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), ((Vector)this.points.get(2)), this.tmp);
                return vector0;
            }
            case 4: {
                Bezier.cubic_derivative(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), ((Vector)this.points.get(2)), ((Vector)this.points.get(3)), this.tmp);
                return vector0;
            }
            default: {
                return vector0;
            }
        }
    }

    @Override  // com.badlogic.gdx.math.Path
    public Object derivativeAt(Object object0, float f) {
        return this.derivativeAt(((Vector)object0), f);
    }

    public static Vector linear(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3) {
        return vector0.set(vector1).scl(1.0f - f).add(vector3.set(vector2).scl(f));
    }

    public static Vector linear_derivative(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3) {
        return vector0.set(vector2).sub(vector1);
    }

    public float locate(Vector vector0) {
        return this.approximate(vector0);
    }

    @Override  // com.badlogic.gdx.math.Path
    public float locate(Object object0) {
        return this.locate(((Vector)object0));
    }

    public static Vector quadratic(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3, Vector vector4) {
        return vector0.set(vector1).scl((1.0f - f) * (1.0f - f)).add(vector4.set(vector2).scl((1.0f - f) * 2.0f * f)).add(vector4.set(vector3).scl(f * f));
    }

    public static Vector quadratic_derivative(Vector vector0, float f, Vector vector1, Vector vector2, Vector vector3, Vector vector4) {
        return vector0.set(vector2).sub(vector1).scl(2.0f).scl(1.0f - f).add(vector4.set(vector3).sub(vector2).scl(f).scl(2.0f));
    }

    public Bezier set(Array array0, int v, int v1) {
        if(v1 < 2 || v1 > 4) {
            throw new GdxRuntimeException("Only first, second and third degree Bezier curves are supported.");
        }
        if(this.tmp == null) {
            this.tmp = ((Vector)array0.get(0)).cpy();
        }
        if(this.tmp2 == null) {
            this.tmp2 = ((Vector)array0.get(0)).cpy();
        }
        if(this.tmp3 == null) {
            this.tmp3 = ((Vector)array0.get(0)).cpy();
        }
        this.points.clear();
        this.points.addAll(array0, v, v1);
        return this;
    }

    public Bezier set(Vector[] arr_vector) {
        return this.set(arr_vector, 0, arr_vector.length);
    }

    public Bezier set(Vector[] arr_vector, int v, int v1) {
        if(v1 < 2 || v1 > 4) {
            throw new GdxRuntimeException("Only first, second and third degree Bezier curves are supported.");
        }
        if(this.tmp == null) {
            this.tmp = arr_vector[0].cpy();
        }
        if(this.tmp2 == null) {
            this.tmp2 = arr_vector[0].cpy();
        }
        if(this.tmp3 == null) {
            this.tmp3 = arr_vector[0].cpy();
        }
        this.points.clear();
        this.points.addAll(arr_vector, v, v1);
        return this;
    }

    public Vector valueAt(Vector vector0, float f) {
        int v = this.points.size;
        if(v == 2) {
            Bezier.linear(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), this.tmp);
            return vector0;
        }
        switch(v) {
            case 3: {
                Bezier.quadratic(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), ((Vector)this.points.get(2)), this.tmp);
                return vector0;
            }
            case 4: {
                Bezier.cubic(vector0, f, ((Vector)this.points.get(0)), ((Vector)this.points.get(1)), ((Vector)this.points.get(2)), ((Vector)this.points.get(3)), this.tmp);
                return vector0;
            }
            default: {
                return vector0;
            }
        }
    }

    @Override  // com.badlogic.gdx.math.Path
    public Object valueAt(Object object0, float f) {
        return this.valueAt(((Vector)object0), f);
    }
}

