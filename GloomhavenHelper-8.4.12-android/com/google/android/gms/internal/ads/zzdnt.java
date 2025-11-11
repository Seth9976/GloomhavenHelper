package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdnt extends zzdoa {
    public static zzdof immediateFailedFuture(Throwable throwable0) {
        zzdlg.checkNotNull(throwable0);
        return new zza(throwable0);
    }

    @SafeVarargs
    public static zzdny zza(zzdof[] arr_zzdof) {
        return new zzdny(false, zzdlr.zzb(arr_zzdof), null);
    }

    public static zzdof zza(zzdne zzdne0, Executor executor0) {
        zzdof zzdof0 = new zzdot(zzdne0);
        executor0.execute(((Runnable)zzdof0));
        return zzdof0;
    }

    // 去混淆评级： 低(20)
    public static zzdof zza(zzdof zzdof0, long v, TimeUnit timeUnit0, ScheduledExecutorService scheduledExecutorService0) {
        return zzdof0.isDone() ? zzdof0 : zzdop.zzb(zzdof0, v, timeUnit0, scheduledExecutorService0);
    }

    public static Object zza(Future future0) throws ExecutionException {
        if(!future0.isDone()) {
            throw new IllegalStateException(zzdlh.zzb("Future was expected to be done: %s", new Object[]{future0}));
        }
        return zzdox.getUninterruptibly(future0);
    }

    public static void zza(zzdof zzdof0, zzdnu zzdnu0, Executor executor0) {
        zzdlg.checkNotNull(zzdnu0);
        zzdof0.addListener(new zzdnv(zzdof0, zzdnu0), executor0);
    }

    public static zzdof zzaj(@NullableDecl Object object0) {
        return object0 == null ? zzdnz.zzhda : new zzdnz(object0);
    }

    @SafeVarargs
    public static zzdny zzb(zzdof[] arr_zzdof) {
        return new zzdny(true, zzdlr.zzb(arr_zzdof), null);
    }

    public static zzdof zzb(zzdof zzdof0, zzdku zzdku0, Executor executor0) {
        return zzdmv.zza(zzdof0, zzdku0, executor0);
    }

    public static zzdof zzb(zzdof zzdof0, zzdng zzdng0, Executor executor0) {
        return zzdmv.zza(zzdof0, zzdng0, executor0);
    }

    public static zzdof zzb(zzdof zzdof0, Class class0, zzdng zzdng0, Executor executor0) {
        return zzdms.zza(zzdof0, class0, zzdng0, executor0);
    }

    public static Object zzb(Future future0) {
        zzdlg.checkNotNull(future0);
        try {
            return zzdox.getUninterruptibly(future0);
        }
        catch(ExecutionException executionException0) {
            Throwable throwable0 = executionException0.getCause();
            if(throwable0 instanceof Error) {
                throw new zzdnl(((Error)throwable0));
            }
            throw new zzdou(throwable0);
        }
    }

    public static zzdof zzg(Iterable iterable0) {
        return new zzdni(zzdlr.zzf(iterable0), true);
    }

    public static zzdny zzh(Iterable iterable0) {
        return new zzdny(false, zzdlr.zzf(iterable0), null);
    }

    public static zzdny zzi(Iterable iterable0) {
        return new zzdny(true, zzdlr.zzf(iterable0), null);
    }
}

