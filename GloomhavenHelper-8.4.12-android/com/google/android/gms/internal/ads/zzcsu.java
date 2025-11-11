package com.google.android.gms.internal.ads;

final class zzcsu implements zzdnu {
    private final zzcsq zzggv;
    private final zzbxo zzggw;
    private final zzcss zzggx;

    zzcsu(zzcss zzcss0, zzcsq zzcsq0, zzbxo zzbxo0) {
        this.zzggx = zzcss0;
        this.zzggv = zzcsq0;
        this.zzggw = zzbxo0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzggx) {
            this.zzggv.onSuccess(((zzbnf)object0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzggw.zzady().onAdFailedToLoad(zzcid.zzd(throwable0));
        zzdfc.zzc(throwable0, "NativeAdLoader.onFailure");
        this.zzggv.zzaow();
    }
}

