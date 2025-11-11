package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzczo implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzevg;
    private final zzeew zzfev;

    public zzczo(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzevg = zzeew0;
        this.zzfev = zzeew1;
        this.zzelc = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzczm(((zzsb)this.zzevg.get()), ((zzdoe)this.zzfev.get()), ((Context)this.zzelc.get()));
    }
}

