package com.google.android.gms.common.api.internal;

final class zzd implements Runnable {
    private final LifecycleCallback zzbi;
    private final String zzbj;
    private final zzc zzbl;

    zzd(zzc zzc0, LifecycleCallback lifecycleCallback0, String s) {
        this.zzbl = zzc0;
        this.zzbi = lifecycleCallback0;
        this.zzbj = s;
        super();
    }

    @Override
    public final void run() {
        if(this.zzbl.zzbg > 0 && this.zzbl.zzbh != null) {
            this.zzbl.zzbh.getBundle(this.zzbj);
        }
        if(this.zzbl.zzbg >= 4) {
            this.zzbi.onStop();
        }
    }
}

