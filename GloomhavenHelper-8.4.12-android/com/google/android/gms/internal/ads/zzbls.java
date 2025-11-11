package com.google.android.gms.internal.ads;

public final class zzbls implements zzeej {
    private final zzeew zzeze;
    private final zzbln zzfgr;

    public zzbls(zzbln zzbln0, zzeew zzeew0) {
        this.zzfgr = zzbln0;
        this.zzeze = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Object object0 = this.zzeze.get();
        return zzbls.zza(this.zzfgr, object0);
    }

    public static zzblg zza(zzbln zzbln0, Object object0) {
        return (zzblg)zzeep.zza(((zzbli)object0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

