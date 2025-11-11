package com.google.android.gms.internal.ads;

final class zzcmn implements zzbnc {
    private final zzbdv zzeiw;

    private zzcmn(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbnc
    public final zzxj getVideoController() {
        return this.zzeiw.zzyq();
    }

    static zzbnc zzp(zzbdv zzbdv0) {
        return new zzcmn(zzbdv0);
    }
}

