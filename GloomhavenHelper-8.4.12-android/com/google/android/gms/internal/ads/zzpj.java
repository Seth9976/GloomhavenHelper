package com.google.android.gms.internal.ads;

final class zzpj implements Runnable {
    private final zzpf zzbjp;
    private final int zzbjq;
    private final long zzbjr;

    zzpj(zzpf zzpf0, int v, long v1) {
        this.zzbjp = zzpf0;
        this.zzbjq = v;
        this.zzbjr = v1;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzf(this.zzbjq, this.zzbjr);
    }
}

