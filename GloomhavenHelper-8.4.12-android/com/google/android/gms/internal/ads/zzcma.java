package com.google.android.gms.internal.ads;

public final class zzcma {
    private zzbqp zzfmi;
    private zzdei zzfmj;

    public zzcma(zzdei zzdei0) {
        this.zzfmj = zzdei0;
    }

    public final void zza(zzbqp zzbqp0) {
        this.zzfmi = zzbqp0;
    }

    public final void zzaoc() {
        if(this.zzfmi == null) {
            return;
        }
        if(this.zzfmj.zzgqb == 2) {
            this.zzfmi.onAdImpression();
        }
    }
}

