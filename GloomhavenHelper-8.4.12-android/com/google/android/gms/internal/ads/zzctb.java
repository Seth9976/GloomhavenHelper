package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzctb implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfed;
    private final zzeew zzffi;
    private final zzeew zzghh;

    public zzctb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzghh = zzeew0;
        this.zzelc = zzeew1;
        this.zzfed = zzeew2;
        this.zzffi = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcsz(((zzbgk)this.zzghh.get()), ((Context)this.zzelc.get()), ((zzdq)this.zzfed.get()), ((zzazo)this.zzffi.get()));
    }
}

