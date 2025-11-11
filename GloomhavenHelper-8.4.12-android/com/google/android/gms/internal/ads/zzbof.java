package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbof implements zzdnu {
    private final zzbod zzfiz;

    zzbof(zzbod zzbod0) {
        this.zzfiz = zzbod0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@NullableDecl Object object0) {
        zzaqx zzaqx0 = (zzaqx)object0;
        this.zzfiz.zzfiv.zzbg(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzfiz.zzfiv.zzbg(false);
    }
}

