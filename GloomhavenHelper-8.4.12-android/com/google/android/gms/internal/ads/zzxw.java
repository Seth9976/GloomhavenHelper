package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

final class zzxw implements Runnable {
    private final zzxu zzcfr;
    private final OnInitializationCompleteListener zzcfs;

    zzxw(zzxu zzxu0, OnInitializationCompleteListener onInitializationCompleteListener0) {
        this.zzcfr = zzxu0;
        this.zzcfs = onInitializationCompleteListener0;
    }

    @Override
    public final void run() {
        this.zzcfr.zza(this.zzcfs);
    }
}

