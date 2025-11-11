package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

public interface Sound extends Disposable {
    @Override  // com.badlogic.gdx.utils.Disposable
    void dispose();

    long loop();

    long loop(float arg1);

    long loop(float arg1, float arg2, float arg3);

    void pause();

    void pause(long arg1);

    long play();

    long play(float arg1);

    long play(float arg1, float arg2, float arg3);

    void resume();

    void resume(long arg1);

    void setLooping(long arg1, boolean arg2);

    void setPan(long arg1, float arg2, float arg3);

    void setPitch(long arg1, float arg2);

    void setVolume(long arg1, float arg2);

    void stop();

    void stop(long arg1);
}

