package com.google.android.gms.internal.ads;

import android.view.ViewGroup;

final class zzbzx implements Runnable {
    private final zzbzv zzfqr;
    private final ViewGroup zzfqx;

    zzbzx(zzbzv zzbzv0, ViewGroup viewGroup0) {
        this.zzfqr = zzbzv0;
        this.zzfqx = viewGroup0;
    }

    @Override
    public final void run() {
        this.zzfqr.zzb(this.zzfqx);
    }
}

