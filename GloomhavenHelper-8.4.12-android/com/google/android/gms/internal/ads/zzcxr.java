package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcxr implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzfev;

    private zzcxr(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfev = zzeew0;
        this.zzelc = zzeew1;
        this.zzfda = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcxp(((zzdoe)this.zzfev.get()), ((Context)this.zzelc.get()), ((zzazo)this.zzfda.get()));
    }

    public static zzcxr zzr(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcxr(zzeew0, zzeew1, zzeew2);
    }
}

