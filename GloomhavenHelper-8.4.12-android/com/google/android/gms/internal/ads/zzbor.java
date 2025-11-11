package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbor implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffv;

    private zzbor(zzeew zzeew0, zzeew zzeew1) {
        this.zzffv = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbvd)this.zzffv.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbor zzf(zzeew zzeew0, zzeew zzeew1) {
        return new zzbor(zzeew0, zzeew1);
    }
}

