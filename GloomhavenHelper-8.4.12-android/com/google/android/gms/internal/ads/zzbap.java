package com.google.android.gms.internal.ads;

final class zzbap implements Runnable {
    private final zzbak zzdyt;

    zzbap(zzbak zzbak0) {
        this.zzdyt = zzbak0;
        super();
    }

    @Override
    public final void run() {
        if(this.zzdyt.zzdys != null) {
            this.zzdyt.zzdys.onPaused();
            this.zzdyt.zzdys.zzyb();
        }
    }
}

