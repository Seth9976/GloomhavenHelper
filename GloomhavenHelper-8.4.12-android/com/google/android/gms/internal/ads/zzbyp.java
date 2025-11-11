package com.google.android.gms.internal.ads;

public final class zzbyp implements zzeej {
    private final zzeew zzfnw;

    private zzbyp(zzeew zzeew0) {
        this.zzfnw = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbym(((zzbyz)this.zzfnw.get()));
    }

    public static zzbyp zzx(zzeew zzeew0) {
        return new zzbyp(zzeew0);
    }
}

