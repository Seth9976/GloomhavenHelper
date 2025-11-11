package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

final class zzbmp implements Runnable {
    private final AtomicReference zzfhe;

    zzbmp(AtomicReference atomicReference0) {
        this.zzfhe = atomicReference0;
    }

    @Override
    public final void run() {
        Runnable runnable0 = (Runnable)this.zzfhe.getAndSet(null);
        if(runnable0 != null) {
            runnable0.run();
        }
    }
}

