package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcls implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzerm;
    private final zzeew zzety;
    private final zzeew zzgas;

    private zzcls(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzelc = zzeew0;
        this.zzgas = zzeew1;
        this.zzerm = zzeew2;
        this.zzety = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzclr(((Context)this.zzelc.get()), ((zzbpm)this.zzgas.get()), ((zzclk)this.zzerm.get()), ((zzcle)this.zzety.get()));
    }

    public static zzcls zzd(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzcls(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

