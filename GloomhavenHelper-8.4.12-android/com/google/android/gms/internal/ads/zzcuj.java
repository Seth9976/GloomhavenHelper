package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcuj implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzgic;

    private zzcuj(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzgic = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcuj.zza(((Executor)this.zzfev.get()), ((zzavr)this.zzgic.get()));
    }

    public static zzcui zza(Executor executor0, zzavr zzavr0) {
        return new zzcui(executor0, zzavr0);
    }

    public static zzcuj zzar(zzeew zzeew0, zzeew zzeew1) {
        return new zzcuj(zzeew0, zzeew1);
    }
}

