package com.badlogic.gdx.audio;

import com.badlogic.gdx.utils.Disposable;

public interface Music extends Disposable {
    public interface OnCompletionListener {
        void onCompletion(Music arg1);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    void dispose();

    float getPosition();

    float getVolume();

    boolean isLooping();

    boolean isPlaying();

    void pause();

    void play();

    void setLooping(boolean arg1);

    void setOnCompletionListener(OnCompletionListener arg1);

    void setPan(float arg1, float arg2);

    void setPosition(float arg1);

    void setVolume(float arg1);

    void stop();
}

