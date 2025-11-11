package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes.Builder;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool.Builder;
import android.media.SoundPool;
import android.os.Build.VERSION;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultAndroidAudio implements AndroidAudio {
    private final AudioManager manager;
    private final List musics;
    private final SoundPool soundPool;

    public DefaultAndroidAudio(Context context0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.musics = new ArrayList();
        if(androidApplicationConfiguration0.disableAudio) {
            this.soundPool = null;
            this.manager = null;
        }
        else {
            if(Build.VERSION.SDK_INT >= 21) {
                AudioAttributes audioAttributes0 = new AudioAttributes.Builder().setUsage(14).setContentType(4).build();
                this.soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes0).setMaxStreams(androidApplicationConfiguration0.maxSimultaneousSounds).build();
            }
            else {
                this.soundPool = new SoundPool(androidApplicationConfiguration0.maxSimultaneousSounds, 3, 0);
            }
            this.manager = (AudioManager)context0.getSystemService("audio");
            if(context0 instanceof Activity) {
                ((Activity)context0).setVolumeControlStream(3);
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.soundPool == null) {
            return;
        }
        synchronized(this.musics) {
            for(Object object0: new ArrayList(this.musics)) {
                ((AndroidMusic)object0).dispose();
            }
        }
        this.soundPool.release();
    }

    @Override  // com.badlogic.gdx.Audio
    public AudioDevice newAudioDevice(int v, boolean z) {
        if(this.soundPool == null) {
            throw new GdxRuntimeException("Android audio is not enabled by the application config.");
        }
        return new AndroidAudioDevice(v, z);
    }

    @Override  // com.badlogic.gdx.Audio
    public AudioRecorder newAudioRecorder(int v, boolean z) {
        if(this.soundPool == null) {
            throw new GdxRuntimeException("Android audio is not enabled by the application config.");
        }
        return new AndroidAudioRecorder(v, z);
    }

    @Override  // com.badlogic.gdx.Audio
    public Music newMusic(FileHandle fileHandle0) {
        if(this.soundPool != null) {
            AndroidFileHandle androidFileHandle0 = (AndroidFileHandle)fileHandle0;
            MediaPlayer mediaPlayer0 = new MediaPlayer();
            if(androidFileHandle0.type() == FileType.Internal) {
                try {
                    AssetFileDescriptor assetFileDescriptor0 = androidFileHandle0.getAssetFileDescriptor();
                    mediaPlayer0.setDataSource(assetFileDescriptor0.getFileDescriptor(), assetFileDescriptor0.getStartOffset(), assetFileDescriptor0.getLength());
                    assetFileDescriptor0.close();
                    mediaPlayer0.prepare();
                    Music music0 = new AndroidMusic(this, mediaPlayer0);
                    synchronized(this.musics) {
                        this.musics.add(music0);
                        return music0;
                    }
                }
                catch(Exception exception0) {
                    throw new GdxRuntimeException("Error loading audio file: " + fileHandle0 + "\nNote: Internal audio files must be placed in the assets directory.", exception0);
                }
            }
            try {
                mediaPlayer0.setDataSource(androidFileHandle0.file().getPath());
                mediaPlayer0.prepare();
                Music music1 = new AndroidMusic(this, mediaPlayer0);
                synchronized(this.musics) {
                    this.musics.add(music1);
                    return music1;
                }
            }
            catch(Exception exception1) {
                throw new GdxRuntimeException("Error loading audio file: " + fileHandle0, exception1);
            }
        }
        throw new GdxRuntimeException("Android audio is not enabled by the application config.");
    }

    public Music newMusic(FileDescriptor fileDescriptor0) {
        if(this.soundPool != null) {
            MediaPlayer mediaPlayer0 = new MediaPlayer();
            try {
                mediaPlayer0.setDataSource(fileDescriptor0);
                mediaPlayer0.prepare();
                Music music0 = new AndroidMusic(this, mediaPlayer0);
                synchronized(this.musics) {
                    this.musics.add(music0);
                    return music0;
                }
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error loading audio from FileDescriptor", exception0);
            }
        }
        throw new GdxRuntimeException("Android audio is not enabled by the application config.");
    }

    @Override  // com.badlogic.gdx.Audio
    public Sound newSound(FileHandle fileHandle0) {
        if(this.soundPool != null) {
            AndroidFileHandle androidFileHandle0 = (AndroidFileHandle)fileHandle0;
            if(androidFileHandle0.type() == FileType.Internal) {
                try {
                    AssetFileDescriptor assetFileDescriptor0 = androidFileHandle0.getAssetFileDescriptor();
                    int v = this.soundPool.load(assetFileDescriptor0, 1);
                    Sound sound0 = new AndroidSound(this.soundPool, this.manager, v);
                    assetFileDescriptor0.close();
                    return sound0;
                }
                catch(IOException iOException0) {
                    throw new GdxRuntimeException("Error loading audio file: " + fileHandle0 + "\nNote: Internal audio files must be placed in the assets directory.", iOException0);
                }
            }
            try {
                String s = androidFileHandle0.file().getPath();
                int v1 = this.soundPool.load(s, 1);
                return new AndroidSound(this.soundPool, this.manager, v1);
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error loading audio file: " + fileHandle0, exception0);
            }
        }
        throw new GdxRuntimeException("Android audio is not enabled by the application config.");
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidAudio
    public void notifyMusicDisposed(AndroidMusic androidMusic0) {
        synchronized(this.musics) {
            this.musics.remove(this);
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidAudio
    public void pause() {
        if(this.soundPool == null) {
            return;
        }
        synchronized(this.musics) {
            for(Object object0: this.musics) {
                AndroidMusic androidMusic0 = (AndroidMusic)object0;
                if(androidMusic0.isPlaying()) {
                    androidMusic0.pause();
                    androidMusic0.wasPlaying = true;
                }
                else {
                    androidMusic0.wasPlaying = false;
                }
            }
        }
        this.soundPool.autoPause();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidAudio
    public void resume() {
        if(this.soundPool == null) {
            return;
        }
        synchronized(this.musics) {
            for(int v1 = 0; v1 < this.musics.size(); ++v1) {
                if(((AndroidMusic)this.musics.get(v1)).wasPlaying) {
                    ((AndroidMusic)this.musics.get(v1)).play();
                }
            }
        }
        this.soundPool.autoResume();
    }
}

