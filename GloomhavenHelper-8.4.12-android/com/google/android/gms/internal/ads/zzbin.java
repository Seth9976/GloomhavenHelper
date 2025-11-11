package com.google.android.gms.internal.ads;

final class zzbin implements Runnable {
    private final zzbik zzfcy;
    private final Runnable zzfcz;

    zzbin(zzbik zzbik0, Runnable runnable0) {
        this.zzfcy = zzbik0;
        this.zzfcz = runnable0;
    }

    @Override
    public final void run() {
        zzbim zzbim0 = new zzbim(this.zzfcy, this.zzfcz);
        zzazq.zzdxo.execute(zzbim0);
    }
}

