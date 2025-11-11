package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

class zzdnz implements zzdof {
    static final class zza extends zzj {
        zza(Throwable throwable0) {
            this.setException(throwable0);
        }
    }

    @NullableDecl
    private final Object value;
    private static final Logger zzhbm;
    static final zzdof zzhda;

    static {
        zzdnz.zzhda = new zzdnz(null);
        zzdnz.zzhbm = Logger.getLogger("com.google.android.gms.internal.ads.zzdnz");
    }

    zzdnz(@NullableDecl Object object0) {
        this.value = object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdof
    public void addListener(Runnable runnable0, Executor executor0) {
        zzdlg.checkNotNull(runnable0, "Runnable was null.");
        zzdlg.checkNotNull(executor0, "Executor was null.");
        try {
            executor0.execute(runnable0);
        }
        catch(RuntimeException runtimeException0) {
            zzdnz.zzhbm.logp(Level.SEVERE, "com.google.common.util.concurrent.ImmediateFuture", "addListener", "RuntimeException while executing runnable " + runnable0 + " with executor " + executor0, runtimeException0);
        }
    }

    @Override
    public boolean cancel(boolean z) {
        return false;
    }

    @Override
    public Object get() {
        return this.value;
    }

    @Override
    public Object get(long v, TimeUnit timeUnit0) throws ExecutionException {
        zzdlg.checkNotNull(timeUnit0);
        return this.get();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
    }
}

