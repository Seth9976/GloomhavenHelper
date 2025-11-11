package com.google.android.gms.internal.ads;

public final class zzcts implements zzeej {
    private final zzeew zzfgu;

    private zzcts(zzeew zzeew0) {
        this.zzfgu = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzctp(((zzdeu)this.zzfgu.get()));
    }

    public static zzcts zzai(zzeew zzeew0) {
        return new zzcts(zzeew0);
    }
}

