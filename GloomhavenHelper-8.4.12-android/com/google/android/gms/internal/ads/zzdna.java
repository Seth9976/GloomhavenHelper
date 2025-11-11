package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzdna extends zzdnd {
    static enum zza {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED;

    }

    private static final Logger logger;
    @NullableDecl
    private zzdlq zzhce;
    private final boolean zzhcf;
    private final boolean zzhcg;

    static {
        zzdna.logger = Logger.getLogger(zzdna.class.getName());
    }

    zzdna(zzdlq zzdlq0, boolean z, boolean z1) {
        super(zzdlq0.size());
        this.zzhce = (zzdlq)zzdlg.checkNotNull(zzdlq0);
        this.zzhcf = z;
        this.zzhcg = z1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void afterDone() {
        super.afterDone();
        zzdlq zzdlq0 = this.zzhce;
        this.zza(zza.zzhch);
        if(this.isCancelled() && zzdlq0 != null) {
            boolean z = this.wasInterrupted();
            zzdmm zzdmm0 = (zzdmm)zzdlq0.iterator();
            while(zzdmm0.hasNext()) {
                Object object0 = zzdmm0.next();
                ((Future)object0).cancel(z);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final String pendingToString() {
        zzdlq zzdlq0 = this.zzhce;
        return zzdlq0 == null ? super.pendingToString() : "futures=" + zzdlq0;
    }

    static zzdlq zza(zzdna zzdna0, zzdlq zzdlq0) {
        zzdna0.zzhce = null;
        return null;
    }

    private final void zza(int v, Future future0) {
        try {
            this.zzb(v, zzdnt.zza(future0));
        }
        catch(ExecutionException executionException0) {
            this.zzi(executionException0.getCause());
        }
        catch(Throwable throwable0) {
            this.zzi(throwable0);
        }
    }

    private final void zza(@NullableDecl zzdlq zzdlq0) {
        int v = this.zzaup();
        if(v < 0) {
            throw new IllegalStateException("Less than 0 remaining futures");
        }
        if(v == 0) {
            if(zzdlq0 != null) {
                zzdmm zzdmm0 = (zzdmm)zzdlq0.iterator();
                for(int v1 = 0; zzdmm0.hasNext(); ++v1) {
                    Object object0 = zzdmm0.next();
                    Future future0 = (Future)object0;
                    if(!future0.isCancelled()) {
                        this.zza(v1, future0);
                    }
                }
            }
            this.zzauq();
            this.zzaun();
            this.zza(zza.zzhci);
        }
    }

    static void zza(zzdna zzdna0, int v, Future future0) {
        zzdna0.zza(v, future0);
    }

    private static boolean zza(Set set0, Throwable throwable0) {
        while(throwable0 != null) {
            if(!set0.add(throwable0)) {
                return false;
            }
            throwable0 = throwable0.getCause();
        }
        return true;
    }

    void zza(zza zzdna$zza0) {
        zzdlg.checkNotNull(zzdna$zza0);
        this.zzhce = null;
    }

    final void zzaum() {
        if(this.zzhce.isEmpty()) {
            this.zzaun();
            return;
        }
        if(this.zzhcf) {
            zzdmm zzdmm0 = (zzdmm)this.zzhce.iterator();
            for(int v = 0; zzdmm0.hasNext(); ++v) {
                Object object0 = zzdmm0.next();
                ((zzdof)object0).addListener(new zzdmz(this, ((zzdof)object0), v), zzdnm.zzhcu);
            }
            return;
        }
        zzdnb zzdnb0 = new zzdnb(this, (this.zzhcg ? this.zzhce : null));
        zzdmm zzdmm1 = (zzdmm)this.zzhce.iterator();
        while(zzdmm1.hasNext()) {
            Object object1 = zzdmm1.next();
            ((zzdof)object1).addListener(zzdnb0, zzdnm.zzhcu);
        }
    }

    abstract void zzaun();

    static void zzb(zzdna zzdna0, zzdlq zzdlq0) {
        zzdna0.zza(zzdlq0);
    }

    abstract void zzb(int arg1, @NullableDecl Object arg2);

    @Override  // com.google.android.gms.internal.ads.zzdnd
    final void zzh(Set set0) {
        zzdlg.checkNotNull(set0);
        if(!this.isCancelled()) {
            zzdna.zza(set0, this.zzauj());
        }
    }

    private final void zzi(Throwable throwable0) {
        zzdlg.checkNotNull(throwable0);
        if(this.zzhcf && !this.setException(throwable0) && zzdna.zza(this.zzauo(), throwable0)) {
            zzdna.zzj(throwable0);
            return;
        }
        if(throwable0 instanceof Error) {
            zzdna.zzj(throwable0);
        }
    }

    private static void zzj(Throwable throwable0) {
        zzdna.logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", (throwable0 instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first"), throwable0);
    }
}

