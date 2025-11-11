package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import javax.annotation.concurrent.GuardedBy;

public final class zzayn {
    private final Object lock;
    private long zzdvz;
    @GuardedBy("lock")
    private long zzdwa;

    public zzayn(long v) {
        this.zzdwa = 0x8000000000000000L;
        this.lock = new Object();
        this.zzdvz = v;
    }

    public final boolean tryAcquire() {
        synchronized(this.lock) {
            long v1 = zzq.zzlc().elapsedRealtime();
            if(this.zzdwa + this.zzdvz > v1) {
                return false;
            }
            this.zzdwa = v1;
            return true;
        }
    }

    public final void zzfb(long v) {
        synchronized(this.lock) {
            this.zzdvz = v;
        }
    }
}

