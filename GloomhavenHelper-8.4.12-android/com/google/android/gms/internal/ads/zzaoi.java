package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;

final class zzaoi implements Runnable {
    private final AdOverlayInfoParcel zzdgb;
    private final zzaog zzdgc;

    zzaoi(zzaog zzaog0, AdOverlayInfoParcel adOverlayInfoParcel0) {
        this.zzdgc = zzaog0;
        this.zzdgb = adOverlayInfoParcel0;
        super();
    }

    @Override
    public final void run() {
        zzl.zza(this.zzdgc.zzdfz, this.zzdgb, true);
    }
}

