package com.google.android.gms.internal.ads;

final class zzf implements Runnable {
    private final zzq zzp;
    private final zzc zzq;

    zzf(zzc zzc0, zzq zzq0) {
        this.zzq = zzc0;
        this.zzp = zzq0;
        super();
    }

    @Override
    public final void run() {
        try {
            this.zzq.zzb.put(this.zzp);
        }
        catch(InterruptedException unused_ex) {
            Thread.currentThread().interrupt();
        }
    }
}

