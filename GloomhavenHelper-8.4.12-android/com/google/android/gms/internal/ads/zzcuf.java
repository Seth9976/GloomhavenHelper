package com.google.android.gms.internal.ads;

public final class zzcuf implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfgu;

    private zzcuf(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzfgu = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcud(((zzdoe)this.zzfev.get()), ((zzdeu)this.zzfgu.get()));
    }

    public static zzcuf zzaq(zzeew zzeew0, zzeew zzeew1) {
        return new zzcuf(zzeew0, zzeew1);
    }
}

