package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ScheduledExecutorService;

public final class zzblh implements zzeej {
    private final zzeew zzfex;
    private final zzeew zzfgc;

    public zzblh(zzeew zzeew0, zzeew zzeew1) {
        this.zzfgc = zzeew0;
        this.zzfex = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzblh.zza(((ScheduledExecutorService)this.zzfgc.get()), ((Clock)this.zzfex.get()));
    }

    public static zzbsf zza(ScheduledExecutorService scheduledExecutorService0, Clock clock0) {
        return (zzbsf)zzeep.zza(new zzbsf(scheduledExecutorService0, clock0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

