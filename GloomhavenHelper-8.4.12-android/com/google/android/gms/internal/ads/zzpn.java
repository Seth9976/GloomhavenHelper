package com.google.android.gms.internal.ads;

final class zzpn implements Runnable {
    private final zzis zzahx;
    private final zzpf zzbjp;

    zzpn(zzpf zzpf0, zzis zzis0) {
        this.zzbjp = zzpf0;
        this.zzahx = zzis0;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzf(this.zzahx);
    }
}

