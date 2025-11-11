package com.google.android.gms.internal.ads;

public final class zzbwq implements zzeej {
    private final zzeew zzepf;
    private final zzeew zzfbh;

    private zzbwq(zzeew zzeew0, zzeew zzeew1) {
        this.zzepf = zzeew0;
        this.zzfbh = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbwr(((zzbrv)this.zzepf.get()), ((zzbur)this.zzfbh.get()));
    }

    public static zzbwq zzo(zzeew zzeew0, zzeew zzeew1) {
        return new zzbwq(zzeew0, zzeew1);
    }
}

