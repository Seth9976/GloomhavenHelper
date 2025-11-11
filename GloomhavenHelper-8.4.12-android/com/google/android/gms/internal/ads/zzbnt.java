package com.google.android.gms.internal.ads;

final class zzbnt implements zzdnu {
    private final zzdnu zzfih;
    private final zzbnm zzfii;

    zzbnt(zzbnm zzbnm0, zzdnu zzdnu0) {
        this.zzfii = zzbnm0;
        this.zzfih = zzdnu0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        this.zzfii.zza(((zzbnl)object0).zzfia, this.zzfih);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzfih.zzb(throwable0);
        this.zzfii.zzahk();
    }
}

