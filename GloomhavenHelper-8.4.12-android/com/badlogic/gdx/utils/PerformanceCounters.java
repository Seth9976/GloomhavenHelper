package com.badlogic.gdx.utils;

public class PerformanceCounters {
    public final Array counters;
    private long lastTick;
    private static final float nano2seconds = 1.000000E-09f;

    public PerformanceCounters() {
        this.lastTick = 0L;
        this.counters = new Array();
    }

    public PerformanceCounter add(String s) {
        PerformanceCounter performanceCounter0 = new PerformanceCounter(s);
        this.counters.add(performanceCounter0);
        return performanceCounter0;
    }

    public PerformanceCounter add(String s, int v) {
        PerformanceCounter performanceCounter0 = new PerformanceCounter(s, v);
        this.counters.add(performanceCounter0);
        return performanceCounter0;
    }

    public void tick() {
        long v = this.lastTick;
        if(v > 0L) {
            this.tick(((float)(36972913858200L - v)) * 1.000000E-09f);
        }
        this.lastTick = 36972913858200L;
    }

    public void tick(float f) {
        for(int v = 0; v < this.counters.size; ++v) {
            ((PerformanceCounter)this.counters.get(v)).tick(f);
        }
    }

    public StringBuilder toString(StringBuilder stringBuilder0) {
        stringBuilder0.setLength(0);
        for(int v = 0; v < this.counters.size; ++v) {
            if(v != 0) {
                stringBuilder0.append("; ");
            }
            ((PerformanceCounter)this.counters.get(v)).toString(stringBuilder0);
        }
        return stringBuilder0;
    }
}

