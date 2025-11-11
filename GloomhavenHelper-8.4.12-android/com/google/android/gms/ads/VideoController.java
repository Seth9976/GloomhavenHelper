package com.google.android.gms.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzxj;
import com.google.android.gms.internal.ads.zzzd;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.FIN;

public final class VideoController {
    public static class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;
    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;
    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN;
    private final Object lock;
    @Nullable
    @GuardedBy("lock")
    private zzxj zzacb;
    @Nullable
    @GuardedBy("lock")
    private VideoLifecycleCallbacks zzacc;

    public VideoController() {
        this.lock = new Object();
    }

    @Deprecated
    public final float getAspectRatio() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return 0.0f;
        }
        try {
            float f = this.zzacb.getAspectRatio();
            FIN.finallyExec$NT(v);
            return f;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call getAspectRatio on video controller.", remoteException0);
            FIN.finallyExec$NT(v);
            return 0.0f;
        }
    }

    @KeepForSdk
    public final int getPlaybackState() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return 0;
        }
        try {
            int v1 = this.zzacb.getPlaybackState();
            FIN.finallyExec$NT(v);
            return v1;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call getPlaybackState on video controller.", remoteException0);
            FIN.finallyExec$NT(v);
            return 0;
        }
    }

    public final float getVideoCurrentTime() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return 0.0f;
        }
        try {
            float f = this.zzacb.getCurrentTime();
            FIN.finallyExec$NT(v);
            return f;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call getCurrentTime on video controller.", remoteException0);
            FIN.finallyExec$NT(v);
            return 0.0f;
        }
    }

    public final float getVideoDuration() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return 0.0f;
        }
        try {
            float f = this.zzacb.getDuration();
            FIN.finallyExec$NT(v);
            return f;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call getDuration on video controller.", remoteException0);
            FIN.finallyExec$NT(v);
            return 0.0f;
        }
    }

    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        synchronized(this.lock) {
        }
        return this.zzacc;
    }

    public final boolean hasVideoContent() {
        synchronized(this.lock) {
        }
        return this.zzacb != null;
    }

    public final boolean isClickToExpandEnabled() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return false;
        }
        try {
            boolean z = this.zzacb.isClickToExpandEnabled();
            FIN.finallyExec$NT(v);
            return z;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call isClickToExpandEnabled.", remoteException0);
            FIN.finallyExec$NT(v);
            return false;
        }
    }

    public final boolean isCustomControlsEnabled() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return false;
        }
        try {
            boolean z = this.zzacb.isCustomControlsEnabled();
            FIN.finallyExec$NT(v);
            return z;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call isUsingCustomPlayerControls.", remoteException0);
            FIN.finallyExec$NT(v);
            return false;
        }
    }

    public final boolean isMuted() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzacb == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return true;
        }
        try {
            boolean z = this.zzacb.isMuted();
            FIN.finallyExec$NT(v);
            return z;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call isMuted on video controller.", remoteException0);
            FIN.finallyExec$NT(v);
            return true;
        }
    }

    public final void mute(boolean z) {
        synchronized(this.lock) {
            if(this.zzacb == null) {
                return;
            }
            try {
                this.zzacb.mute(z);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call mute on video controller.", remoteException0);
            }
        }
    }

    public final void pause() {
        synchronized(this.lock) {
            if(this.zzacb == null) {
                return;
            }
            try {
                this.zzacb.pause();
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call pause on video controller.", remoteException0);
            }
        }
    }

    public final void play() {
        synchronized(this.lock) {
            if(this.zzacb == null) {
                return;
            }
            try {
                this.zzacb.play();
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call play on video controller.", remoteException0);
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoController$VideoLifecycleCallbacks0) {
        Preconditions.checkNotNull(videoController$VideoLifecycleCallbacks0, "VideoLifecycleCallbacks may not be null.");
        synchronized(this.lock) {
            this.zzacc = videoController$VideoLifecycleCallbacks0;
            if(this.zzacb == null) {
                return;
            }
            try {
                this.zzacb.zza(new zzzd(videoController$VideoLifecycleCallbacks0));
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call setVideoLifecycleCallbacks on video controller.", remoteException0);
            }
        }
    }

    public final void stop() {
        synchronized(this.lock) {
            if(this.zzacb == null) {
                return;
            }
            try {
                this.zzacb.stop();
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call stop on video controller.", remoteException0);
            }
        }
    }

    public final void zza(zzxj zzxj0) {
        synchronized(this.lock) {
            this.zzacb = zzxj0;
            if(this.zzacc != null) {
                this.setVideoLifecycleCallbacks(this.zzacc);
            }
        }
    }

    public final zzxj zzdq() {
        synchronized(this.lock) {
        }
        return this.zzacb;
    }
}

