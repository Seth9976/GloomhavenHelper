package com.google.android.gms.internal.ads;

public final class zzcqt implements zzeej {
    private final zzeew zzfdb;
    private final zzeew zzfgm;
    private final zzeew zzfgx;
    private final zzeew zzgej;

    private zzcqt(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfgx = zzeew0;
        this.zzfgm = zzeew1;
        this.zzfdb = zzeew2;
        this.zzgej = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcqp(((zzdif)this.zzfgx.get()), ((zzdoe)this.zzfgm.get()), ((zzcmc)this.zzfdb.get()), ((zzcmf)this.zzgej.get()));
    }

    public static zzcqt zze(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzcqt(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

