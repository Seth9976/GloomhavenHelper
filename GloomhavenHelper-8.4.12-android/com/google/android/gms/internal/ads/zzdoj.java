package com.google.android.gms.internal.ads;

final class zzdoj implements Runnable {
    private final Runnable zzhdi;
    private final zzdog zzhdj;

    zzdoj(zzdog zzdog0, Runnable runnable0) {
        this.zzhdj = zzdog0;
        this.zzhdi = runnable0;
        super();
    }

    @Override
    public final void run() {
        this.zzhdj.zzhde = false;
        this.zzhdi.run();
    }
}

