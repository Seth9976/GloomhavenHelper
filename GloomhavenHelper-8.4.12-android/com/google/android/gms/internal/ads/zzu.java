package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzu {
    private final zzn zzaa;
    private final AtomicInteger zzav;
    private final Set zzaw;
    private final PriorityBlockingQueue zzax;
    private final PriorityBlockingQueue zzay;
    private final zzm[] zzaz;
    private final List zzba;
    private final List zzbb;
    private final zza zzc;
    private final zzaa zzd;
    private zzc zzo;

    public zzu(zza zza0, zzn zzn0) {
        this(zza0, zzn0, 4);
    }

    private zzu(zza zza0, zzn zzn0, int v) {
        this(zza0, zzn0, 4, new zzj(new Handler(Looper.getMainLooper())));
    }

    private zzu(zza zza0, zzn zzn0, int v, zzaa zzaa0) {
        this.zzav = new AtomicInteger();
        this.zzaw = new HashSet();
        this.zzax = new PriorityBlockingQueue();
        this.zzay = new PriorityBlockingQueue();
        this.zzba = new ArrayList();
        this.zzbb = new ArrayList();
        this.zzc = zza0;
        this.zzaa = zzn0;
        this.zzaz = new zzm[4];
        this.zzd = zzaa0;
    }

    public final void start() {
        zzc zzc0 = this.zzo;
        if(zzc0 != null) {
            zzc0.quit();
        }
        zzm[] arr_zzm = this.zzaz;
        for(int v1 = 0; v1 < arr_zzm.length; ++v1) {
            zzm zzm0 = arr_zzm[v1];
            if(zzm0 != null) {
                zzm0.quit();
            }
        }
        this.zzo = new zzc(this.zzax, this.zzay, this.zzc, this.zzd);
        this.zzo.start();
        for(int v = 0; v < this.zzaz.length; ++v) {
            zzm zzm1 = new zzm(this.zzay, this.zzaa, this.zzc, this.zzd);
            this.zzaz[v] = zzm1;
            zzm1.start();
        }
    }

    final void zza(zzq zzq0, int v) {
        synchronized(this.zzbb) {
            for(Object object0: this.zzbb) {
                ((zzx)object0).zzb(zzq0, v);
            }
        }
    }

    public final zzq zze(zzq zzq0) {
        zzq0.zza(this);
        synchronized(this.zzaw) {
            this.zzaw.add(zzq0);
        }
        zzq0.zzb(this.zzav.incrementAndGet());
        zzq0.zzb("add-to-queue");
        this.zza(zzq0, 0);
        if(!zzq0.zzh()) {
            this.zzay.add(zzq0);
            return zzq0;
        }
        this.zzax.add(zzq0);
        return zzq0;
    }

    final void zzf(zzq zzq0) {
        synchronized(this.zzaw) {
            this.zzaw.remove(zzq0);
        }
        synchronized(this.zzba) {
            for(Object object0: this.zzba) {
                ((zzw)object0).zzg(zzq0);
            }
        }
        this.zza(zzq0, 5);
    }
}

