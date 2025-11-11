package com.google.android.gms.common.api.internal;

final class zzb implements Runnable {
    private final LifecycleCallback zzbi;
    private final String zzbj;
    private final zza zzbk;

    zzb(zza zza0, LifecycleCallback lifecycleCallback0, String s) {
        this.zzbk = zza0;
        this.zzbi = lifecycleCallback0;
        this.zzbj = s;
        super();
    }

    @Override
    public final void run() {
        if(this.zzbk.zzbg > 0 && this.zzbk.zzbh != null) {
            this.zzbk.zzbh.getBundle(this.zzbj);
        }
        if(this.zzbk.zzbg >= 4) {
            this.zzbi.onStop();
        }
    }
}

