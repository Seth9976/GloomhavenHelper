package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public final class zzbko implements zzqk {
    private final Clock zzbmz;
    @GuardedBy("this")
    private Runnable zzdsu;
    private final ScheduledExecutorService zzffm;
    @Nullable
    @GuardedBy("this")
    private ScheduledFuture zzffn;
    @GuardedBy("this")
    private long zzffo;
    @GuardedBy("this")
    private long zzffp;
    @GuardedBy("this")
    private boolean zzffq;

    public zzbko(ScheduledExecutorService scheduledExecutorService0, Clock clock0) {
        this.zzffo = -1L;
        this.zzffp = -1L;
        this.zzdsu = null;
        this.zzffq = false;
        this.zzffm = scheduledExecutorService0;
        this.zzbmz = clock0;
        zzq.zzky().zza(this);
    }

    public final void zza(int v, Runnable runnable0) {
        synchronized(this) {
            this.zzdsu = runnable0;
            this.zzffo = this.zzbmz.elapsedRealtime() + ((long)v);
            this.zzffn = this.zzffm.schedule(runnable0, ((long)v), TimeUnit.MILLISECONDS);
        }
    }

    @VisibleForTesting
    private final void zzaga() {
        synchronized(this) {
            if(!this.zzffq) {
                if(this.zzffn == null || this.zzffn.isDone()) {
                    this.zzffp = -1L;
                }
                else {
                    this.zzffn.cancel(true);
                    this.zzffp = this.zzffo - this.zzbmz.elapsedRealtime();
                }
                this.zzffq = true;
            }
        }
    }

    @VisibleForTesting
    private final void zzagb() {
        synchronized(this) {
            if(this.zzffq) {
                if(this.zzffp > 0L && this.zzffn != null && this.zzffn.isCancelled()) {
                    this.zzffn = this.zzffm.schedule(this.zzdsu, this.zzffp, TimeUnit.MILLISECONDS);
                }
                this.zzffq = false;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzqk
    public final void zzp(boolean z) {
        if(z) {
            this.zzagb();
            return;
        }
        this.zzaga();
    }
}

