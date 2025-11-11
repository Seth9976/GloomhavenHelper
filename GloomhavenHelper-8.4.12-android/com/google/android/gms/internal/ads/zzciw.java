package com.google.android.gms.internal.ads;

final class zzciw implements zzdnu {
    private final zzcix zzfxw;

    zzciw(zzcix zzcix0) {
        this.zzfxw = zzcix0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        this.zzfxw.zzfxx.zzb(((zzdeq)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
    }
}

