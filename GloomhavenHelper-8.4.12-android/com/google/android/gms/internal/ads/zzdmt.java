package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

public class zzdmt extends zzdow implements zzdof {
    static abstract class zza {
        private zza() {
        }

        zza(com.google.android.gms.internal.ads.zzdmt.1 zzdmt$10) {
        }

        abstract void zza(zzk arg1, zzk arg2);

        abstract void zza(zzk arg1, Thread arg2);

        abstract boolean zza(zzdmt arg1, zzd arg2, zzd arg3);

        abstract boolean zza(zzdmt arg1, zzk arg2, zzk arg3);

        abstract boolean zza(zzdmt arg1, Object arg2, Object arg3);
    }

    static final class zzb {
        final Throwable exception;
        static final zzb zzhbo;

        static {
            zzb.zzhbo = new zzb(new Throwable("Failure occurred while trying to finish a future.") {
                @Override
                public Throwable fillInStackTrace() {
                    synchronized(this) {
                    }
                    return this;
                }
            });
        }

        zzb(Throwable throwable0) {
            this.exception = (Throwable)zzdlg.checkNotNull(throwable0);
        }
    }

    static final class zzc {
        @NullableDecl
        final Throwable cause;
        final boolean wasInterrupted;
        static final zzc zzhbp;
        static final zzc zzhbq;

        static {
            zzc.zzhbq = new zzc(false, null);
            zzc.zzhbp = new zzc(true, null);
        }

        zzc(boolean z, @NullableDecl Throwable throwable0) {
            this.wasInterrupted = z;
            this.cause = throwable0;
        }
    }

    static final class zzd {
        final Executor executor;
        @NullableDecl
        zzd next;
        final Runnable task;
        static final zzd zzhbr;

        static {
            zzd.zzhbr = new zzd(null, null);
        }

        zzd(Runnable runnable0, Executor executor0) {
            this.task = runnable0;
            this.executor = executor0;
        }
    }

    static final class zze implements Runnable {
        final zzdof future;
        final zzdmt zzhbs;

        zze(zzdmt zzdmt0, zzdof zzdof0) {
            this.zzhbs = zzdmt0;
            this.future = zzdof0;
        }

        @Override
        public final void run() {
            if(this.zzhbs.value != this) {
                return;
            }
            Object object0 = zzdmt.getFutureValue(this.future);
            if(zzdmt.zzhbn.zza(this.zzhbs, this, object0)) {
                zzdmt.zza(this.zzhbs);
            }
        }
    }

    static final class zzf extends zza {
        final AtomicReferenceFieldUpdater listenersUpdater;
        final AtomicReferenceFieldUpdater valueUpdater;
        final AtomicReferenceFieldUpdater waiterNextUpdater;
        final AtomicReferenceFieldUpdater waiterThreadUpdater;
        final AtomicReferenceFieldUpdater waitersUpdater;

