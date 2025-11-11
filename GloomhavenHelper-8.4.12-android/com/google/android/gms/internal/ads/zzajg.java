package com.google.android.gms.internal.ads;

final class zzajg implements Runnable {
    private final zzair zzdav;

    private zzajg(zzair zzair0) {
        this.zzdav = zzair0;
    }

    @Override
    public final void run() {
        this.zzdav.destroy();
    }

    static Runnable zzb(zzair zzair0) {
        return new zzajg(zzair0);
    }
}

