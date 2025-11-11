package com.google.android.gms.internal.ads;

public final class zzcuo implements zzeej {
    private final zzeew zzequ;
    private final zzeew zzesd;
    private final zzeew zzfgu;
    private final zzeew zzgin;
    private final zzeew zzgio;

    private zzcuo(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzgin = zzeew0;
        this.zzgio = zzeew1;
        this.zzequ = zzeew2;
        this.zzesd = zzeew3;
        this.zzfgu = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcum(((String)this.zzgin.get()), ((String)this.zzgio.get()), ((zzbop)this.zzequ.get()), ((zzdfj)this.zzesd.get()), ((zzdeu)this.zzfgu.get()));
    }

    public static zzcuo zzf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzcuo(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

