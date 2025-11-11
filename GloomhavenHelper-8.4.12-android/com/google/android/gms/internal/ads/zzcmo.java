package com.google.android.gms.internal.ads;

final class zzcmo implements Runnable {
    private final zzbdv zzfsx;
    private final zzcml zzgbi;

    zzcmo(zzcml zzcml0, zzbdv zzbdv0) {
        this.zzgbi = zzcml0;
        this.zzfsx = zzbdv0;
    }

    @Override
    public final void run() {
        this.zzgbi.zzo(this.zzfsx);
    }
}

