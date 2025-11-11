package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzcfp implements zzbqm, zzbqu, zzbrn, zzbsn, zzbth, zztz {
    private final zzsn zzfvb;
    @GuardedBy("this")
    private boolean zzfvc;
    @GuardedBy("this")
    private boolean zzfvd;

    public zzcfp(zzsn zzsn0, @Nullable zzdcu zzdcu0) {
        this.zzfvc = false;
        this.zzfvd = false;
        this.zzfvb = zzsn0;
        zzsn0.zza(zza.zzbsp);
        if(zzdcu0 != null) {
            zzsn0.zza(zza.zzbtw);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        synchronized(this) {
            if(!this.zzfvd) {
                this.zzfvb.zza(zza.zzbss);
                this.zzfvd = true;
                return;
            }
            this.zzfvb.zza(zza.zzbst);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        switch(v) {
            case 1: {
                this.zzfvb.zza(zza.zzbti);
                return;
            }
            case 2: {
                this.zzfvb.zza(zza.zzbtj);
                return;
            }
            case 3: {
                this.zzfvb.zza(zza.zzbth);
                return;
            }
            case 4: {
                this.zzfvb.zza(zza.zzbtk);
                return;
            }
            case 5: {
                this.zzfvb.zza(zza.zzbtl);
                return;
            }
            case 6: {
                this.zzfvb.zza(zza.zzbtm);
                return;
            }
            case 7: {
                this.zzfvb.zza(zza.zzbtn);
                return;
            }
            default: {
                this.zzfvb.zza(zza.zzbtg);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        synchronized(this) {
            this.zzfvb.zza(zza.zzbsr);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        this.zzfvb.zza(zza.zzbsq);
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zza(com.google.android.gms.internal.ads.zzsz.zza zzsz$zza0) {
        zzcfr zzcfr0 = new zzcfr(zzsz$zza0);
        this.zzfvb.zza(zzcfr0);
        this.zzfvb.zza(zza.zzbty);
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zzaig() {
        this.zzfvb.zza(zza.zzbue);
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzb(zzdeq zzdeq0) {
        zzcfo zzcfo0 = new zzcfo(zzdeq0);
        this.zzfvb.zza(zzcfo0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zzb(com.google.android.gms.internal.ads.zzsz.zza zzsz$zza0) {
        zzcfq zzcfq0 = new zzcfq(zzsz$zza0);
        this.zzfvb.zza(zzcfq0);
        this.zzfvb.zza(zza.zzbtx);
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zzbg(boolean z) {
        this.zzfvb.zza((z ? zza.zzbua : zza.zzbub));
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zzbh(boolean z) {
        this.zzfvb.zza((z ? zza.zzbuc : zza.zzbud));
    }

    @Override  // com.google.android.gms.internal.ads.zzbth
    public final void zzc(com.google.android.gms.internal.ads.zzsz.zza zzsz$zza0) {
        zzcft zzcft0 = new zzcft(zzsz$zza0);
        this.zzfvb.zza(zzcft0);
        this.zzfvb.zza(zza.zzbtz);
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzd(zzaqx zzaqx0) {
    }
}

