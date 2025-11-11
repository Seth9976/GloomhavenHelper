package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

final class zzbsk implements Runnable {
    private final WeakReference zzfkr;

    private zzbsk(zzbsf zzbsf0) {
        this.zzfkr = new WeakReference(zzbsf0);
    }

    zzbsk(zzbsf zzbsf0, zzbsh zzbsh0) {
        this(zzbsf0);
    }

    @Override
    public final void run() {
        zzbsf zzbsf0 = (zzbsf)this.zzfkr.get();
        if(zzbsf0 != null) {
            zzbsf0.zzaid();
        }
    }
}

