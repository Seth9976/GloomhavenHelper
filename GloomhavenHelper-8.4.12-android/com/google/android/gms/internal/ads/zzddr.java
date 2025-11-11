package com.google.android.gms.internal.ads;

final class zzddr implements zzdnu {
    private final zzcsq zzggv;
    private final zzddu zzgov;
    private final zzddq zzgow;

    zzddr(zzddq zzddq0, zzcsq zzcsq0, zzddu zzddu0) {
        this.zzgow = zzddq0;
        this.zzggv = zzcsq0;
        this.zzgov = zzddu0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzgow) {
            this.zzggv.onSuccess(((zzcdn)object0));
            this.zzgow.zzgot.onAdMetadataChanged();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        synchronized(this.zzgow) {
            zzcdq zzcdq0 = (zzcdq)this.zzgow.zzgmg.zzaqj();
            if(zzcdq0 == null) {
                this.zzgow.zzd(this.zzgov).zzafa().zzadx().zzahr().zzaig();
            }
            else {
                zzcdq0.zzady().onAdFailedToLoad(zzcid.zzd(throwable0));
            }
            zzdfc.zzc(throwable0, "RewardedAdLoader.onFailure");
            this.zzggv.zzaow();
        }
    }
}

