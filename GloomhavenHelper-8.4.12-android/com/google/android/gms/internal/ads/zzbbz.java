package com.google.android.gms.internal.ads;

final class zzbbz implements Runnable {
    private final boolean zzdzs;
    private final zzbbp zzecs;
    private final long zzect;

    zzbbz(zzbbp zzbbp0, boolean z, long v) {
        this.zzecs = zzbbp0;
        this.zzdzs = z;
        this.zzect = v;
    }

    @Override
    public final void run() {
        this.zzecs.zzc(this.zzdzs, this.zzect);
    }
}

