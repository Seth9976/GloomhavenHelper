package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbzp implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgu;
    private final zzbzl zzfqb;

    private zzbzp(zzbzl zzbzl0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfqb = zzbzl0;
        this.zzelc = zzeew0;
        this.zzfgu = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzauf)zzeep.zza(new zzauf(((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()).zzgqr), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbzp zza(zzbzl zzbzl0, zzeew zzeew0, zzeew zzeew1) {
        return new zzbzp(zzbzl0, zzeew0, zzeew1);
    }
}

