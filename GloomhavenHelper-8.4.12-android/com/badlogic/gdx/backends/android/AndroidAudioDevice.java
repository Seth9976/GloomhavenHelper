package com.badlogic.gdx.backends.android;

import android.media.AudioTrack;
import com.badlogic.gdx.audio.AudioDevice;

class AndroidAudioDevice implements AudioDevice {
    private short[] buffer;
    private final boolean isMono;
    private final int latency;
    private final AudioTrack track;

    AndroidAudioDevice(int v, boolean z) {
        this.buffer = new short[0x400];
        this.isMono = z;
        int v1 = 2;
        int v2 = AudioTrack.getMinBufferSize(v, (z ? 4 : 12), 2);
        this.track = new AudioTrack(3, v, (z ? 4 : 12), 2, v2, 1);
        this.track.play();
        if(z) {
            v1 = 1;
        }
        this.latency = v2 / v1;
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public void dispose() {
        this.track.stop();
        this.track.release();
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public int getLatency() {
        return this.latency;
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public boolean isMono() {
        return this.isMono;
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public void setVolume(float f) {
        this.track.setStereoVolume(f, f);
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(float[] arr_f, int v, int v1) {
        if(this.buffer.length < arr_f.length) {
            this.buffer = new short[arr_f.length];
        }
        int v2 = v + v1;
        for(int v3 = 0; v < v2; ++v3) {
            float f = arr_f[v];
            if(f > 1.0f) {
                f = 1.0f;
            }
            if(f < -1.0f) {
                f = -1.0f;
            }
            this.buffer[v3] = (short)(((int)(f * 32767.0f)));
            ++v;
        }
        for(int v4 = this.track.write(this.buffer, 0, v1); v4 != v1; v4 += this.track.write(this.buffer, v4, v1 - v4)) {
        }
    }

    @Override  // com.badlogic.gdx.audio.AudioDevice
    public void writeSamples(short[] arr_v, int v, int v1) {
        for(int v2 = this.track.write(arr_v, v, v1); v2 != v1; v2 += this.track.write(arr_v, v + v2, v1 - v2)) {
        }
    }
}

