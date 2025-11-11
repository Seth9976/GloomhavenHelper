package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzdfq implements zzeej {
    private final zzeew zzffi;
    private final zzeew zzfjj;
    private final zzeew zzgic;

    public zzdfq(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfjj = zzeew0;
        this.zzffi = zzeew1;
        this.zzgic = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdfl(((Context)this.zzfjj.get()), ((zzazo)this.zzffi.get()), ((zzavr)this.zzgic.get()));
    }
}

