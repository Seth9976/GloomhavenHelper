package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SphericalHarmonics {
    private static final float[] coeff;
    public final float[] data;

    static {
        SphericalHarmonics.coeff = new float[]{0.282095f, 0.488603f, 0.488603f, 0.488603f, 1.092548f, 1.092548f, 1.092548f, 0.315392f, 0.546274f};
    }

    public SphericalHarmonics() {
        this.data = new float[27];
    }

    public SphericalHarmonics(float[] arr_f) {
        if(arr_f.length != 27) {
            throw new GdxRuntimeException("Incorrect array size");
        }
        this.data = (float[])arr_f.clone();
    }

    private static final float clamp(float f) {
        if(f < 0.0f) {
            return 0.0f;
        }
        return f > 1.0f ? 1.0f : f;
    }

    public SphericalHarmonics set(float f, float f1, float f2) {
        for(int v = 0; true; v += 3) {
            float[] arr_f = this.data;
            if(v >= arr_f.length) {
                break;
            }
            arr_f[v] = f;
            arr_f[v + 1] = f1;
            arr_f[v + 2] = f2;
        }
        return this;
    }

    public SphericalHarmonics set(Color color0) {
        return this.set(color0.r, color0.g, color0.b);
    }

    public SphericalHarmonics set(AmbientCubemap ambientCubemap0) {
        return this.set(ambientCubemap0.data);
    }

    public SphericalHarmonics set(float[] arr_f) {
        for(int v = 0; true; ++v) {
            float[] arr_f1 = this.data;
            if(v >= arr_f1.length) {
                break;
            }
            arr_f1[v] = arr_f[v];
        }
        return this;
    }
}

