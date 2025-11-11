package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

public final class zzbuw implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgs;
    private final zzeew zzfhn;

    private zzbuw(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzfgs = zzeew1;
        this.zzfhn = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbuu(((Context)this.zzelc.get()), ((Set)this.zzfgs.get()), ((zzdei)this.zzfhn.get()));
    }

    public static zzbuw zzi(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbuw(zzeew0, zzeew1, zzeew2);
    }
}

