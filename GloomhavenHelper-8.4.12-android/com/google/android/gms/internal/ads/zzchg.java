package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

public final class zzchg implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfve;
    private final zzchc zzfwl;

    private zzchg(zzchc zzchc0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfwl = zzchc0;
        this.zzfve = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzchm zzchm0 = (zzchm)this.zzfve.get();
        Executor executor0 = (Executor)this.zzfev.get();
        return zzchg.zza(this.zzfwl, zzchm0, executor0);
    }

    public static Set zza(zzchc zzchc0, zzchm zzchm0, Executor executor0) {
        return (Set)zzeep.zza(zzchc.zzc(zzchm0, executor0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzchg zzc(zzchc zzchc0, zzeew zzeew0, zzeew zzeew1) {
        return new zzchg(zzchc0, zzeew0, zzeew1);
    }
}

