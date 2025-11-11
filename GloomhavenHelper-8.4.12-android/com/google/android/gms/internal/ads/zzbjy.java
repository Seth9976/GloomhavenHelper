package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbjy implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfeh;

    private zzbjy(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzfeh = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbjz(((Context)this.zzelc.get()), ((zzpo)this.zzfeh.get()));
    }

    public static zzbjy zza(zzeew zzeew0, zzeew zzeew1) {
        return new zzbjy(zzeew0, zzeew1);
    }
}

