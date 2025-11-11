package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzalf implements Runnable {
    private final Context zzcft;
    private final zzalc zzdcp;
    private final String zzdcq;

    zzalf(zzalc zzalc0, Context context0, String s) {
        this.zzdcp = zzalc0;
        this.zzcft = context0;
        this.zzdcq = s;
    }

    @Override
    public final void run() {
        zzalc.zzd(this.zzcft, this.zzdcq);
    }
}

