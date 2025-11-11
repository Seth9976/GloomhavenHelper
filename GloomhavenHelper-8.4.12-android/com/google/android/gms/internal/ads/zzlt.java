package com.google.android.gms.internal.ads;

final class zzlt implements Runnable {
    private final zzls zzbab;
    private final zzly zzbbc;

    zzlt(zzls zzls0, zzly zzly0) {
        this.zzbab = zzls0;
        this.zzbbc = zzly0;
        super();
    }

    @Override
    public final void run() {
        this.zzbbc.release();
        int v = zzls.zzd(this.zzbab).size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((zzmm)zzls.zzd(this.zzbab).valueAt(v1)).disable();
        }
    }
}

