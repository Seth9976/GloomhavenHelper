package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbwl implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffv;
    private final zzbvz zzfmc;

    private zzbwl(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfmc = zzbvz0;
        this.zzffv = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbxg)this.zzffv.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwl zzb(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1) {
        return new zzbwl(zzbvz0, zzeew0, zzeew1);
    }
}

