package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcff implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffc;

    private zzcff(zzeew zzeew0, zzeew zzeew1) {
        this.zzffc = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzcfp)this.zzffc.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcff zzu(zzeew zzeew0, zzeew zzeew1) {
        return new zzcff(zzeew0, zzeew1);
    }
}

