package com.google.android.gms.internal.ads;

public final class zzblw implements zzeej {
    private final zzeew zzffv;
    private final zzbln zzfgr;

    public zzblw(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbmu zzbmu0 = (zzbmu)this.zzffv.get();
        return zzblw.zza(this.zzfgr, zzbmu0);
    }

    public static zzbuv zza(zzbln zzbln0, zzbmu zzbmu0) {
        return (zzbuv)zzeep.zza(new zzbuv(zzbmu0, zzazq.zzdxo), "Cannot return null from a non-@Nullable @Provides method");
    }
}

