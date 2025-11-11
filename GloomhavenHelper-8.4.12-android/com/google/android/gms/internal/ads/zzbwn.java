package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbwn implements zzeej {
    private final zzeew zzfev;
    private final zzbvz zzfmc;

    private zzbwn(zzbvz zzbvz0, zzeew zzeew0) {
        this.zzfmc = zzbvz0;
        this.zzfev = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Executor executor0 = (Executor)this.zzfev.get();
        return (zzbuv)zzeep.zza(this.zzfmc.zzb(executor0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwn zzd(zzbvz zzbvz0, zzeew zzeew0) {
        return new zzbwn(zzbvz0, zzeew0);
    }
}

