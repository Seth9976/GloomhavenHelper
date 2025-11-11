package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.utils.Disposable;

public interface AndroidAudio extends Audio, Disposable {
    void notifyMusicDisposed(AndroidMusic arg1);

    void pause();

    void resume();
}

