package com.google.android.gms.internal.ads;

public final class zzdfk implements zzeej {
    private final zzeew zzfjb;
    private final zzdfh zzgrg;

    private zzdfk(zzdfh zzdfh0, zzeew zzeew0) {
        this.zzgrg = zzdfh0;
        this.zzfjb = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzdfj)this.zzfjb.get()), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzdfk zza(zzdfh zzdfh0, zzeew zzeew0) {
        return new zzdfk(zzdfh0, zzeew0);
    }
}

