package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcyq implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;

    private zzcyq(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzelc = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcyo(((zzdoe)this.zzfev.get()), ((Context)this.zzelc.get()));
    }

    public static zzcyq zzbb(zzeew zzeew0, zzeew zzeew1) {
        return new zzcyq(zzeew0, zzeew1);
    }
}

