package com.google.android.gms.internal.ads;

public final class zzblq implements zzeej {
    private final zzbln zzfgr;

    public zzblq(zzbln zzbln0) {
        this.zzfgr = zzbln0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzblq.zza(this.zzfgr);
    }

    public static zzdeh zza(zzbln zzbln0) {
        return (zzdeh)zzeep.zza(zzbln0.zzagv(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

