package com.google.android.gms.internal.ads;

final class zzcbl implements zzdng {
    private final zzdof zzfsq;

    zzcbl(zzdof zzdof0) {
        this.zzfsq = zzdof0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return object0 == null ? zzdnt.immediateFailedFuture(new zzcpe("Retrieve required value in native ad response failed.", 0)) : this.zzfsq;
    }
}

