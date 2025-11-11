package com.google.android.gms.internal.ads;

final class zzbvx implements Runnable {
    private final zzbdv zzeiw;

    private zzbvx(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override
    public final void run() {
        this.zzeiw.destroy();
    }

    static Runnable zzh(zzbdv zzbdv0) {
        return new zzbvx(zzbdv0);
    }
}

