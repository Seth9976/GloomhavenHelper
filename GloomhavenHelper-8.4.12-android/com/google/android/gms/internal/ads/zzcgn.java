package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcgn implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffc;

    private zzcgn(zzeew zzeew0, zzeew zzeew1) {
        this.zzffc = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzcgg)this.zzffc.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcgn zzae(zzeew zzeew0, zzeew zzeew1) {
        return new zzcgn(zzeew0, zzeew1);
    }
}

