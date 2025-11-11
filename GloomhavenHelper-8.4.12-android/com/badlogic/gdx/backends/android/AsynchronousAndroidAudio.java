package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class AsynchronousAndroidAudio extends DefaultAndroidAudio {
    private final Handler handler;
    private final HandlerThread handlerThread;

    public AsynchronousAndroidAudio(Context context0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        super(context0, androidApplicationConfiguration0);
        if(!androidApplicationConfiguration0.disableAudio) {
            this.handlerThread = new HandlerThread("libGDX Sound Management");
            this.handlerThread.start();
            this.handler = new Handler(this.handlerThread.getLooper());
            return;
        }
        this.handler = null;
        this.handlerThread = null;
    }

    @Override  // com.badlogic.gdx.backends.android.DefaultAndroidAudio
    public void dispose() {
        super.dispose();
        HandlerThread handlerThread0 = this.handlerThread;
        if(handlerThread0 != null) {
            handlerThread0.quit();
        }
    }

    @Override  // com.badlogic.gdx.backends.android.DefaultAndroidAudio
    public Sound newSound(FileHandle fileHandle0) {
        return new AsynchronousSound(super.newSound(fileHandle0), this.handler);
    }
}

