package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzavw {
    private final Object lock;
    private volatile int zzdsi;
    private volatile long zzdsj;

    private zzavw() {
        this.lock = new Object();
        this.zzdsi = zzavv.zzdse;
        this.zzdsj = 0L;
    }

    zzavw(zzavt zzavt0) {
    }

    public final void zzvg() {
        long v = zzq.zzlc().currentTimeMillis();
        synchronized(this.lock) {
            if(this.zzdsi == zzavv.zzdsg && this.zzdsj + ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcqp)))) <= v) {
                this.zzdsi = zzavv.zzdse;
            }
        }
        long v2 = zzq.zzlc().currentTimeMillis();
        synchronized(this.lock) {
            if(this.zzdsi != 2) {
                return;
            }
            this.zzdsi = 3;
            if(3 == zzavv.zzdsg) {
                this.zzdsj = v2;
            }
        }
    }
}

