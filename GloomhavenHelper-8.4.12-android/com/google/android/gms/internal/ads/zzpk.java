package com.google.android.gms.internal.ads;

final class zzpk implements Runnable {
    private final zzgz zzahv;
    private final zzpf zzbjp;

    zzpk(zzpf zzpf0, zzgz zzgz0) {
        this.zzbjp = zzpf0;
        this.zzahv = zzgz0;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzk(this.zzahv);
    }
}

