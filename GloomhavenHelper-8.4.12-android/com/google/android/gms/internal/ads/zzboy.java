package com.google.android.gms.internal.ads;

public final class zzboy implements zzeej {
    private final zzeew zzffv;
    private final zzboz zzfji;

    private zzboy(zzboz zzboz0, zzeew zzeew0) {
        this.zzfji = zzboz0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbnz)this.zzffv.get()), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzboy zza(zzboz zzboz0, zzeew zzeew0) {
        return new zzboy(zzboz0, zzeew0);
    }
}

