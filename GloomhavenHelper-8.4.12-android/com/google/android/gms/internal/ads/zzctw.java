package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzctw implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzghy;

    private zzctw(zzeew zzeew0, zzeew zzeew1) {
        this.zzghy = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzctr(((zzdof)this.zzghy.get()), ((Executor)this.zzfev.get()));
    }

    public static zzctw zzap(zzeew zzeew0, zzeew zzeew1) {
        return new zzctw(zzeew0, zzeew1);
    }
}

