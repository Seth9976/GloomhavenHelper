package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcyc implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfjj;

    private zzcyc(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzfjj = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcyc.zza(((zzdoe)this.zzfev.get()), ((Context)this.zzfjj.get()));
    }

    public static zzcxv zza(zzdoe zzdoe0, Context context0) {
        return new zzcxv(zzdoe0, context0);
    }

    public static zzcyc zzaz(zzeew zzeew0, zzeew zzeew1) {
        return new zzcyc(zzeew0, zzeew1);
    }
}

