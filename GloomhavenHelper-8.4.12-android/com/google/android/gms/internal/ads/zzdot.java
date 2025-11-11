package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdot extends zzdnn implements RunnableFuture {
    private volatile zzdob zzhds;

    zzdot(zzdne zzdne0) {
        this.zzhds = new zzdos(this, zzdne0);
    }

    private zzdot(Callable callable0) {
        this.zzhds = new zzdov(this, callable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void afterDone() {
        super.afterDone();
        if(this.wasInterrupted()) {
            zzdob zzdob0 = this.zzhds;
            if(zzdob0 != null) {
                zzdob0.interruptTask();
            }
        }
        this.zzhds = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final String pendingToString() {
        zzdob zzdob0 = this.zzhds;
        return zzdob0 == null ? super.pendingToString() : "task=[" + zzdob0 + "]";
    }

    @Override
    public final void run() {
        zzdob zzdob0 = this.zzhds;
        if(zzdob0 != null) {
            zzdob0.run();
        }
        this.zzhds = null;
    }

    static zzdot zza(Runnable runnable0, @NullableDecl Object object0) {
        return new zzdot(Executors.callable(runnable0, object0));
    }

    static zzdot zze(Callable callable0) {
        return new zzdot(callable0);
    }
}

