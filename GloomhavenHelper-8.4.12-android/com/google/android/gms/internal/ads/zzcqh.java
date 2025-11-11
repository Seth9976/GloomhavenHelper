package com.google.android.gms.internal.ads;

final class zzcqh implements zzbvi {
    private final zzdeq zzgdt;
    private final zzdei zzgdu;
    private final zzcmd zzgdv;
    final zzcqf zzgdw;

    zzcqh(zzcqf zzcqf0, zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) {
        this.zzgdw = zzcqf0;
        this.zzgdt = zzdeq0;
        this.zzgdu = zzdei0;
        this.zzgdv = zzcmd0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzbvi
    public final void onInitializationSucceeded() {
        this.zzgdw.zzfeo.execute(new zzcqg(this, this.zzgdt, this.zzgdu, this.zzgdv));
    }

    @Override  // com.google.android.gms.internal.ads.zzbvi
    public final void zzdh(int v) {
        String s = String.valueOf(this.zzgdv.zzfik);
        zzawf.zzfa((s.length() == 0 ? new String("Fail to initialize adapter ") : "Fail to initialize adapter " + s));
    }
}

