package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;

@TargetApi(14)
public abstract class zzbat extends TextureView implements zzbbq {
    protected final zzbbd zzdyy;
    protected final zzbbn zzdyz;

    public zzbat(Context context0) {
        super(context0);
        this.zzdyy = new zzbbd();
        this.zzdyz = new zzbbn(context0, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int arg1);

    public abstract void setVideoPath(String arg1);

    public abstract void stop();

    public abstract void zza(float arg1, float arg2);

    public abstract void zza(zzbau arg1);

    public void zzb(String s, String[] arr_s) {
        this.setVideoPath(s);
    }

    public void zzcv(int v) {
    }

    public void zzcw(int v) {
    }

    public void zzcx(int v) {
    }

    public void zzcy(int v) {
    }

    public void zzcz(int v) {
    }

    public abstract String zzxt();

    @Override  // com.google.android.gms.internal.ads.zzbbq
    public abstract void zzxx();
}

