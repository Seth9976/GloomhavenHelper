package com.google.android.gms.internal.ads;

final class zzbns implements zzdnu {
    private final zzdnu zzfih;
    private final zzbnm zzfii;

    zzbns(zzbnm zzbnm0, zzdnu zzdnu0) {
        this.zzfii = zzbnm0;
        this.zzfih = zzdnu0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        this.zzfii.zzahk();
        this.zzfih.onSuccess(((zzbnf)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzfii.zzahk();
        this.zzfih.zzb(throwable0);
    }
}

