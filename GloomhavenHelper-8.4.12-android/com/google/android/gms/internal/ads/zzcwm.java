package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;

public final class zzcwm implements zzeej {
    private final zzeew zzerc;
    private final zzeew zzfev;
    private final zzeew zzfgu;
    private final zzeew zzfju;

    public zzcwm(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfev = zzeew0;
        this.zzfgu = zzeew1;
        this.zzfju = zzeew2;
        this.zzerc = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcwf(((zzdoe)this.zzfev.get()), ((zzdeu)this.zzfgu.get()), ((PackageInfo)this.zzfju.get()), ((zzawh)this.zzerc.get()));
    }
}

