package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

final class zzbae implements zzdnu {
    private final zzbab zzdxz;

    zzbae(zzbab zzbab0) {
        this.zzdxz = zzbab0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@Nullable Object object0) {
        this.zzdxz.zzdxw.set(1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzdxz.zzdxw.set(-1);
    }
}

