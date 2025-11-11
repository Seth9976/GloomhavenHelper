package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;

public final class zzbmw implements zzbrn, zzpt {
    private final zzdei zzfdq;
    private final zzbqp zzfhj;
    private final zzbrr zzfhk;
    private final AtomicBoolean zzfhl;
    private final AtomicBoolean zzfhm;

    public zzbmw(zzdei zzdei0, zzbqp zzbqp0, zzbrr zzbrr0) {
        this.zzfhl = new AtomicBoolean();
        this.zzfhm = new AtomicBoolean();
        this.zzfdq = zzdei0;
        this.zzfhj = zzbqp0;
        this.zzfhk = zzbrr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        synchronized(this) {
            if(this.zzfdq.zzgpl != 1) {
                this.zzahf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        if(this.zzfdq.zzgpl == 1 && zzpu0.zzbnz) {
            this.zzahf();
        }
        if(zzpu0.zzbnz && this.zzfhm.compareAndSet(false, true)) {
            this.zzfhk.zzaia();
        }
    }

    private final void zzahf() {
        if(this.zzfhl.compareAndSet(false, true)) {
            this.zzfhj.onAdImpression();
        }
    }
}

