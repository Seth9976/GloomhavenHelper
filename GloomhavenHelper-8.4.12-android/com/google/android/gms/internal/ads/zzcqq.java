package com.google.android.gms.internal.ads;

final class zzcqq implements zzbrj {
    private final zzcmd zzgdv;
    private final zzazy zzgeg;

    zzcqq(zzcqp zzcqp0, zzazy zzazy0, zzcmd zzcmd0) {
        this.zzgeg = zzazy0;
        this.zzgdv = zzcmd0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzbrj
    public final void onAdFailedToLoad(int v) {
        synchronized(this) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpq)).booleanValue()) {
                v = 3;
            }
            zzcpe zzcpe0 = new zzcpe("adapter " + this.zzgdv.zzfik + " failed to load", v);
            this.zzgeg.setException(zzcpe0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrj
    public final void onAdLoaded() {
        synchronized(this) {
            this.zzgeg.set(null);
        }
    }
}

