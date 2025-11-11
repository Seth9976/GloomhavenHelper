package com.google.android.gms.internal.ads;

final class zzru implements Runnable {
    private final zzrr zzbrq;

    zzru(zzrr zzrr0) {
        this.zzbrq = zzrr0;
        super();
    }

    @Override
    public final void run() {
        this.zzbrq.disconnect();
    }
}

