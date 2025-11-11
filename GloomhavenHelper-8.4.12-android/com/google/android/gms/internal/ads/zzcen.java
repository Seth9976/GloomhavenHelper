package com.google.android.gms.internal.ads;

final class zzcen implements zzaoz {
    private final zzced zzfuo;

    zzcen(zzced zzced0) {
        this.zzfuo = zzced0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzaoz
    public final void zzc(int v, int v1, int v2, int v3) {
        this.zzfuo.zzfil.onAdOpened();
    }

    @Override  // com.google.android.gms.internal.ads.zzaoz
    public final void zztn() {
        this.zzfuo.zzfil.onAdClosed();
    }

    @Override  // com.google.android.gms.internal.ads.zzaoz
    public final void zzto() {
        this.zzfuo.zzful.zzagw();
    }
}

