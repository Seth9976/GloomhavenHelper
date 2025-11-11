package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcjs implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfdd;

    private zzcjs(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzfdd = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcjt(((Context)this.zzelc.get()), ((zzaui)this.zzfdd.get()));
    }

    public static zzcjs zzak(zzeew zzeew0, zzeew zzeew1) {
        return new zzcjs(zzeew0, zzeew1);
    }
}

