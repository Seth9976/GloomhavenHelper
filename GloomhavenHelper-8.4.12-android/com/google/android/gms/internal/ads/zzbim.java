package com.google.android.gms.internal.ads;

final class zzbim implements Runnable {
    private final zzbik zzfcy;
    private final Runnable zzfcz;

    zzbim(zzbik zzbik0, Runnable runnable0) {
        this.zzfcy = zzbik0;
        this.zzfcz = runnable0;
    }

    @Override
    public final void run() {
        this.zzfcy.zzd(this.zzfcz);
    }
}

