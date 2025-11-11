package com.google.android.gms.internal.ads;

public final class zzchp implements zzeej {
    private final zzeew zzela;
    private final zzeew zzfve;

    private zzchp(zzeew zzeew0, zzeew zzeew1) {
        this.zzfve = zzeew0;
        this.zzela = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzchm(((zzcha)this.zzfve.get()), ((zzbgk)this.zzela.get()));
    }

    public static zzchp zzag(zzeew zzeew0, zzeew zzeew1) {
        return new zzchp(zzeew0, zzeew1);
    }
}

