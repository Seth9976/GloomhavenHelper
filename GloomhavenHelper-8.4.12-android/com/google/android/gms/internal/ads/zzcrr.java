package com.google.android.gms.internal.ads;

public final class zzcrr implements zzeej {
    private final zzeew zzgeb;

    private zzcrr(zzeew zzeew0) {
        this.zzgeb = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcro(((zzcnk)this.zzgeb.get()));
    }

    public static zzcrr zzah(zzeew zzeew0) {
        return new zzcrr(zzeew0);
    }
}

