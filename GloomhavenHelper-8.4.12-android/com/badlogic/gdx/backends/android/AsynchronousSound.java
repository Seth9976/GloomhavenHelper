package com.badlogic.gdx.backends.android;

import android.os.Handler;
import com.badlogic.gdx.audio.Sound;

public class AsynchronousSound implements Sound {
    private final Handler handler;
    private final Sound sound;

    public AsynchronousSound(Sound sound0, Handler handler0) {
        this.sound = sound0;
        this.handler = handler0;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void dispose() {
        this.sound.dispose();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop() {
        com.badlogic.gdx.backends.android.AsynchronousSound.4 asynchronousSound$40 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.loop();
            }
        };
        this.handler.post(asynchronousSound$40);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop(float f) {
        com.badlogic.gdx.backends.android.AsynchronousSound.5 asynchronousSound$50 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.loop(f);
            }
        };
        this.handler.post(asynchronousSound$50);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop(float f, float f1, float f2) {
        com.badlogic.gdx.backends.android.AsynchronousSound.6 asynchronousSound$60 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.loop(f, f1, f2);
            }
        };
        this.handler.post(asynchronousSound$60);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void pause() {
        this.sound.pause();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void pause(long v) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play() {
        com.badlogic.gdx.backends.android.AsynchronousSound.1 asynchronousSound$10 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.play();
            }
        };
        this.handler.post(asynchronousSound$10);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play(float f) {
        com.badlogic.gdx.backends.android.AsynchronousSound.2 asynchronousSound$20 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.play(f);
            }
        };
        this.handler.post(asynchronousSound$20);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play(float f, float f1, float f2) {
        com.badlogic.gdx.backends.android.AsynchronousSound.3 asynchronousSound$30 = new Runnable() {
            @Override
            public void run() {
                AsynchronousSound.this.sound.play(f, f1, f2);
            }
        };
        this.handler.post(asynchronousSound$30);
        return 0L;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void resume() {
        this.sound.resume();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void resume(long v) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setLooping(long v, boolean z) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setPan(long v, float f, float f1) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setPitch(long v, float f) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setVolume(long v, float f) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void stop() {
        this.sound.stop();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void stop(long v) {
        throw new UnsupportedOperationException("Asynchronous audio doesn\'t support sound id based operations.");
    }
}

