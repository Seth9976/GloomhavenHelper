package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

public final class zzchi implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfve;
    private final zzchc zzfwl;

    private zzchi(zzchc zzchc0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfwl = zzchc0;
        this.zzfve = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (Set)zzeep.zza(zzchc.zzb(((zzchm)this.zzfve.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzchi zze(zzchc zzchc0, zzeew zzeew0, zzeew zzeew1) {
        return new zzchi(zzchc0, zzeew0, zzeew1);
    }
}

