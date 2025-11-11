package com.google.android.gms.internal.ads;

public final class zzbxf implements zzeej {
    private final zzeew zzfml;
    private final zzeew zzfmm;

    private zzbxf(zzeew zzeew0, zzeew zzeew1) {
        this.zzfml = zzeew0;
        this.zzfmm = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbxc(((zzbqp)this.zzfml.get()), ((zzdei)this.zzfmm.get()));
    }

    public static zzbxf zzp(zzeew zzeew0, zzeew zzeew1) {
        return new zzbxf(zzeew0, zzeew1);
    }
}

