package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AmbientCubemap {
    private static final int NUM_VALUES = 18;
    public final float[] data;

    public AmbientCubemap() {
        this.data = new float[18];
    }

    public AmbientCubemap(AmbientCubemap ambientCubemap0) {
        this(ambientCubemap0.data);
    }

    public AmbientCubemap(float[] arr_f) {
        if(arr_f.length != 18) {
            throw new GdxRuntimeException("Incorrect array size");
        }
        this.data = new float[arr_f.length];
        System.arraycopy(arr_f, 0, this.data, 0, this.data.length);
    }

    public AmbientCubemap add(float f, float f1, float f2) {
        for(int v = 0; true; v += 3) {
            float[] arr_f = this.data;
            if(v >= arr_f.length) {
                break;
            }
            arr_f[v] += f;
            arr_f[v + 1] += f1;
            arr_f[v + 2] += f2;
        }
        return this;
    }

    public AmbientCubemap add(float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = f3 * f3;
        float f7 = f4 * f4;
        float f8 = f5 * f5;
        float f9 = f6 + f7 + f8;
        if(f9 == 0.0f) {
            return this;
        }
        float f10 = 1.0f / f9 * (f9 + 1.0f);
        float f11 = f * f10;
        float f12 = f1 * f10;
        float f13 = f2 * f10;
        int v = f3 > 0.0f ? 0 : 3;
        this.data[v] += f6 * f11;
        this.data[v + 1] += f6 * f12;
        this.data[v + 2] += f6 * f13;
        int v1 = f4 > 0.0f ? 6 : 9;
        this.data[v1] += f7 * f11;
        this.data[v1 + 1] += f7 * f12;
        this.data[v1 + 2] += f7 * f13;
        int v2 = f5 > 0.0f ? 12 : 15;
        this.data[v2] += f11 * f8;
        this.data[v2 + 1] += f12 * f8;
        this.data[v2 + 2] += f8 * f13;
        return this;
    }

    public AmbientCubemap add(float f, float f1, float f2, Vector3 vector30) {
        return this.add(f, f1, f2, vector30.x, vector30.y, vector30.z);
    }

    public AmbientCubemap add(Color color0) {
        return this.add(color0.r, color0.g, color0.b);
    }

    public AmbientCubemap add(Color color0, float f, float f1, float f2) {
        return this.add(color0.r, color0.g, color0.b, f, f1, f2);
    }

    public AmbientCubemap add(Color color0, Vector3 vector30) {
        return this.add(color0.r, color0.g, color0.b, vector30.x, vector30.y, vector30.z);
    }

    public AmbientCubemap add(Color color0, Vector3 vector30, Vector3 vector31) {
        return this.add(color0.r, color0.g, color0.b, vector31.x - vector30.x, vector31.y - vector30.y, vector31.z - vector30.z);
    }

    public AmbientCubemap add(Color color0, Vector3 vector30, Vector3 vector31, float f) {
        float f1 = f / (vector31.dst(vector30) + 1.0f);
        return this.add(color0.r * f1, color0.g * f1, color0.b * f1, vector31.x - vector30.x, vector31.y - vector30.y, vector31.z - vector30.z);
    }

    private static final float clamp(float f) {
        if(f < 0.0f) {
            return 0.0f;
        }
        return f > 1.0f ? 1.0f : f;
    }

    public AmbientCubemap clamp() {
        for(int v = 0; true; ++v) {
            float[] arr_f = this.data;
            if(v >= arr_f.length) {
                break;
            }
            arr_f[v] = AmbientCubemap.clamp(arr_f[v]);
        }
        return this;
    }

    public AmbientCubemap clear() {
        for(int v = 0; true; ++v) {
            float[] arr_f = this.data;
            if(v >= arr_f.length) {
                break;
            }
            arr_f[v] = 0.0f;
        }
        return this;
    }

    public Color getColor(Color color0, int v) {
        return color0.set(this.data[v * 3], this.data[v * 3 + 1], this.data[v * 3 + 2], 1.0f);
    }

    public AmbientCubemap set(float f, float f1, float f2) {
        for(int v = 0; v < 18; v += 3) {
            this.data[v] = f;
            this.data[v + 1] = f1;
            this.data[v + 2] = f2;
        }
        return this;
    }

    public AmbientCubemap set(Color color0) {
        return this.set(color0.r, color0.g, color0.b);
    }

    public AmbientCubemap set(AmbientCubemap ambientCubemap0) {
        return this.set(ambientCubemap0.data);
    }

    public AmbientCubemap set(float[] arr_f) {
        for(int v = 0; true; ++v) {
            float[] arr_f1 = this.data;
            if(v >= arr_f1.length) {
                break;
            }
            arr_f1[v] = arr_f[v];
        }
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        for(int v = 0; v < this.data.length; v += 3) {
            s = s + Float.toString(this.data[v]) + ", " + Float.toString(this.data[v + 1]) + ", " + Float.toString(this.data[v + 2]) + "\n";
        }
        return s;
    }
}

