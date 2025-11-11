package com.google.android.gms.internal.ads;

final class zzbax implements Runnable {
    private final zzbav zzdzr;
    private final boolean zzdzs;

    zzbax(zzbav zzbav0, boolean z) {
        this.zzdzr = zzbav0;
        this.zzdzs = z;
    }

    @Override
    public final void run() {
        this.zzdzr.zzau(this.zzdzs);
    }
}

