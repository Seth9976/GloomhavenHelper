package com.google.android.gms.internal.ads;

final class zzbyq implements Runnable {
    private final zzbzh zzfop;

    private zzbyq(zzbzh zzbzh0) {
        this.zzfop = zzbzh0;
    }

    @Override
    public final void run() {
        this.zzfop.zzaju();
    }

    static Runnable zza(zzbzh zzbzh0) {
        return new zzbyq(zzbzh0);
    }
}

