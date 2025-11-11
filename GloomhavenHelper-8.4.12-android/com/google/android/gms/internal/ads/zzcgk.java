package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcgk implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffc;

    private zzcgk(zzeew zzeew0, zzeew zzeew1) {
        this.zzffc = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzcgg)this.zzffc.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcgk zzac(zzeew zzeew0, zzeew zzeew1) {
        return new zzcgk(zzeew0, zzeew1);
    }
}

