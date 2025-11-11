package com.google.android.gms.internal.ads;

final class zzcmp implements Runnable {
    private final zzbdv zzeiw;

    private zzcmp(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override
    public final void run() {
        this.zzeiw.zzaas();
    }

    static Runnable zzh(zzbdv zzbdv0) {
        return new zzcmp(zzbdv0);
    }
}

