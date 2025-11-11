package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcik implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfed;
    private final zzeew zzfxr;

    private zzcik(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfed = zzeew0;
        this.zzelc = zzeew1;
        this.zzfxr = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdq zzdq0 = (zzdq)this.zzfed.get();
        Context context0 = (Context)this.zzelc.get();
        return (zzdof)zzeep.zza(((zzdoe)this.zzfxr.get()).zzd(new zzcil(zzdq0, context0)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcik zzn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcik(zzeew0, zzeew1, zzeew2);
    }
}

