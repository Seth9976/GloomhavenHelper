package com.google.android.gms.internal.ads;

public final class zzclo implements zzeej {
    private final zzeew zzgak;

    private zzclo(zzeew zzeew0) {
        this.zzgak = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzclp(((zzclk)this.zzgak.get()));
    }

    public static zzclo zzaf(zzeew zzeew0) {
        return new zzclo(zzeew0);
    }
}

