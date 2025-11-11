package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzblv implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzffh;
    private final zzbln zzfgr;
    private final zzeew zzfgu;

    public zzblv(zzbln zzbln0, zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfgr = zzbln0;
        this.zzelc = zzeew0;
        this.zzfda = zzeew1;
        this.zzffh = zzeew2;
        this.zzfgu = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        zzazo zzazo0 = (zzazo)this.zzfda.get();
        zzdei zzdei0 = (zzdei)this.zzffh.get();
        zzdeu zzdeu0 = (zzdeu)this.zzfgu.get();
        return zzblv.zza(this.zzfgr, context0, zzazo0, zzdei0, zzdeu0);
    }

    public static zzbuv zza(zzbln zzbln0, Context context0, zzazo zzazo0, zzdei zzdei0, zzdeu zzdeu0) {
        return (zzbuv)zzeep.zza(new zzbuv(new zzblm(context0, zzazo0, zzdei0, zzdeu0), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }
}

