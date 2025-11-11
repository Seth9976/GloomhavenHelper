package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbov implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffv;

    private zzbov(zzeew zzeew0, zzeew zzeew1) {
        this.zzffv = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbov.zza(((zzbjq)this.zzffv.get()), ((Executor)this.zzfev.get()));
    }

    public static zzbuv zza(zzbjq zzbjq0, Executor executor0) {
        return (zzbuv)zzeep.zza(new zzbuv(zzbjq0, executor0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbov zzi(zzeew zzeew0, zzeew zzeew1) {
        return new zzbov(zzeew0, zzeew1);
    }
}

