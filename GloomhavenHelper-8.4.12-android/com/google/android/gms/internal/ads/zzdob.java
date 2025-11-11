package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzdob extends AtomicReference implements Runnable {
    private static final Runnable zzhdb;
    private static final Runnable zzhdc;
    private static final Runnable zzhdd;

    static {
        zzdob.zzhdb = new zzdoc(null);
        zzdob.zzhdc = new zzdoc(null);
        zzdob.zzhdd = new zzdoc(null);
    }

    final void interruptTask() {
        Runnable runnable0 = (Runnable)this.get();
        if(runnable0 instanceof Thread && this.compareAndSet(runnable0, zzdob.zzhdc)) {
            try {
                ((Thread)runnable0).interrupt();
            }
            finally {
                if(((Runnable)this.getAndSet(zzdob.zzhdb)) == zzdob.zzhdd) {
                    LockSupport.unpark(((Thread)runnable0));
                }
            }
        }
    }

    abstract boolean isDone();

    @Override
    public final void run() {
        Object object0;
        Thread thread0 = Thread.currentThread();
        if(!this.compareAndSet(null, thread0)) {
            return;
        }
        boolean z = this.isDone();
        if(!z == 0) {
            object0 = null;
        label_29:
            if(!this.compareAndSet(thread0, zzdob.zzhdb)) {
                Runnable runnable1 = (Runnable)this.get();
                int v3 = 0;
                int v4 = 0;
                while(runnable1 == zzdob.zzhdc || runnable1 == zzdob.zzhdd) {
                    ++v4;
                    if(v4 <= 1000) {
                        Thread.yield();
                    }
                    else if(runnable1 == zzdob.zzhdd || this.compareAndSet(zzdob.zzhdc, zzdob.zzhdd)) {
                        int v5 = Thread.interrupted() || v3 != 0 ? 1 : 0;
                        LockSupport.park(this);
                        v3 = v5;
                    }
                    runnable1 = (Runnable)this.get();
                }
                if(v3 != 0) {
                    thread0.interrupt();
                }
            }
            if(!z != 0) {
                this.zzb(object0, null);
            }
        }
        else {
            try {
                object0 = this.zzaur();
                goto label_29;
            }
            catch(Throwable throwable0) {
                if(!this.compareAndSet(thread0, zzdob.zzhdb)) {
                    Runnable runnable0 = (Runnable)this.get();
                    int v = 0;
                    int v1 = 0;
                    while(runnable0 == zzdob.zzhdc || runnable0 == zzdob.zzhdd) {
                        ++v1;
                        if(v1 <= 1000) {
                            Thread.yield();
                        }
                        else if(runnable0 == zzdob.zzhdd || this.compareAndSet(zzdob.zzhdc, zzdob.zzhdd)) {
                            int v2 = Thread.interrupted() || v != 0 ? 1 : 0;
                            LockSupport.park(this);
                            v = v2;
                        }
                        runnable0 = (Runnable)this.get();
                    }
                    if(v != 0) {
                        thread0.interrupt();
                    }
                }
                if(!z != 0) {
                    this.zzb(null, throwable0);
                }
            }
        }
    }

    @Override
    public final String toString() {
        Runnable runnable0 = (Runnable)this.get();
        if(runnable0 == zzdob.zzhdb) {
            return "running=[DONE], " + this.zzaus();
        }
        if(runnable0 == zzdob.zzhdc) {
            return "running=[INTERRUPTED], " + this.zzaus();
        }
        return runnable0 instanceof Thread ? "running=[RUNNING ON " + ((Thread)runnable0).getName() + "]" + ", " + this.zzaus() : "running=[NOT STARTED YET], " + this.zzaus();
    }

    abstract Object zzaur() throws Exception;

    abstract String zzaus();

    abstract void zzb(@NullableDecl Object arg1, @NullableDecl Throwable arg2);
}

