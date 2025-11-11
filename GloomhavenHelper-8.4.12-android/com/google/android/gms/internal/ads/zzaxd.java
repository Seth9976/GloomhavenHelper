package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

public final class zzaxd {
    private float zzdkj;
    private boolean zzdkp;

    public zzaxd() {
        this.zzdkp = false;
        this.zzdkj = 1.0f;
    }

    public final void setAppMuted(boolean z) {
        synchronized(this) {
            this.zzdkp = z;
        }
    }

    public final void setAppVolume(float f) {
        synchronized(this) {
            this.zzdkj = f;
        }
    }

    public static float zzbh(Context context0) {
        AudioManager audioManager0 = (AudioManager)context0.getSystemService("audio");
        if(audioManager0 == null) {
            return 0.0f;
        }
        int v = audioManager0.getStreamMaxVolume(3);
        return v == 0 ? 0.0f : ((float)audioManager0.getStreamVolume(3)) / ((float)v);
    }

    public final float zzpj() {
        synchronized(this) {
            return this.zzwy() ? this.zzdkj : 1.0f;
        }
    }

    public final boolean zzpk() {
        synchronized(this) {
        }
        return this.zzdkp;
    }

    private final boolean zzwy() {
        synchronized(this) {
        }
        return this.zzdkj >= 0.0f;
    }
}

