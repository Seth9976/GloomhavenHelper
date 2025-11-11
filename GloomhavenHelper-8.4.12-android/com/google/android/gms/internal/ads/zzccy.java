package com.google.android.gms.internal.ads;

final class zzccy implements zzdnu {
    private final String zzftn;
    private final zzafz zzfto;

    zzccy(zzccv zzccv0, String s, zzafz zzafz0) {
        this.zzftn = s;
        this.zzfto = zzafz0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        ((zzbdv)object0).zzb(this.zzftn, this.zzfto);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
    }
}

