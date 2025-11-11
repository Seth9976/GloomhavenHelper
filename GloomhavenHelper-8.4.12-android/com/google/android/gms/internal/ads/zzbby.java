package com.google.android.gms.internal.ads;

final class zzbby implements Runnable {
    private final int zzdul;
    private final int zzdum;
    private final zzbbp zzecs;

    zzbby(zzbbp zzbbp0, int v, int v1) {
        this.zzecs = zzbbp0;
        this.zzdul = v;
        this.zzdum = v1;
    }

    @Override
    public final void run() {
        this.zzecs.zzp(this.zzdul, this.zzdum);
    }
}

