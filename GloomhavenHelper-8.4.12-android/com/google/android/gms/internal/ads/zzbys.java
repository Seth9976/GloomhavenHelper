package com.google.android.gms.internal.ads;

final class zzbys implements Runnable {
    private final boolean zzdzs;
    private final zzbyo zzfoq;

    zzbys(zzbyo zzbyo0, boolean z) {
        this.zzfoq = zzbyo0;
        this.zzdzs = z;
    }

    @Override
    public final void run() {
        this.zzfoq.zzbj(this.zzdzs);
    }
}

