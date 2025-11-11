package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzv;

final class zzcdc implements zzv {
    private final zzbrc zzftr;

    private zzcdc(zzbrc zzbrc0) {
        this.zzftr = zzbrc0;
    }

    static zzv zza(zzbrc zzbrc0) {
        return new zzcdc(zzbrc0);
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzv
    public final void zzub() {
        this.zzftr.onAdLeftApplication();
    }
}

