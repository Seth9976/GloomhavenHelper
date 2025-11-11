package com.google.android.gms.internal.ads;

final class zzbca implements Runnable {
    private final int zzdul;
    private final zzbbp zzecs;

    zzbca(zzbbp zzbbp0, int v) {
        this.zzecs = zzbbp0;
        this.zzdul = v;
    }

    @Override
    public final void run() {
        this.zzecs.zzdb(this.zzdul);
    }
}

