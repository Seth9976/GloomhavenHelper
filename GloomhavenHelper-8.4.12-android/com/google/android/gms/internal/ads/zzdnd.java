package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzdnd extends zzj {
    static final class zza extends zzb {
        private final AtomicReferenceFieldUpdater zzhcm;
        private final AtomicIntegerFieldUpdater zzhcn;

        zza(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0) {
            super(null);
            this.zzhcm = atomicReferenceFieldUpdater0;
            this.zzhcn = atomicIntegerFieldUpdater0;
        }

        @Override  // com.google.android.gms.internal.ads.zzdnd$zzb
        final void zza(zzdnd zzdnd0, Set set0, Set set1) {
            this.zzhcm.compareAndSet(zzdnd0, null, set1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdnd$zzb
        final int zzc(zzdnd zzdnd0) {
            return this.zzhcn.decrementAndGet(zzdnd0);
        }
    }

    static abstract class zzb {
        private zzb() {
        }

        zzb(zzdnc zzdnc0) {
        }

        abstract void zza(zzdnd arg1, Set arg2, Set arg3);

        abstract int zzc(zzdnd arg1);
    }

    static final class zzc extends zzb {
        private zzc() {
            super(null);
        }

        zzc(zzdnc zzdnc0) {
        }

        @Override  // com.google.android.gms.internal.ads.zzdnd$zzb
        final void zza(zzdnd zzdnd0, Set set0, Set set1) {
            synchronized(zzdnd0) {
                if(zzdnd0.seenExceptions == null) {
                    zzdnd0.seenExceptions = set1;
                }
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdnd$zzb
        final int zzc(zzdnd zzdnd0) {
            synchronized(zzdnd0) {
            }
            return zzdnd.zzb(zzdnd0);
        }
    }

    private volatile int remaining;
    private volatile Set seenExceptions;
    private static final Logger zzhbm;
    private static final zzb zzhcl;

    static {
        zza zzdnd$zza0;
        Throwable throwable0;
        zzdnd.zzhbm = Logger.getLogger(zzdnd.class.getName());
        try {
            throwable0 = throwable1;
            zzdnd$zza0 = new zza(AtomicReferenceFieldUpdater.newUpdater(zzdnd.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(zzdnd.class, "remaining"));
            throwable0 = null;
        }
        catch(Throwable unused_ex) {
            zzdnd$zza0 = new zzc(null);
        }
        zzdnd.zzhcl = zzdnd$zza0;
        if(throwable0 != null) {
            zzdnd.zzhbm.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", throwable0);
        }
    }

    zzdnd(int v) {
        this.seenExceptions = null;
        this.remaining = v;
    }

    final Set zzauo() {
        Set set0 = this.seenExceptions;
        if(set0 == null) {
            Set set1 = Collections.newSetFromMap(new ConcurrentHashMap());
            this.zzh(set1);
            zzdnd.zzhcl.zza(this, null, set1);
            return this.seenExceptions;
        }
        return set0;
    }

    final int zzaup() {
        return zzdnd.zzhcl.zzc(this);
    }

    final void zzauq() {
        this.seenExceptions = null;
    }

    static int zzb(zzdnd zzdnd0) {
        int v = zzdnd0.remaining - 1;
        zzdnd0.remaining = v;
        return v;
    }

    abstract void zzh(Set arg1);
}

