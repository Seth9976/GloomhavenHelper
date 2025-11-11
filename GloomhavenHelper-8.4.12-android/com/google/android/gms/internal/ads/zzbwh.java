package com.google.android.gms.internal.ads;

public final class zzbwh implements zzeej {
    private final zzeew zzffv;
    private final zzbvz zzfmc;

    private zzbwh(zzbvz zzbvz0, zzeew zzeew0) {
        this.zzfmc = zzbvz0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbxa)this.zzffv.get()), zzazq.zzdxo), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwh zzc(zzbvz zzbvz0, zzeew zzeew0) {
        return new zzbwh(zzbvz0, zzeew0);
    }
}

