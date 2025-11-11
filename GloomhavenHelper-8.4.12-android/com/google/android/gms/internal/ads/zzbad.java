package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

final class zzbad implements zzdnu {
    private final zzbac zzdxx;
    private final zzbaa zzdxy;

    zzbad(zzbab zzbab0, zzbac zzbac0, zzbaa zzbaa0) {
        this.zzdxx = zzbac0;
        this.zzdxy = zzbaa0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@Nullable Object object0) {
        this.zzdxx.zzh(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzdxy.run();
    }
}

