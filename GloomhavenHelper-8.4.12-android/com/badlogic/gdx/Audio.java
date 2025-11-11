package com.badlogic.gdx;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public interface Audio {
    AudioDevice newAudioDevice(int arg1, boolean arg2);

    AudioRecorder newAudioRecorder(int arg1, boolean arg2);

    Music newMusic(FileHandle arg1);

    Sound newSound(FileHandle arg1);
}

