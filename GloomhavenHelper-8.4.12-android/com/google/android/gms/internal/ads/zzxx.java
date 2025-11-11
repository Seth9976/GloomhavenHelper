package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzxx implements Runnable {
    private final zzxu zzcfr;
    private final Context zzcft;

    zzxx(zzxu zzxu0, Context context0) {
        this.zzcfr = zzxu0;
        this.zzcft = context0;
    }

    @Override
    public final void run() {
        this.zzcfr.getRewardedVideoAdInstance(this.zzcft);
    }
}

