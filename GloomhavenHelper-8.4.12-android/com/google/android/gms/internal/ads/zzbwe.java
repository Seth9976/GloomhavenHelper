package com.google.android.gms.internal.ads;

public final class zzbwe implements zzeej {
    private final zzeew zzffv;
    private final zzbvz zzfmc;

    private zzbwe(zzbvz zzbvz0, zzeew zzeew0) {
        this.zzfmc = zzbvz0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbxa)this.zzffv.get()), zzazq.zzdxo), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwe zzb(zzbvz zzbvz0, zzeew zzeew0) {
        return new zzbwe(zzbvz0, zzeew0);
    }
}

