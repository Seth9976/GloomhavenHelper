package com.badlogic.gdx.backends.android;

import android.media.AudioRecord;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AndroidAudioRecorder implements AudioRecorder {
    private AudioRecord recorder;

    public AndroidAudioRecorder(int v, boolean z) {
        this.recorder = new AudioRecord(1, v, (z ? 16 : 12), 2, AudioRecord.getMinBufferSize(v, (z ? 16 : 12), 2));
        if(this.recorder.getState() != 1) {
            throw new GdxRuntimeException("Unable to initialize AudioRecorder.\nDo you have the RECORD_AUDIO permission?");
        }
        this.recorder.startRecording();
    }

    @Override  // com.badlogic.gdx.audio.AudioRecorder
    public void dispose() {
        this.recorder.stop();
        this.recorder.release();
    }

    @Override  // com.badlogic.gdx.audio.AudioRecorder
    public void read(short[] arr_v, int v, int v1) {
        for(int v2 = 0; v2 != v1; v2 += this.recorder.read(arr_v, v + v2, v1 - v2)) {
        }
    }
}

