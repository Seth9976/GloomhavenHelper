package com.google.android.gms.internal.ads;

final class zzchy implements Runnable {
    private final String zzczs;
    private final zzchz zzfxd;

    zzchy(zzchz zzchz0, String s) {
        this.zzfxd = zzchz0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzfxd.zzfxe.zzgg(this.zzczs);
    }
}

