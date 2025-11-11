package com.google.android.gms.internal.ads;

final class zzaqc implements Thread.UncaughtExceptionHandler {
    private final zzaqa zzdjq;
    private final Thread.UncaughtExceptionHandler zzdjr;

    zzaqc(zzaqa zzaqa0, Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler0) {
        this.zzdjq = zzaqa0;
        this.zzdjr = thread$UncaughtExceptionHandler0;
        super();
    }

    @Override
    public final void uncaughtException(Thread thread0, Throwable throwable0) {
        Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler1;
        Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler0;
        try {
            this.zzdjq.zza(thread0, throwable0);
            thread$UncaughtExceptionHandler0 = this.zzdjr;
        }
        catch(Throwable unused_ex) {
            try {
                zzazh.zzey("AdMob exception reporter failed reporting the exception.");
                thread$UncaughtExceptionHandler1 = this.zzdjr;
            }
            catch(Throwable throwable1) {
                Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler2 = this.zzdjr;
                if(thread$UncaughtExceptionHandler2 != null) {
                    thread$UncaughtExceptionHandler2.uncaughtException(thread0, throwable0);
                }
                throw throwable1;
            }
            if(thread$UncaughtExceptionHandler1 != null) {
                thread$UncaughtExceptionHandler1.uncaughtException(thread0, throwable0);
                return;
            }
            return;
        }
        if(thread$UncaughtExceptionHandler0 != null) {
            thread$UncaughtExceptionHandler0.uncaughtException(thread0, throwable0);
        }
    }
}

