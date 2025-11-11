package com.google.android.gms.internal.ads;

import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import javax.annotation.concurrent.GuardedBy;

public final class zzdfe {
    private final zzdoe zzfrv;
    @GuardedBy("this")
    private final Deque zzgra;
    private final Callable zzgrb;

    public zzdfe(Callable callable0, zzdoe zzdoe0) {
        this.zzgra = new LinkedBlockingDeque();
        this.zzgrb = callable0;
        this.zzfrv = zzdoe0;
    }

    public final zzdof zzard() {
        synchronized(this) {
            this.zzdm(1);
            return (zzdof)this.zzgra.poll();
        }
    }

    public final void zzd(zzdof zzdof0) {
        synchronized(this) {
            this.zzgra.addFirst(zzdof0);
        }
    }

    public final void zzdm(int v) {
        synchronized(this) {
            int v2 = this.zzgra.size();
            for(int v3 = 0; v3 < v - v2; ++v3) {
                zzdof zzdof0 = this.zzfrv.zzd(this.zzgrb);
                this.zzgra.add(zzdof0);
            }
        }
    }
}

