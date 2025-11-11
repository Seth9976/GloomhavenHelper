package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

public final class zzcyi implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzgkw;

    private zzcyi(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzgkw = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcyi.zza(((Executor)this.zzfev.get()), ((Set)this.zzgkw.get()));
    }

    public static zzcyd zza(Executor executor0, Set set0) {
        return new zzcyd(executor0, set0);
    }

    public static zzcyi zzba(zzeew zzeew0, zzeew zzeew1) {
        return new zzcyi(zzeew0, zzeew1);
    }
}

