package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzale implements Runnable {
    private final Context zzcft;
    private final zzalc zzdcp;

    zzale(zzalc zzalc0, Context context0) {
        this.zzdcp = zzalc0;
        this.zzcft = context0;
    }

    @Override
    public final void run() {
        zzalc.zzp(this.zzcft);
    }
}

