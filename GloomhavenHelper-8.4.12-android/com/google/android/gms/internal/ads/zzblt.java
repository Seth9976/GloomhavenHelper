package com.google.android.gms.internal.ads;

public final class zzblt implements zzeej {
    private final zzbln zzfgr;

    public zzblt(zzbln zzbln0) {
        this.zzfgr = zzbln0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzblt.zzc(this.zzfgr);
    }

    public static zzbnc zzc(zzbln zzbln0) {
        return (zzbnc)zzeep.zza(zzbln0.zzagu(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

