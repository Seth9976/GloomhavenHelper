package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcgl implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzelu;
    private final zzeew zzerf;
    private final zzeew zzfeb;

    private zzcgl(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzerf = zzeew0;
        this.zzelu = zzeew1;
        this.zzfeb = zzeew2;
        this.zzelc = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgi(((zzcgq)this.zzerf.get()), ((zzcgx)this.zzelu.get()), ((zzdei)this.zzfeb.get()), ((Context)this.zzelc.get()));
    }

    public static zzcgl zzc(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzcgl(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

