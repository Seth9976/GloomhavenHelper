package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcxn implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfdd;
    private final zzeew zzfev;

    private zzcxn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfdd = zzeew0;
        this.zzfev = zzeew1;
        this.zzelc = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcxl(((zzaui)this.zzfdd.get()), ((zzdoe)this.zzfev.get()), ((Context)this.zzelc.get()));
    }

    public static zzcxn zzq(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcxn(zzeew0, zzeew1, zzeew2);
    }
}

