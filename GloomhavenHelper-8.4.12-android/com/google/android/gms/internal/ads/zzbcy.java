package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzbcy implements Runnable {
    private final zzbcv zzeeg;

    zzbcy(zzbcv zzbcv0) {
        this.zzeeg = zzbcv0;
        super();
    }

    @Override
    public final void run() {
        zzq.zzlr().zzb(this.zzeeg);
    }
}

