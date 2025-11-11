package com.google.android.gms.internal.ads;

final class zzdf implements Runnable {
    private final zzdc zzvd;

    zzdf(zzdc zzdc0) {
        this.zzvd = zzdc0;
        super();
    }

    @Override
    public final void run() {
        try {
            this.zzvd.zzbq();
        }
        catch(Exception exception0) {
            this.zzvd.zzuu.zza(2023, -1L, exception0);
        }
    }
}

