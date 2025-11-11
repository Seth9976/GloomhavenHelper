package com.google.android.gms.internal.ads;

final class zzajz implements Runnable {
    private final zzajw zzdbl;
    private final zzair zzdbm;

    zzajz(zzajw zzajw0, zzair zzair0) {
        this.zzdbl = zzajw0;
        this.zzdbm = zzair0;
    }

    @Override
    public final void run() {
        this.zzdbl.zzdbk.zzdan.zzh(this.zzdbm);
        this.zzdbm.destroy();
    }
}

