package com.badlogic.gdx.backends.android;

import android.media.AudioManager;
import android.media.SoundPool;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntArray;

final class AndroidSound implements Sound {
    final AudioManager manager;
    final int soundId;
    final SoundPool soundPool;
    final IntArray streamIds;

    AndroidSound(SoundPool soundPool0, AudioManager audioManager0, int v) {
        this.streamIds = new IntArray(8);
        this.soundPool = soundPool0;
        this.manager = audioManager0;
        this.soundId = v;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void dispose() {
        this.soundPool.unload(this.soundId);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop() {
        return this.loop(1.0f);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop(float f) {
        if(this.streamIds.size == 8) {
            this.streamIds.pop();
        }
        int v = this.soundPool.play(this.soundId, f, f, 1, -1, 1.0f);
        if(v == 0) {
            return -1L;
        }
        this.streamIds.insert(0, v);
        return (long)v;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long loop(float f, float f1, float f2) {
        float f4;
        float f3;
        if(this.streamIds.size == 8) {
            this.streamIds.pop();
        }
        if(f2 < 0.0f) {
            f3 = f;
            f4 = f * (1.0f - Math.abs(f2));
        }
        else if(f2 > 0.0f) {
            f4 = f;
            f3 = f * (1.0f - Math.abs(f2));
        }
        else {
            f3 = f;
            f4 = f3;
        }
        int v = this.soundPool.play(this.soundId, f3, f4, 1, -1, f1);
        if(v == 0) {
            return -1L;
        }
        this.streamIds.insert(0, v);
        return (long)v;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void pause() {
        this.soundPool.autoPause();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void pause(long v) {
        this.soundPool.pause(((int)v));
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play() {
        return this.play(1.0f);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play(float f) {
        if(this.streamIds.size == 8) {
            this.streamIds.pop();
        }
        int v = this.soundPool.play(this.soundId, f, f, 1, 0, 1.0f);
        if(v == 0) {
            return -1L;
        }
        this.streamIds.insert(0, v);
        return (long)v;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public long play(float f, float f1, float f2) {
        float f4;
        float f3;
        if(this.streamIds.size == 8) {
            this.streamIds.pop();
        }
        if(f2 < 0.0f) {
            f3 = f;
            f4 = f * (1.0f - Math.abs(f2));
        }
        else if(f2 > 0.0f) {
            f4 = f;
            f3 = f * (1.0f - Math.abs(f2));
        }
        else {
            f3 = f;
            f4 = f3;
        }
        int v = this.soundPool.play(this.soundId, f3, f4, 1, 0, f1);
        if(v == 0) {
            return -1L;
        }
        this.streamIds.insert(0, v);
        return (long)v;
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void resume() {
        this.soundPool.autoResume();
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void resume(long v) {
        this.soundPool.resume(((int)v));
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setLooping(long v, boolean z) {
        this.soundPool.pause(((int)v));
        this.soundPool.setLoop(((int)v), (z ? -1 : 0));
        this.soundPool.resume(((int)v));
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setPan(long v, float f, float f1) {
        float f2;
        if(f < 0.0f) {
            f2 = f1 * (1.0f - Math.abs(f));
        }
        else if(f > 0.0f) {
            float f3 = f1;
            f1 *= 1.0f - Math.abs(f);
            f2 = f3;
        }
        else {
            f2 = f1;
        }
        this.soundPool.setVolume(((int)v), f1, f2);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setPitch(long v, float f) {
        this.soundPool.setRate(((int)v), f);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void setVolume(long v, float f) {
        this.soundPool.setVolume(((int)v), f, f);
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void stop() {
        int v = this.streamIds.size;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = this.streamIds.get(v1);
            this.soundPool.stop(v2);
        }
    }

    @Override  // com.badlogic.gdx.audio.Sound
    public void stop(long v) {
        this.soundPool.stop(((int)v));
    }
}

