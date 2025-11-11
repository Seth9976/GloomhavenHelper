package com.google.android.gms.internal.ads;

final class zzcfa implements zzsq {
    private final String zzczs;
    private final int zzdxb;
    private final zztu zzfuw;
    private final String zzfux;

    zzcfa(int v, String s, zztu zztu0, String s1) {
        this.zzdxb = v;
        this.zzczs = s;
        this.zzfuw = zztu0;
        this.zzfux = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzsq
    public final void zza(zztv zztv0) {
        zztv0.zzcbm.zzcah = this.zzdxb;
        zztv0.zzcbj.zzcas = this.zzczs;
        zztv0.zzcbj.zzcav = this.zzfuw;
        zztv0.zzcbe = this.zzfux;
    }
}

