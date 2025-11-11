package com.google.android.gms.internal.ads;

final class zzpi implements Runnable {
    private final zzis zzahr;
    private final zzpf zzbjp;

    zzpi(zzpf zzpf0, zzis zzis0) {
        this.zzbjp = zzpf0;
        this.zzahr = zzis0;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zze(this.zzahr);
    }
}

