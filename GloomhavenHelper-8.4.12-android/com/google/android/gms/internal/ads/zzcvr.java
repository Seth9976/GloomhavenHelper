package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcvr implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;

    private zzcvr(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcvp(((Context)this.zzelc.get()), ((zzdoe)this.zzfev.get()));
    }

    public static zzcvr zzau(zzeew zzeew0, zzeew zzeew1) {
        return new zzcvr(zzeew0, zzeew1);
    }
}

