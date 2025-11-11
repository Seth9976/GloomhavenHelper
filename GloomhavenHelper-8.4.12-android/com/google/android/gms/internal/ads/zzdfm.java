package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzdfm implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeqr;

    private zzdfm(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzeqr = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdfj(((Context)this.zzelc.get()), ((zzawc)this.zzeqr.get()));
    }

    public static zzdfm zzbc(zzeew zzeew0, zzeew zzeew1) {
        return new zzdfm(zzeew0, zzeew1);
    }
}

