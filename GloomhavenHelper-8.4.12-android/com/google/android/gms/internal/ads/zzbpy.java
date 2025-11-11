package com.google.android.gms.internal.ads;

public final class zzbpy implements zzeej {
    private final zzeew zzequ;
    private final zzbpt zzfkc;

    private zzbpy(zzbpt zzbpt0, zzeew zzeew0) {
        this.zzfkc = zzbpt0;
        this.zzequ = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbop zzbop0 = (zzbop)this.zzequ.get();
        return zzbpy.zza(this.zzfkc, zzbop0);
    }

    public static String zza(zzbpt zzbpt0, zzbop zzbop0) {
        return (String)zzeep.zza(zzbop0.zzvd(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpy zzb(zzbpt zzbpt0, zzeew zzeew0) {
        return new zzbpy(zzbpt0, zzeew0);
    }
}

