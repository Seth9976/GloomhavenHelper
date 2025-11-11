package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbkf implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffe;
    private final zzeew zzfff;

    private zzbkf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzffe = zzeew0;
        this.zzfff = zzeew1;
        this.zzfev = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbjs)zzeep.zza(new zzbjs(((zzpo)this.zzffe.get()).zzkr(), ((zzakt)this.zzfff.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbkf zzb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbkf(zzeew0, zzeew1, zzeew2);
    }
}

