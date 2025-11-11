package com.google.android.gms.internal.ads;

public final class zzcqk implements zzeej {
    private final zzeew zzgeb;

    private zzcqk(zzeew zzeew0) {
        this.zzgeb = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcql(((zzcro)this.zzgeb.get()));
    }

    public static zzcqk zzag(zzeew zzeew0) {
        return new zzcqk(zzeew0);
    }
}

