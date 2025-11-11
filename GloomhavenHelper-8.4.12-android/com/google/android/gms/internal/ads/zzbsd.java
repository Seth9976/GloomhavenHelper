package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbsd implements zzeej {
    private final zzeew zzfeb;
    private final zzeew zzfgs;

    private zzbsd(zzeew zzeew0, zzeew zzeew1) {
        this.zzfgs = zzeew0;
        this.zzfeb = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbsb(((Set)this.zzfgs.get()), ((zzdei)this.zzfeb.get()));
    }

    public static zzbsd zzm(zzeew zzeew0, zzeew zzeew1) {
        return new zzbsd(zzeew0, zzeew1);
    }
}

