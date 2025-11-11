package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzawj implements Runnable {
    private final Context zzcft;
    private final String zzdcq;
    private final zzawk zzdtb;

    zzawj(zzawk zzawk0, Context context0, String s) {
        this.zzdtb = zzawk0;
        this.zzcft = context0;
        this.zzdcq = s;
    }

    @Override
    public final void run() {
        this.zzdtb.zzp(this.zzcft, this.zzdcq);
    }
}

