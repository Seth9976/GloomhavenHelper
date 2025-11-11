package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

public interface AudioDevice extends Disposable {
    @Override  // com.badlogic.gdx.utils.Disposable
    void dispose();

    int getLatency();

    boolean isMono();

    void setVolume(float arg1);

    void writeSamples(float[] arg1, int arg2, int arg3);

    void writeSamples(short[] arg1, int arg2, int arg3);
}

