package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;

public class FPSLogger {
    int bound;
    long startTime;

    public FPSLogger() {
        this(0x7FFFFFFF);
    }

    public FPSLogger(int v) {
        this.bound = v;
        this.startTime = 0x2199159051F8L;
    }

    public void log() {
        if(36941406015100L - this.startTime > 1000000000L) {
            int v = Gdx.graphics.getFramesPerSecond();
            if(v < this.bound) {
                Gdx.app.log("FPSLogger", "fps: " + v);
                this.startTime = 36941406015100L;
            }
        }
    }
}

