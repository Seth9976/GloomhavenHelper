package com.badlogic.gdx.backends.android;

import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Music;
import java.io.IOException;
import jeb.synthetic.FIN;

public class AndroidMusic implements MediaPlayer.OnCompletionListener, Music {
    private final AndroidAudio audio;
    private boolean isPrepared;
    protected OnCompletionListener onCompletionListener;
    private MediaPlayer player;
    private float volume;
    protected boolean wasPlaying;

    AndroidMusic(AndroidAudio androidAudio0, MediaPlayer mediaPlayer0) {
        this.isPrepared = true;
        this.wasPlaying = false;
        this.volume = 1.0f;
        this.audio = androidAudio0;
        this.player = mediaPlayer0;
        this.onCompletionListener = null;
        this.player.setOnCompletionListener(this);
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void dispose() {
        int v;
        try {
            MediaPlayer mediaPlayer0 = this.player;
            if(mediaPlayer0 == null) {
                return;
            }
            v = FIN.finallyOpen$NT();
            mediaPlayer0.release();
        }
        catch(Throwable unused_ex) {
            Gdx.app.log("AndroidMusic", "error while disposing AndroidMusic instance, non-fatal");
        }
        FIN.finallyCodeBegin$NT(v);
        this.player = null;
        this.onCompletionListener = null;
        this.audio.notifyMusicDisposed(this);
        FIN.finallyCodeEnd$NT(v);
    }

    public float getDuration() {
        return this.player == null ? 0.0f : ((float)this.player.getDuration()) / 1000.0f;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public float getPosition() {
        return this.player == null ? 0.0f : ((float)this.player.getCurrentPosition()) / 1000.0f;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public float getVolume() {
        return this.volume;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public boolean isLooping() {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return false;
        }
        try {
            return mediaPlayer0.isLooping();
        }
        catch(Exception exception0) {
            exception0.printStackTrace();
            return false;
        }
    }

    @Override  // com.badlogic.gdx.audio.Music
    public boolean isPlaying() {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return false;
        }
        try {
            return mediaPlayer0.isPlaying();
        }
        catch(Exception exception0) {
            exception0.printStackTrace();
            return false;
        }
    }

    @Override  // android.media.MediaPlayer$OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer0) {
        if(this.onCompletionListener != null) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    AndroidMusic.this.onCompletionListener.onCompletion(AndroidMusic.this);
                }
            });
        }
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void pause() {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        try {
            if(mediaPlayer0.isPlaying()) {
                this.player.pause();
            }
        }
        catch(Exception exception0) {
            exception0.printStackTrace();
        }
        this.wasPlaying = false;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void play() {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        try {
            if(mediaPlayer0.isPlaying()) {
                return;
            }
        }
        catch(Exception exception0) {
            exception0.printStackTrace();
            return;
        }
        try {
            if(!this.isPrepared) {
                this.player.prepare();
                this.isPrepared = true;
            }
            this.player.start();
        }
        catch(IllegalStateException illegalStateException0) {
            illegalStateException0.printStackTrace();
        }
        catch(IOException iOException0) {
            iOException0.printStackTrace();
        }
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void setLooping(boolean z) {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        mediaPlayer0.setLooping(z);
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void setOnCompletionListener(OnCompletionListener music$OnCompletionListener0) {
        this.onCompletionListener = music$OnCompletionListener0;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void setPan(float f, float f1) {
        float f3;
        float f2;
        if(this.player == null) {
            return;
        }
        if(f < 0.0f) {
            f2 = f1 * (1.0f - Math.abs(f));
            f3 = f1;
        }
        else if(f > 0.0f) {
            f3 = f1 * (1.0f - Math.abs(f));
            f2 = f1;
        }
        else {
            f3 = f1;
            f2 = f3;
        }
        this.player.setVolume(f3, f2);
        this.volume = f1;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void setPosition(float f) {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        try {
            if(!this.isPrepared) {
                mediaPlayer0.prepare();
                this.isPrepared = true;
            }
            this.player.seekTo(((int)(f * 1000.0f)));
        }
        catch(IllegalStateException illegalStateException0) {
            illegalStateException0.printStackTrace();
        }
        catch(IOException iOException0) {
            iOException0.printStackTrace();
        }
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void setVolume(float f) {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        mediaPlayer0.setVolume(f, f);
        this.volume = f;
    }

    @Override  // com.badlogic.gdx.audio.Music
    public void stop() {
        MediaPlayer mediaPlayer0 = this.player;
        if(mediaPlayer0 == null) {
            return;
        }
        if(this.isPrepared) {
            mediaPlayer0.seekTo(0);
        }
        this.player.stop();
        this.isPrepared = false;
    }
}

