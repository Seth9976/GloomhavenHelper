package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

public final class zzdio implements zzeej {
    private final zzeew zzerz;
    private final zzeew zzfev;
    private final zzeew zzfgc;

    private zzdio(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfev = zzeew0;
        this.zzfgc = zzeew1;
        this.zzerz = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdif(((zzdoe)this.zzfev.get()), ((ScheduledExecutorService)this.zzfgc.get()), ((zzdii)this.zzerz.get()));
    }

    public static zzdio zzs(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzdio(zzeew0, zzeew1, zzeew2);
    }
}

