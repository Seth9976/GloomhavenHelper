package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class zzsj implements Runnable {
    private final zzazy zzbsd;
    private final Future zzbse;

    zzsj(zzazy zzazy0, Future future0) {
        this.zzbsd = zzazy0;
        this.zzbse = future0;
    }

    @Override
    public final void run() {
        Future future0 = this.zzbse;
        if(this.zzbsd.isCancelled()) {
            future0.cancel(true);
        }
    }
}

