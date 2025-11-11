package com.google.android.gms.internal.ads;

public final class zzbpc implements zzeej {
    private final zzeew zzffc;
    private final zzboz zzfji;

    private zzbpc(zzboz zzboz0, zzeew zzeew0) {
        this.zzfji = zzboz0;
        this.zzffc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbpk)this.zzffc.get()), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpc zzb(zzboz zzboz0, zzeew zzeew0) {
        return new zzbpc(zzboz0, zzeew0);
    }
}

