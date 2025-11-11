package com.google.android.gms.internal.ads;

final class zzbzu implements Runnable {
    private final zzbzv zzfqr;
    private final zzcal zzfqs;

    zzbzu(zzbzv zzbzv0, zzcal zzcal0) {
        this.zzfqr = zzbzv0;
        this.zzfqs = zzcal0;
    }

    @Override
    public final void run() {
        this.zzfqr.zzd(this.zzfqs);
    }
}

