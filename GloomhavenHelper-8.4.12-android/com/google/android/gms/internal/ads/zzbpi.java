package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbpi implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzfgu;

    public zzbpi(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzfda = zzeew1;
        this.zzfgu = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzdku)zzeep.zza(new zzbpj(((Context)this.zzelc.get()), ((zzazo)this.zzfda.get()), ((zzdeu)this.zzfgu.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

