package com.google.android.gms.internal.ads;

public final class zzbvc implements zzeej {
    private final zzeew zzeuf;
    private final zzeew zzfeb;

    private zzbvc(zzeew zzeew0, zzeew zzeew1) {
        this.zzfeb = zzeew0;
        this.zzeuf = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbvd(((zzdei)this.zzfeb.get()), ((zzdis)this.zzeuf.get()));
    }

    public static zzbvc zzn(zzeew zzeew0, zzeew zzeew1) {
        return new zzbvc(zzeew0, zzeew1);
    }
}

