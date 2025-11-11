package com.google.android.gms.internal.ads;

public final class zzcfs implements zzeej {
    private final zzeew zzeqz;
    private final zzeew zzfve;

    private zzcfs(zzeew zzeew0, zzeew zzeew1) {
        this.zzfve = zzeew0;
        this.zzeqz = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcfp(((zzsn)this.zzfve.get()), ((zzdcu)this.zzeqz.get()));
    }

    public static zzcfs zzz(zzeew zzeew0, zzeew zzeew1) {
        return new zzcfs(zzeew0, zzeew1);
    }
}

