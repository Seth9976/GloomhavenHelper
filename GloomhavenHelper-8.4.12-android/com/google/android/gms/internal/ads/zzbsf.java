package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Clock;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public final class zzbsf extends zzbtk {
    private final Clock zzbmz;
    @GuardedBy("this")
    private boolean zzfey;
    private final ScheduledExecutorService zzffm;
    @GuardedBy("this")
    private long zzffo;
    @GuardedBy("this")
    private long zzffp;
    @Nullable
    @GuardedBy("this")
    private ScheduledFuture zzfkq;

    public zzbsf(ScheduledExecutorService scheduledExecutorService0, Clock clock0) {
        super(Collections.emptySet());
        this.zzffo = -1L;
        this.zzffp = -1L;
        this.zzfey = false;
        this.zzffm = scheduledExecutorService0;
        this.zzbmz = clock0;
    }

    public final void onPause() {
        synchronized(this) {
            if(!this.zzfey) {
                if(this.zzfkq == null || this.zzfkq.isCancelled()) {
                    this.zzffp = -1L;
                }
                else {
                    this.zzfkq.cancel(true);
                    this.zzffp = this.zzffo - this.zzbmz.elapsedRealtime();
                }
                this.zzfey = true;
            }
        }
    }

    public final void onResume() {
        synchronized(this) {
            if(this.zzfey) {
                if(this.zzffp > 0L && this.zzfkq.isCancelled()) {
                    this.zzfd(this.zzffp);
                }
                this.zzfey = false;
            }
        }
    }

    public final void zzaic() {
        synchronized(this) {
            this.zzfey = false;
            this.zzfd(0L);
        }
    }

    private final void zzaid() {
        this.zza(zzbsi.zzfkj);
    }

    public final void zzdg(int v) {
        synchronized(this) {
            if(v <= 0) {
                return;
            }
            long v2 = TimeUnit.SECONDS.toMillis(((long)v));
            if(this.zzfey) {
                if(this.zzffp <= 0L || v2 >= this.zzffp) {
                    v2 = this.zzffp;
                }
                this.zzffp = v2;
                return;
            }
            if(this.zzbmz.elapsedRealtime() > this.zzffo || this.zzffo - this.zzbmz.elapsedRealtime() > v2) {
                this.zzfd(v2);
            }
        }
    }

    private final void zzfd(long v) {
        synchronized(this) {
            if(this.zzfkq != null && !this.zzfkq.isDone()) {
                this.zzfkq.cancel(true);
            }
            this.zzffo = this.zzbmz.elapsedRealtime() + v;
            zzbsk zzbsk0 = new zzbsk(this, null);
            this.zzfkq = this.zzffm.schedule(zzbsk0, v, TimeUnit.MILLISECONDS);
        }
    }
}

