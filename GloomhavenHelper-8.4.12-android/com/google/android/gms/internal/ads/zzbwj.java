package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbwj implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgu;
    private final zzbvz zzfmc;

    private zzbwj(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfmc = zzbvz0;
        this.zzelc = zzeew0;
        this.zzfgu = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzauf)zzeep.zza(new zzauf(((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()).zzgqr), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwj zza(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1) {
        return new zzbwj(zzbvz0, zzeew0, zzeew1);
    }
}

