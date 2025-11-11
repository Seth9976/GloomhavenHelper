package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzboq implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffv;

    private zzboq(zzeew zzeew0, zzeew zzeew1) {
        this.zzffv = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbjq)this.zzffv.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzboq zze(zzeew zzeew0, zzeew zzeew1) {
        return new zzboq(zzeew0, zzeew1);
    }
}

