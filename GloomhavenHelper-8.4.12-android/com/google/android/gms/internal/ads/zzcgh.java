package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzcgh implements zzeej {
    private final zzeew zzfve;
    private final zzeew zzfvr;

    private zzcgh(zzeew zzeew0, zzeew zzeew1) {
        this.zzfve = zzeew0;
        this.zzfvr = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgc(((zzsn)this.zzfve.get()), ((Map)this.zzfvr.get()));
    }

    public static zzcgh zzaa(zzeew zzeew0, zzeew zzeew1) {
        return new zzcgh(zzeew0, zzeew1);
    }
}

