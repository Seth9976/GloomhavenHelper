package com.google.android.gms.internal.ads;

final class zzbcm implements zznn {
    private final byte[] zzdmv;
    private final zznn zzedx;

    zzbcm(zznn zznn0, byte[] arr_b) {
        this.zzedx = zznn0;
        this.zzdmv = arr_b;
    }

    @Override  // com.google.android.gms.internal.ads.zznn
    public final zzno zzim() {
        zzno zzno0 = this.zzedx.zzim();
        return new zzbcq(new zznl(this.zzdmv), this.zzdmv.length, zzno0);
    }
}

