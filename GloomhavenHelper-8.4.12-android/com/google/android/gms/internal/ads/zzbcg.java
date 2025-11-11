package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbcg implements zzhd {
    private int zzbfq;
    private final zzns zzedo;
    private long zzedp;
    private long zzedq;
    private long zzedr;
    private long zzeds;
    private boolean zzedt;

    zzbcg() {
        this(15000, 30000, 2500L, 5000L);
    }

    private zzbcg(int v, int v1, long v2, long v3) {
        this.zzedo = new zzns(true, 0x10000);
        this.zzedp = 15000000L;
        this.zzedq = 30000000L;
        this.zzedr = 2500000L;
        this.zzeds = 5000000L;
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final void onStopped() {
        this.zzk(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final void zza(zzhe[] arr_zzhe, zzmu zzmu0, zznf zznf0) {
        this.zzbfq = 0;
        for(int v = 0; v < arr_zzhe.length; ++v) {
            if(zznf0.zzay(v) != null) {
                this.zzbfq += zzop.zzbl(arr_zzhe[v].getTrackType());
            }
        }
        this.zzedo.zzbb(this.zzbfq);
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final boolean zzc(long v, boolean z) {
        long v1;
        synchronized(this) {
            v1 = z ? this.zzeds : this.zzedr;
        }
        return v1 <= 0L || v >= v1;
    }

    public final void zzcx(int v) {
        synchronized(this) {
            this.zzedr = ((long)v) * 1000L;
        }
    }

    public final void zzcy(int v) {
        synchronized(this) {
            this.zzeds = ((long)v) * 1000L;
        }
    }

    public final void zzdc(int v) {
        synchronized(this) {
            this.zzedp = ((long)v) * 1000L;
        }
    }

    public final void zzdd(int v) {
        synchronized(this) {
            this.zzedq = ((long)v) * 1000L;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final boolean zzdt(long v) {
        int v2;
        boolean z = false;
        synchronized(this) {
            if(v > this.zzedq) {
                v2 = 0;
            }
            else {
                v2 = v >= this.zzedp ? 1 : 2;
            }
            boolean z1 = this.zzedo.zzio() >= this.zzbfq;
            if(v2 == 2 || v2 == 1 && this.zzedt && !z1) {
                z = true;
            }
            this.zzedt = z;
            return this.zzedt;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final void zzew() {
        this.zzk(false);
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final void zzex() {
        this.zzk(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzhd
    public final zznm zzey() {
        return this.zzedo;
    }

    @VisibleForTesting
    private final void zzk(boolean z) {
        this.zzbfq = 0;
        this.zzedt = false;
        if(z) {
            this.zzedo.reset();
        }
    }
}

