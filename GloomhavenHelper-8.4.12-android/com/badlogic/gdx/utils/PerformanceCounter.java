package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.FloatCounter;

public class PerformanceCounter {
    public float current;
    private long lastTick;
    public final FloatCounter load;
    public final String name;
    private static final float nano2seconds = 1.000000E-09f;
    private long startTime;
    public final FloatCounter time;
    public boolean valid;

    public PerformanceCounter(String s) {
        this(s, 5);
    }

    public PerformanceCounter(String s, int v) {
        this.startTime = 0L;
        this.lastTick = 0L;
        this.current = 0.0f;
        this.valid = false;
        this.name = s;
        this.time = new FloatCounter(v);
        this.load = new FloatCounter(1);
    }

    public void reset() {
        this.time.reset();
        this.load.reset();
        this.startTime = 0L;
        this.lastTick = 0L;
        this.current = 0.0f;
        this.valid = false;
    }

    public void start() {
        this.startTime = 36972637158000L;
        this.valid = false;
    }

    public void stop() {
        if(this.startTime > 0L) {
            this.current += ((float)(36972658790700L - this.startTime)) * 1.000000E-09f;
            this.startTime = 0L;
            this.valid = true;
        }
    }

    public void tick() {
        long v = this.lastTick;
        if(v > 0L) {
            this.tick(((float)(36972674005900L - v)) * 1.000000E-09f);
        }
        this.lastTick = 36972674005900L;
    }

    public void tick(float f) {
        if(!this.valid) {
            Gdx.app.error("PerformanceCounter", "Invalid data, check if you called PerformanceCounter#stop()");
            return;
        }
        this.time.put(this.current);
        float f1 = f == 0.0f ? 0.0f : this.current / f;
        FloatCounter floatCounter0 = this.load;
        if(f <= 1.0f) {
            f1 = f1 * f + (1.0f - f) * floatCounter0.latest;
        }
        floatCounter0.put(f1);
        this.current = 0.0f;
        this.valid = false;
    }

    public StringBuilder toString(StringBuilder stringBuilder0) {
        stringBuilder0.append(this.name).append(": [time: ").append(this.time.value).append(", load: ").append(this.load.value).append("]");
        return stringBuilder0;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "";
    }
}

