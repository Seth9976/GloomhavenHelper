package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.AudioManager;

@TargetApi(14)
public final class zzbbn implements AudioManager.OnAudioFocusChangeListener {
    private float zzcy;
    private boolean zzdzg;
    private final AudioManager zzecg;
    private final zzbbq zzech;
    private boolean zzeci;
    private boolean zzecj;

    public zzbbn(Context context0, zzbbq zzbbq0) {
        this.zzcy = 1.0f;
        this.zzecg = (AudioManager)context0.getSystemService("audio");
        this.zzech = zzbbq0;
    }

    // 去混淆评级： 低(40)
    public final float getVolume() {
        return this.zzeci ? (this.zzecj ? 0.0f : this.zzcy) : 0.0f;
    }

    @Override  // android.media.AudioManager$OnAudioFocusChangeListener
    public final void onAudioFocusChange(int v) {
        this.zzeci = v > 0;
        this.zzech.zzxx();
    }

    public final void setMuted(boolean z) {
        this.zzecj = z;
        this.zzza();
    }

    public final void setVolume(float f) {
        this.zzcy = f;
        this.zzza();
    }

    public final void zzyn() {
        this.zzdzg = true;
        this.zzza();
    }

    public final void zzyo() {
        this.zzdzg = false;
        this.zzza();
    }

    private final void zzza() {
        boolean z = false;
        boolean z1 = this.zzdzg && !this.zzecj && this.zzcy > 0.0f;
        if(z1 && !this.zzeci) {
            AudioManager audioManager0 = this.zzecg;
            if(audioManager0 != null) {
                if(audioManager0.requestAudioFocus(this, 3, 2) == 1) {
                    z = true;
                }
                this.zzeci = z;
            }
            this.zzech.zzxx();
            return;
        }
        if(!z1 && this.zzeci) {
            AudioManager audioManager1 = this.zzecg;
            if(audioManager1 != null) {
                if(audioManager1.abandonAudioFocus(this) == 0) {
                    z = true;
                }
                this.zzeci = z;
            }
            this.zzech.zzxx();
        }
    }
}

