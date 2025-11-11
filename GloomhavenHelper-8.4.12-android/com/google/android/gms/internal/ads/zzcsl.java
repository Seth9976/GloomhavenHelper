package com.google.android.gms.internal.ads;

final class zzcsl implements zzdnu {
    private final zzbwt zzggk;
    private final zzcsi zzggl;

    zzcsl(zzcsi zzcsi0, zzbwt zzbwt0) {
        this.zzggl = zzcsi0;
        this.zzggk = zzbwt0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzggl) {
            zzcsi.zza(this.zzggl, null);
            zzcsi.zza(this.zzggl, ((zzbvu)object0));
            ((zzbvu)object0).zzags();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        synchronized(this.zzggl) {
            zzcsi.zza(this.zzggl, null);
            this.zzggk.zzady().onAdFailedToLoad(zzcid.zzd(throwable0));
            zzdfc.zzc(throwable0, "InterstitialAdManagerShim.onFailure");
        }
    }
}

