package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import javax.annotation.concurrent.GuardedBy;

public final class zzdeg {
    private final Object lock;
    private final Clock zzbmz;
    private volatile long zzdsj;
    @GuardedBy("lock")
    private volatile int zzgpi;

    public zzdeg(Clock clock0) {
        this.lock = new Object();
        this.zzgpi = zzdef.zzgpe;
        this.zzdsj = 0L;
        this.zzbmz = clock0;
    }

    private final void zzaqs() {
        long v = this.zzbmz.currentTimeMillis();
        synchronized(this.lock) {
            if(this.zzgpi == zzdef.zzgpg && this.zzdsj + ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcqp)))) <= v) {
                this.zzgpi = zzdef.zzgpe;
            }
        }
    }

    public final boolean zzaqt() {
        synchronized(this.lock) {
            this.zzaqs();
            return this.zzgpi == zzdef.zzgpf;
        }
    }

    public final boolean zzaqu() {
        synchronized(this.lock) {
            this.zzaqs();
            return this.zzgpi == zzdef.zzgpg;
        }
    }

    public final void zzbn(boolean z) {
        if(z) {
            this.zzr(zzdef.zzgpe, zzdef.zzgpf);
            return;
        }
        this.zzr(zzdef.zzgpf, zzdef.zzgpe);
    }

    private final void zzr(int v, int v1) {
        this.zzaqs();
        long v2 = this.zzbmz.currentTimeMillis();
        synchronized(this.lock) {
            if(this.zzgpi != v) {
                return;
            }
            this.zzgpi = v1;
            if(this.zzgpi == zzdef.zzgpg) {
                this.zzdsj = v2;
            }
        }
    }

    public final void zzvg() {
        this.zzr(zzdef.zzgpf, zzdef.zzgpg);
    }
}

