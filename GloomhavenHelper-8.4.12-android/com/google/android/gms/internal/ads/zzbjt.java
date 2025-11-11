package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

public final class zzbjt implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeuf;
    private final zzeew zzfea;
    private final zzeew zzfeb;
    private final zzeew zzfec;
    private final zzeew zzfed;

    private zzbjt(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzelc = zzeew0;
        this.zzfea = zzeew1;
        this.zzfeb = zzeew2;
        this.zzeuf = zzeew3;
        this.zzfec = zzeew4;
        this.zzfed = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbjq(((Context)this.zzelc.get()), ((zzdeq)this.zzfea.get()), ((zzdei)this.zzfeb.get()), ((zzdis)this.zzeuf.get()), ((View)this.zzfec.get()), ((zzdq)this.zzfed.get()));
    }

    public static zzbjt zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        return new zzbjt(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5);
    }
}

