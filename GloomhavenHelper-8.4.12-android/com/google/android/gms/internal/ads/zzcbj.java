package com.google.android.gms.internal.ads;

final class zzcbj implements zzdng {
    private final zzdof zzfsq;

    zzcbj(zzdof zzdof0) {
        this.zzfsq = zzdof0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        zzdof zzdof0 = this.zzfsq;
        if(((zzbdv)object0) == null || ((zzbdv)object0).zzyq() == null) {
            throw new zzcpe("Retrieve video view in instream ad response failed.", 0);
        }
        return zzdof0;
    }
}

