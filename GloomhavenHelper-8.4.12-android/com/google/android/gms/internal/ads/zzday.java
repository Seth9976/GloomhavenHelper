package com.google.android.gms.internal.ads;

final class zzday implements zzdnu {
    private final zzcsq zzggv;
    private final zzdax zzgmi;
    private final zzdat zzgmj;

    zzday(zzdat zzdat0, zzcsq zzcsq0, zzdax zzdax0) {
        this.zzgmj = zzdat0;
        this.zzggv = zzcsq0;
        this.zzgmi = zzdax0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzgmj) {
            zzdat.zza(this.zzgmj, null);
            this.zzggv.onSuccess(((zzbla)object0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        synchronized(this.zzgmj) {
            zzdat.zza(this.zzgmj, null);
            zzbku zzbku0 = (zzbku)this.zzgmj.zzgmg.zzaqj();
            if(zzbku0 == null) {
                this.zzgmj.zza(this.zzgmi).zzaeh().zzadx().zzahr().zzaig();
            }
            else {
                zzbku0.zzady().onAdFailedToLoad(zzcid.zzd(throwable0));
            }
            zzdfc.zzc(throwable0, "AppOpenAdLoader.onFailure");
            this.zzggv.zzaow();
        }
    }
}

