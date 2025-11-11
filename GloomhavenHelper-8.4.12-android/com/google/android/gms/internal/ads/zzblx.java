package com.google.android.gms.internal.ads;

public final class zzblx implements zzeej {
    private final zzeew zzffv;
    private final zzbln zzfgr;

    public zzblx(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbmu)this.zzffv.get()), zzazq.zzdxo), "Cannot return null from a non-@Nullable @Provides method");
    }
}

