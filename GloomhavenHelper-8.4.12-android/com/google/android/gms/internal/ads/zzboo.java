package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzboo implements zzeej {
    private final zzeew zzfex;
    private final zzeew zzfjc;

    private zzboo(zzeew zzeew0, zzeew zzeew1) {
        this.zzfex = zzeew0;
        this.zzfjc = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbop(((Clock)this.zzfex.get()), ((zzavq)this.zzfjc.get()));
    }

    public static zzboo zzd(zzeew zzeew0, zzeew zzeew1) {
        return new zzboo(zzeew0, zzeew1);
    }
}

