package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzdip implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfkh;
    private final zzdiq zzgwe;

    public zzdip(zzdiq zzdiq0, zzeew zzeew0, zzeew zzeew1) {
        this.zzgwe = zzdiq0;
        this.zzelc = zzeew0;
        this.zzfkh = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        zzazo zzazo0 = (zzazo)this.zzfkh.get();
        return (zzakk)zzeep.zza(new zzakf().zzb(context0, zzazo0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

