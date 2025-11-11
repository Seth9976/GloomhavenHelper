package com.google.android.gms.internal.ads;

final class zzdie implements zzdnu {
    private final zzdhs zzgvc;
    private final zzdhx zzgvd;

    zzdie(zzdhx zzdhx0, zzdhs zzdhs0) {
        this.zzgvd = zzdhx0;
        this.zzgvc = zzdhs0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        this.zzgvd.zzguv.zzguq.zzc(this.zzgvc);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzgvd.zzguv.zzguq.zza(this.zzgvc, throwable0);
    }
}

