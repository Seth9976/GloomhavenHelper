package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

public final class zzcfm implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffc;

    private zzcfm(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzffc = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Executor executor0 = (Executor)this.zzfev.get();
        zzcgc zzcgc0 = (zzcgc)this.zzffc.get();
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcot)).booleanValue() ? ((Set)zzeep.zza(Collections.singleton(new zzbuv(zzcgc0, executor0)), "Cannot return null from a non-@Nullable @Provides method")) : ((Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    public static zzcfm zzy(zzeew zzeew0, zzeew zzeew1) {
        return new zzcfm(zzeew0, zzeew1);
    }
}

