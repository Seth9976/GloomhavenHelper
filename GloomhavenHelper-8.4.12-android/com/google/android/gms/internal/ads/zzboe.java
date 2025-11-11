package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzboe implements zzdnu {
    private final zzbod zzfiz;

    zzboe(zzbod zzbod0) {
        this.zzfiz = zzbod0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@NullableDecl Object object0) {
        Void void0 = (Void)object0;
        this.zzfiz.zzfiv.zzbh(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzfiz.zzfiv.zzbh(false);
    }
}

