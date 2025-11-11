package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

public final class zzcnj implements zzeej {
    private final zzeew zzezl;
    private final zzeew zzezp;
    private final zzeew zzfev;
    private final zzeew zzfst;
    private final zzeew zzgcc;

    public zzcnj(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzezl = zzeew0;
        this.zzezp = zzeew1;
        this.zzgcc = zzeew2;
        this.zzfst = zzeew3;
        this.zzfev = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcnf(((zzbmc)this.zzezl.get()), ((zzcml)this.zzezp.get()), ((zzbqk)this.zzgcc.get()), ((ScheduledExecutorService)this.zzfst.get()), ((zzdoe)this.zzfev.get()));
    }
}

