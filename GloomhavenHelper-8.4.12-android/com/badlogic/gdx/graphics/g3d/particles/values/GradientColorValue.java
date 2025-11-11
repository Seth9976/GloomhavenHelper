package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class GradientColorValue extends ParticleValue {
    private float[] colors;
    private static float[] temp;
    public float[] timeline;

    static {
        GradientColorValue.temp = new float[3];
    }

    public GradientColorValue() {
        this.colors = new float[]{1.0f, 1.0f, 1.0f};
        this.timeline = new float[]{0.0f};
    }

    public void getColor(float f, float[] arr_f, int v) {
        float[] arr_f1 = this.timeline;
        int v1 = 0;
        int v2 = 1;
        while(true) {
            if(v2 >= arr_f1.length) {
                v2 = -1;
                break;
            }
            if(arr_f1[v2] > f) {
                break;
            }
            int v3 = v2;
            ++v2;
            v1 = v3;
        }
        float f1 = arr_f1[v1];
        float[] arr_f2 = this.colors;
        float f2 = arr_f2[v1 * 3];
        float f3 = arr_f2[v1 * 3 + 1];
        float f4 = arr_f2[v1 * 3 + 2];
        if(v2 == -1) {
            arr_f[v] = f2;
            arr_f[v + 1] = f3;
            arr_f[v + 2] = f4;
            return;
        }
        float f5 = (f - f1) / (arr_f1[v2] - f1);
        arr_f[v] = f2 + (arr_f2[v2 * 3] - f2) * f5;
        arr_f[v + 1] = f3 + (arr_f2[v2 * 3 + 1] - f3) * f5;
        arr_f[v + 2] = f4 + (arr_f2[v2 * 3 + 2] - f4) * f5;
    }

    public float[] getColor(float f) {
        this.getColor(f, GradientColorValue.temp, 0);
        return GradientColorValue.temp;
    }

    public float[] getColors() {
        return this.colors;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public void load(GradientColorValue gradientColorValue0) {
        super.load(gradientColorValue0);
        this.colors = new float[gradientColorValue0.colors.length];
        System.arraycopy(gradientColorValue0.colors, 0, this.colors, 0, this.colors.length);
        this.timeline = new float[gradientColorValue0.timeline.length];
        System.arraycopy(gradientColorValue0.timeline, 0, this.timeline, 0, this.timeline.length);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void read(Json json0, JsonValue jsonValue0) {
        super.read(json0, jsonValue0);
        this.colors = (float[])json0.readValue("colors", float[].class, jsonValue0);
        this.timeline = (float[])json0.readValue("timeline", float[].class, jsonValue0);
    }

    public void setColors(float[] arr_f) {
        this.colors = arr_f;
    }

    public void setTimeline(float[] arr_f) {
        this.timeline = arr_f;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void write(Json json0) {
        super.write(json0);
        json0.writeValue("colors", this.colors);
        json0.writeValue("timeline", this.timeline);
    }
}

