package com.google.android.gms.internal.ads;

final class zzbbu implements Runnable {
    private final String zzczs;
    private final zzbbp zzecs;

    zzbbu(zzbbp zzbbp0, String s) {
        this.zzecs = zzbbp0;
        this.zzczs = s;
    }

    @Override
    public final void run() {
        this.zzecs.zzfg(this.zzczs);
    }
}

