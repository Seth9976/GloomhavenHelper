package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzbom implements zzeej {
    private final zzeew zzeqr;
    private final zzeew zzfex;
    private final zzeew zzfgu;

    private zzbom(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfex = zzeew0;
        this.zzeqr = zzeew1;
        this.zzfgu = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Clock clock0 = (Clock)this.zzfex.get();
        return (zzavq)zzeep.zza(((zzawc)this.zzeqr.get()).zza(clock0, ((zzdeu)this.zzfgu.get()).zzgqr), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbom zzg(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbom(zzeew0, zzeew1, zzeew2);
    }
}

