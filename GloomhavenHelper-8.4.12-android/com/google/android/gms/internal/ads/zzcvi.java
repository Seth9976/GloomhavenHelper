package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzcvi implements zzeej {
    private final zzeew zzfex;
    private final zzeew zzgja;

    public zzcvi(zzeew zzeew0, zzeew zzeew1) {
        this.zzgja = zzeew0;
        this.zzfex = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzcvg)zzeep.zza(new zzcvg(((zzcva)this.zzgja.get()), 10000L, ((Clock)this.zzfex.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

