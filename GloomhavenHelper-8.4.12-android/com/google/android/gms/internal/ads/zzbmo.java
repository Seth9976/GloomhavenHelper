package com.google.android.gms.internal.ads;

final class zzbmo implements Runnable {
    private final Runnable zzfcz;
    private final zzbmm zzfhd;

    zzbmo(zzbmm zzbmm0, Runnable runnable0) {
        this.zzfhd = zzbmm0;
        this.zzfcz = runnable0;
    }

    @Override
    public final void run() {
        this.zzfhd.zze(this.zzfcz);
    }
}

