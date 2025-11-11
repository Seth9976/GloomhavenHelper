package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;

public final class zzctn implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgu;
    private final zzeew zzgda;
    private final zzeew zzght;

    public zzctn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzght = zzeew0;
        this.zzelc = zzeew1;
        this.zzfgu = zzeew2;
        this.zzgda = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzctl(((zzdoe)this.zzght.get()), ((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()), ((ViewGroup)this.zzgda.get()));
    }
}