        zzf(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater1, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4) {
            super(null);
            this.waiterThreadUpdater = atomicReferenceFieldUpdater0;
            this.waiterNextUpdater = atomicReferenceFieldUpdater1;
            this.waitersUpdater = atomicReferenceFieldUpdater2;
            this.listenersUpdater = atomicReferenceFieldUpdater3;
            this.valueUpdater = atomicReferenceFieldUpdater4;
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            this.waiterNextUpdater.lazySet(zzdmt$zzk0, zzdmt$zzk1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, Thread thread0) {
            this.waiterThreadUpdater.lazySet(zzdmt$zzk0, thread0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzd zzdmt$zzd0, zzd zzdmt$zzd1) {
            return this.listenersUpdater.compareAndSet(zzdmt0, zzdmt$zzd0, zzdmt$zzd1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            return this.waitersUpdater.compareAndSet(zzdmt0, zzdmt$zzk0, zzdmt$zzk1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, Object object0, Object object1) {
            return this.valueUpdater.compareAndSet(zzdmt0, object0, object1);
        }
    }

    interface zzg extends zzdof {
    }

    static final class zzh extends zza {
        private zzh() {
            super(null);
        }

        zzh(com.google.android.gms.internal.ads.zzdmt.1 zzdmt$10) {
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            zzdmt$zzk0.next = zzdmt$zzk1;
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, Thread thread0) {
            zzdmt$zzk0.thread = thread0;
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzd zzdmt$zzd0, zzd zzdmt$zzd1) {
            synchronized(zzdmt0) {
                if(zzdmt0.listeners == zzdmt$zzd0) {
                    zzdmt0.listeners = zzdmt$zzd1;
                    return true;
                }
                return false;
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            synchronized(zzdmt0) {
                if(zzdmt0.waiters == zzdmt$zzk0) {
                    zzdmt0.waiters = zzdmt$zzk1;
                    return true;
                }
                return false;
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, Object object0, Object object1) {
            synchronized(zzdmt0) {
                if(zzdmt0.value == object0) {
                    zzdmt0.value = object1;
                    return true;
                }
                return false;
            }
        }
    }

    static final class zzi extends zza {
        static final Unsafe zzhbt;
        static final long zzhbu;
        static final long zzhbv;
        static final long zzhbw;
        static final long zzhbx;
        static final long zzhby;

        static {
            Unsafe unsafe0;
            try {
                unsafe0 = Unsafe.getUnsafe();
            }
            catch(SecurityException unused_ex) {
                try {
                    unsafe0 = (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {
                        @Override
                        public Object run() throws Exception {
                            Class class0 = Unsafe.class;
                            Field[] arr_field = class0.getDeclaredFields();
                            for(int v = 0; v < arr_field.length; ++v) {
                                Field field0 = arr_field[v];
                                field0.setAccessible(true);
                                Object object0 = field0.get(null);
                                if(class0.isInstance(object0)) {
                                    return (Unsafe)class0.cast(object0);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                }
                catch(PrivilegedActionException privilegedActionException0) {
                    throw new RuntimeException("Could not initialize intrinsics", privilegedActionException0.getCause());
                }
            }
            try {
                zzi.zzhbv = unsafe0.objectFieldOffset(zzdmt.class.getDeclaredField("waiters"));
                zzi.zzhbu = unsafe0.objectFieldOffset(zzdmt.class.getDeclaredField("listeners"));
                zzi.zzhbw = unsafe0.objectFieldOffset(zzdmt.class.getDeclaredField("value"));
                zzi.zzhbx = unsafe0.objectFieldOffset(zzk.class.getDeclaredField("thread"));
                zzi.zzhby = unsafe0.objectFieldOffset(zzk.class.getDeclaredField("next"));
                zzi.zzhbt = unsafe0;
            }
            catch(Exception exception0) {
                zzdlj.zzg(exception0);
                throw new RuntimeException(exception0);
            }
        }

        private zzi() {
            super(null);
        }

        zzi(com.google.android.gms.internal.ads.zzdmt.1 zzdmt$10) {
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            zzi.zzhbt.putObject(zzdmt$zzk0, zzi.zzhby, zzdmt$zzk1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final void zza(zzk zzdmt$zzk0, Thread thread0) {
            zzi.zzhbt.putObject(zzdmt$zzk0, zzi.zzhbx, thread0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzd zzdmt$zzd0, zzd zzdmt$zzd1) {
            return zzi.zzhbt.compareAndSwapObject(zzdmt0, zzi.zzhbu, zzdmt$zzd0, zzdmt$zzd1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, zzk zzdmt$zzk0, zzk zzdmt$zzk1) {
            return zzi.zzhbt.compareAndSwapObject(zzdmt0, zzi.zzhbv, zzdmt$zzk0, zzdmt$zzk1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdmt$zza
        final boolean zza(zzdmt zzdmt0, Object object0, Object object1) {
            return zzi.zzhbt.compareAndSwapObject(zzdmt0, zzi.zzhbw, object0, object1);
        }
    }

    static class zzj extends zzdmt implements zzg {
        @Override  // com.google.android.gms.internal.ads.zzdmt
        public final Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
            return super.get(v, timeUnit0);
        }
    }

    static final class zzk {
        @NullableDecl
        volatile zzk next;
        @NullableDecl
        volatile Thread thread;
        static final zzk zzhbz;

        static {
            zzk.zzhbz = new zzk(false);
        }

        zzk() {
            zzdmt.zzhbn.zza(this, Thread.currentThread());
        }

        private zzk(boolean z) {
        }

        final void zzb(zzk zzdmt$zzk0) {
            zzdmt.zzhbn.zza(this, zzdmt$zzk0);
        }
    }

    private static final boolean GENERATE_CANCELLATION_CAUSES;
    private static final Object NULL;
    @NullableDecl
    private volatile zzd listeners;
    @NullableDecl
    private volatile Object value;
    @NullableDecl
    private volatile zzk waiters;
    private static final Logger zzhbm;
    private static final zza zzhbn;

    static {
        zzi zzdmt$zzi0;
        Throwable throwable1;
        Throwable throwable0;
        zzdmt.GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        zzdmt.zzhbm = Logger.getLogger("com.google.android.gms.internal.ads.zzdmt");
        try {
            throwable0 = null;
            throwable1 = throwable2;
            zzdmt$zzi0 = new zzi(null);
            throwable1 = null;
        }
        catch(Throwable unused_ex) {
            try {
                zzdmt$zzi0 = new zzf(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(zzdmt.class, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(zzdmt.class, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(zzdmt.class, Object.class, "value"));
            }
            catch(Throwable throwable3) {
                throwable0 = throwable3;
                zzdmt$zzi0 = new zzh(null);
            }
        }
        zzdmt.zzhbn = zzdmt$zzi0;
        if(throwable0 != null) {
            zzdmt.zzhbm.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", throwable1);
            zzdmt.zzhbm.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", throwable0);
        }
        zzdmt.NULL = new Object();
    }

    @Override  // com.google.android.gms.internal.ads.zzdof
    public void addListener(Runnable runnable0, Executor executor0) {
        zzdlg.checkNotNull(runnable0, "Runnable was null.");
        zzdlg.checkNotNull(executor0, "Executor was null.");
        if(!this.isDone()) {
            zzd zzdmt$zzd0 = this.listeners;
            if(zzdmt$zzd0 != zzd.zzhbr) {
                zzd zzdmt$zzd1 = new zzd(runnable0, executor0);
                while(true) {
                    zzdmt$zzd1.next = zzdmt$zzd0;
                    if(zzdmt.zzhbn.zza(this, zzdmt$zzd0, zzdmt$zzd1)) {
                        return;
                    }
                    zzdmt$zzd0 = this.listeners;
                    if(zzdmt$zzd0 == zzd.zzhbr) {
                        break;
                    }
                }
            }
        }
        zzdmt.zza(runnable0, executor0);
    }

    protected void afterDone() {
    }

    @Override
    public boolean cancel(boolean z) {
        zzc zzdmt$zzc0;
        Object object0 = this.value;
        if((object0 == null | object0 instanceof zze) != 0) {
            if(zzdmt.GENERATE_CANCELLATION_CAUSES) {
                zzdmt$zzc0 = new zzc(z, new CancellationException("Future.cancel() was called."));
            }
            else {
                zzdmt$zzc0 = z ? zzc.zzhbp : zzc.zzhbq;
            }
            boolean z1 = false;
            Object object1 = object0;
            zzdmt zzdmt0 = this;
            while(true) {
                if(zzdmt.zzhbn.zza(zzdmt0, object1, zzdmt$zzc0)) {
                    if(z) {
                        zzdmt0.interruptTask();
                    }
                    zzdmt.zza(zzdmt0);
                    if(object1 instanceof zze) {
                        zzdof zzdof0 = ((zze)object1).future;
                        if(!(zzdof0 instanceof zzg)) {
                            zzdof0.cancel(z);
                            return true;
                        }
                        zzdmt0 = (zzdmt)zzdof0;
                        object1 = zzdmt0.value;
                        if((object1 == null | object1 instanceof zze) != 0) {
                            z1 = true;
                            continue;
                        }
                    }
                    return true;
                }
                object1 = zzdmt0.value;
                if(!(object1 instanceof zze)) {
                    break;
                }
            }
            return z1;
        }
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object object0 = this.value;
        if(((object0 == null ? 0 : 1) & (object0 instanceof zze ? 0 : 1)) != 0) {
            return zzdmt.zzai(object0);
        }
        zzk zzdmt$zzk0 = this.waiters;
        if(zzdmt$zzk0 != zzk.zzhbz) {
            zzk zzdmt$zzk1 = new zzk();
            while(true) {
                zzdmt$zzk1.zzb(zzdmt$zzk0);
                if(zzdmt.zzhbn.zza(this, zzdmt$zzk0, zzdmt$zzk1)) {
                    while(true) {
                        LockSupport.park(this);
                        if(Thread.interrupted()) {
                            break;
                        }
                        Object object1 = this.value;
                        if(((object1 == null ? 0 : 1) & (object1 instanceof zze ? 0 : 1)) != 0) {
                            return zzdmt.zzai(object1);
                        }
                    }
                    this.zza(zzdmt$zzk1);
                    throw new InterruptedException();
                }
                zzdmt$zzk0 = this.waiters;
                if(zzdmt$zzk0 == zzk.zzhbz) {
                    break;
                }
            }
        }
        return zzdmt.zzai(this.value);
    }

    @Override
    public Object get(long v, TimeUnit timeUnit0) throws InterruptedException, TimeoutException, ExecutionException {
        long v1 = timeUnit0.toNanos(v);
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object object0 = this.value;
        if(((object0 == null ? 0 : 1) & (object0 instanceof zze ? 0 : 1)) != 0) {
            return zzdmt.zzai(object0);
        }
        long v2 = v1 <= 0L ? 0L : System.nanoTime() + v1;
        if(v1 >= 1000L) {
            zzk zzdmt$zzk0 = this.waiters;
            if(zzdmt$zzk0 != zzk.zzhbz) {
                zzk zzdmt$zzk1 = new zzk();
                while(true) {
                    zzdmt$zzk1.zzb(zzdmt$zzk0);
                    if(zzdmt.zzhbn.zza(this, zzdmt$zzk0, zzdmt$zzk1)) {
                        do {
                            LockSupport.parkNanos(this, v1);
                            if(Thread.interrupted()) {
                                this.zza(zzdmt$zzk1);
                                throw new InterruptedException();
                            }
                            Object object1 = this.value;
                            if(((object1 == null ? 0 : 1) & (object1 instanceof zze ? 0 : 1)) != 0) {
                                return zzdmt.zzai(object1);
                            }
                            v1 = v2 - System.nanoTime();
                        }
                        while(v1 >= 1000L);
                        this.zza(zzdmt$zzk1);
                        goto label_26;
                    }
                    zzdmt$zzk0 = this.waiters;
                    if(zzdmt$zzk0 == zzk.zzhbz) {
                        break;
                    }
                }
            }
            return zzdmt.zzai(this.value);
        }
    label_26:
        while(v1 > 0L) {
            Object object2 = this.value;
            if(((object2 == null ? 0 : 1) & (object2 instanceof zze ? 0 : 1)) != 0) {
                return zzdmt.zzai(object2);
            }
            if(Thread.interrupted()) {
                throw new InterruptedException();
            }
            v1 = v2 - System.nanoTime();
        }
        String s = timeUnit0.toString().toLowerCase(Locale.ROOT);
        String s1 = "Waited " + v + " " + timeUnit0.toString().toLowerCase(Locale.ROOT);
        if(v1 + 1000L < 0L) {
            String s2 = s1 + " (plus ";
            long v3 = timeUnit0.convert(-v1, TimeUnit.NANOSECONDS);
            long v4 = -v1 - timeUnit0.toNanos(v3);
            boolean z = v3 == 0L || v4 > 1000L;
            if(v3 > 0L) {
                s2 = (z ? String.valueOf(s2) + v3 + " " + s + "," : String.valueOf(s2) + v3 + " " + s) + " ";
            }
            if(z) {
                s2 = String.valueOf(s2) + v4 + " nanoseconds ";
            }
            s1 = s2 + "delay)";
        }
        throw this.isDone() ? new TimeoutException(s1 + " but future completed as timeout expired") : new TimeoutException(s1 + " for " + "jebdyn.dexdec.irsb.Object_f146c62d@193d1928[status=PENDING]");
    }

    private static Object getFutureValue(zzdof zzdof0) {
        if(zzdof0 instanceof zzg) {
            zzc zzdmt$zzc0 = ((zzdmt)zzdof0).value;
            if(zzdmt$zzc0 instanceof zzc && zzdmt$zzc0.wasInterrupted) {
                return zzdmt$zzc0.cause == null ? zzc.zzhbq : new zzc(false, zzdmt$zzc0.cause);
            }
            return zzdmt$zzc0;
        }
        if(zzdof0 instanceof zzdow) {
            Throwable throwable0 = zzdoz.zza(((zzdow)zzdof0));
            if(throwable0 != null) {
                return new zzb(throwable0);
            }
        }
        boolean z = zzdof0.isCancelled();
        if((!zzdmt.GENERATE_CANCELLATION_CAUSES & z) != 0) {
            return zzc.zzhbq;
        }
        try {
            Object object0 = zzdmt.getUninterruptibly(zzdof0);
            if(z) {
                return new zzc(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + zzdof0));
            }
            return object0 == null ? zzdmt.NULL : object0;
        }
        catch(ExecutionException executionException0) {
            return z ? new zzc(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + zzdof0, executionException0)) : new zzb(executionException0.getCause());
        }
        catch(CancellationException cancellationException0) {
            return !z ? new zzb(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + zzdof0, cancellationException0)) : new zzc(false, cancellationException0);
        }
        catch(Throwable throwable1) {
            return new zzb(throwable1);
        }
    }

    private static Object getUninterruptibly(Future future0) throws ExecutionException {
        Object object0;
        boolean z = false;
        while(true) {
            try {
                object0 = future0.get();
                break;
            }
            catch(InterruptedException unused_ex) {
                z = true;
            }
            catch(Throwable throwable0) {
                if(z) {
                    Thread.currentThread().interrupt();
                }
                throw throwable0;
            }
        }
        if(z) {
            Thread.currentThread().interrupt();
        }
        return object0;
    }

    protected void interruptTask() {
    }

    @Override
    public boolean isCancelled() {
        return this.value instanceof zzc;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean isDone() {
        return (this.value == null ? 0 : 1) & (this.value instanceof zze ? 0 : 1);
    }

    final void maybePropagateCancellationTo(@NullableDecl Future future0) {
        if((future0 != null & this.isCancelled()) != 0) {
            future0.cancel(this.wasInterrupted());
        }
    }

    // 去混淆评级： 低(20)
    @NullableDecl
    protected String pendingToString() {
        return this instanceof ScheduledFuture ? "remaining delay=[" + ((ScheduledFuture)this).getDelay(TimeUnit.MILLISECONDS) + " ms]" : null;
    }

    protected boolean set(@NullableDecl Object object0) {
        if(object0 == null) {
            object0 = zzdmt.NULL;
        }
        if(zzdmt.zzhbn.zza(this, null, object0)) {
            zzdmt.zza(this);
            return true;
        }
        return false;
    }

    protected boolean setException(Throwable throwable0) {
        zzb zzdmt$zzb0 = new zzb(((Throwable)zzdlg.checkNotNull(throwable0)));
        if(zzdmt.zzhbn.zza(this, null, zzdmt$zzb0)) {
            zzdmt.zza(this);
            return true;
        }
        return false;
    }

    protected final boolean setFuture(zzdof zzdof0) {
        zzb zzdmt$zzb0;
        zzdlg.checkNotNull(zzdof0);
        Object object0 = this.value;
        if(object0 == null) {
            if(zzdof0.isDone()) {
                Object object1 = zzdmt.getFutureValue(zzdof0);
                if(zzdmt.zzhbn.zza(this, null, object1)) {
                    zzdmt.zza(this);
                    return true;
                }
                return false;
            }
            zze zzdmt$zze0 = new zze(this, zzdof0);
            if(zzdmt.zzhbn.zza(this, null, zzdmt$zze0)) {
                try {
                    zzdof0.addListener(zzdmt$zze0, zzdnm.zzhcu);
                }
                catch(Throwable throwable0) {
                    try {
                        zzdmt$zzb0 = new zzb(throwable0);
                    }
                    catch(Throwable unused_ex) {
                        zzdmt$zzb0 = zzb.zzhbo;
                    }
                    zzdmt.zzhbn.zza(this, zzdmt$zze0, zzdmt$zzb0);
                }
                return true;
            }
            object0 = this.value;
        }
        if(object0 instanceof zzc) {
            zzdof0.cancel(((zzc)object0).wasInterrupted);
        }
        return false;
    }

    @Override
    public String toString() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    protected final boolean wasInterrupted() {
        return this.value instanceof zzc && ((zzc)this.value).wasInterrupted;
    }

    private final void zza(zzk zzdmt$zzk0) {
        zzdmt$zzk0.thread = null;
    alab1:
        while(true) {
            zzk zzdmt$zzk1 = this.waiters;
            if(zzdmt$zzk1 == zzk.zzhbz) {
                return;
            }
            zzk zzdmt$zzk2 = null;
            while(true) {
                if(zzdmt$zzk1 == null) {
                    break alab1;
                }
                zzk zzdmt$zzk3 = zzdmt$zzk1.next;
                if(zzdmt$zzk1.thread != null) {
                    zzdmt$zzk2 = zzdmt$zzk1;
                }
                else if(zzdmt$zzk2 == null) {
                    if(zzdmt.zzhbn.zza(this, zzdmt$zzk1, zzdmt$zzk3)) {
                        zzdmt$zzk1 = zzdmt$zzk3;
                        continue;
                    }
                    break;
                }
                else {
                    zzdmt$zzk2.next = zzdmt$zzk3;
                    if(zzdmt$zzk2.thread == null) {
                        break;
                    }
                }
                zzdmt$zzk1 = zzdmt$zzk3;
            }
        }
    }

    private static void zza(zzdmt zzdmt0) {
        Runnable runnable0;
        zzd zzdmt$zzd2;
        zzk zzdmt$zzk0;
        zzd zzdmt$zzd0 = null;
        while(true) {
            do {
            label_1:
                zzdmt$zzk0 = zzdmt0.waiters;
            }
            while(!zzdmt.zzhbn.zza(zzdmt0, zzdmt$zzk0, zzk.zzhbz));
            while(zzdmt$zzk0 != null) {
                Thread thread0 = zzdmt$zzk0.thread;
                if(thread0 != null) {
                    zzdmt$zzk0.thread = null;
                    LockSupport.unpark(thread0);
                }
                zzdmt$zzk0 = zzdmt$zzk0.next;
            }
            zzdmt0.afterDone();
            do {
                zzd zzdmt$zzd1 = zzdmt0.listeners;
            }
            while(!zzdmt.zzhbn.zza(zzdmt0, zzdmt$zzd1, zzd.zzhbr));
            zzdmt$zzd2 = zzdmt$zzd0;
            for(zzd zzdmt$zzd3 = zzdmt$zzd1; zzdmt$zzd3 != null; zzdmt$zzd3 = zzdmt$zzd4) {
                zzd zzdmt$zzd4 = zzdmt$zzd3.next;
                zzdmt$zzd3.next = zzdmt$zzd2;
                zzdmt$zzd2 = zzdmt$zzd3;
            }
        label_21:
            if(zzdmt$zzd2 != null) {
                zzdmt$zzd0 = zzdmt$zzd2.next;
                runnable0 = zzdmt$zzd2.task;
                if(runnable0 instanceof zze) {
                    zzdmt0 = ((zze)runnable0).zzhbs;
                    if(zzdmt0.value == ((zze)runnable0)) {
                        Object object0 = zzdmt.getFutureValue(((zze)runnable0).future);
                        if(zzdmt.zzhbn.zza(zzdmt0, ((zze)runnable0), object0)) {
                            goto label_1;
                        }
                    }
                }
                else {
                    zzdmt.zza(runnable0, zzdmt$zzd2.executor);
                    zzdmt$zzd2 = zzdmt$zzd0;
                    goto label_21;
                }
                break;
            }
            return;
        }
        zzdmt$zzd2 = zzdmt$zzd0;
        goto label_21;
    }

    private static void zza(Runnable runnable0, Executor executor0) {
        try {
            executor0.execute(runnable0);
        }
        catch(RuntimeException runtimeException0) {
            zzdmt.zzhbm.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + runnable0 + " with executor " + executor0, runtimeException0);
        }
    }

    private final void zza(StringBuilder stringBuilder0) {
        try {
            Object object0 = zzdmt.getUninterruptibly(this);
            stringBuilder0.append("SUCCESS, result=[");
            this.zza(stringBuilder0, object0);
            stringBuilder0.append("]");
        }
        catch(ExecutionException executionException0) {
            stringBuilder0.append("FAILURE, cause=[");
            stringBuilder0.append(executionException0.getCause());
            stringBuilder0.append("]");
        }
        catch(CancellationException unused_ex) {
            stringBuilder0.append("CANCELLED");
        }
        catch(RuntimeException runtimeException0) {
            stringBuilder0.append("UNKNOWN, cause=[");
            stringBuilder0.append(runtimeException0.getClass());
            stringBuilder0.append(" thrown from get()]");
        }
    }

    private final void zza(StringBuilder stringBuilder0, Object object0) {
        try {
            if(object0 == this) {
                stringBuilder0.append("this future");
                return;
            }
            stringBuilder0.append(object0);
        }
        catch(RuntimeException | StackOverflowError runtimeException0) {
            stringBuilder0.append("Exception thrown from implementation: ");
            stringBuilder0.append(runtimeException0.getClass());
        }
    }

    private static Object zzai(Object object0) throws ExecutionException {
        if(!(object0 instanceof zzc)) {
            if(object0 instanceof zzb) {
                throw new ExecutionException(((zzb)object0).exception);
            }
            return object0 == zzdmt.NULL ? null : object0;
        }
        CancellationException cancellationException0 = new CancellationException("Task was cancelled.");
        cancellationException0.initCause(((zzc)object0).cause);
        throw cancellationException0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdow
    @NullableDecl
    protected final Throwable zzauj() {
        if(this instanceof zzg) {
            return this.value instanceof zzb ? ((zzb)this.value).exception : null;
        }
        return null;
    }

    static boolean zzaul() [...] // 潜在的解密器

    class com.google.android.gms.internal.ads.zzdmt.1 {
    }

}

