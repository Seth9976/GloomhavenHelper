package com.google.android.gms.internal.ads;

public final class zzcev implements zzeej {
    private final zzeew zzffv;
    private final zzces zzfuu;

    private zzcev(zzces zzces0, zzeew zzeew0) {
        this.zzfuu = zzces0;
        this.zzffv = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzceq)this.zzffv.get()), zzazq.zzdxo), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcev zza(zzces zzces0, zzeew zzeew0) {
        return new zzcev(zzces0, zzeew0);
    }
}

