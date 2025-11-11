package com.google.android.gms.internal.ads;

final class zzcdm implements Runnable {
    private final zzbdv zzeiw;

    private zzcdm(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override
    public final void run() {
        this.zzeiw.destroy();
    }

    static Runnable zzh(zzbdv zzbdv0) {
        return new zzcdm(zzbdv0);
    }
}

