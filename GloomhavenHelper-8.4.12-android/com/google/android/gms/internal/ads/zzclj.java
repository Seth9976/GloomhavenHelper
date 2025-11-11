package com.google.android.gms.internal.ads;

public final class zzclj implements zzeej {
    private final zzeew zzetx;
    private final zzeew zzgaa;

    private zzclj(zzeew zzeew0, zzeew zzeew1) {
        this.zzetx = zzeew0;
        this.zzgaa = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcle(((zzclc)this.zzetx.get()), ((zzdoe)this.zzgaa.get()));
    }

    public static zzclj zzao(zzeew zzeew0, zzeew zzeew1) {
        return new zzclj(zzeew0, zzeew1);
    }
}

