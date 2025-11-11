package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzcyg implements Runnable {
    private final zzcye zzgkq;
    private final long zzgkr;

    zzcyg(zzcye zzcye0, long v) {
        this.zzgkq = zzcye0;
        this.zzgkr = v;
    }

    @Override
    public final void run() {
        zzawf.zzee(("Signal runtime : " + this.zzgkq.getClass().getCanonicalName() + " = " + (zzq.zzlc().elapsedRealtime() - this.zzgkr)));
    }
}

