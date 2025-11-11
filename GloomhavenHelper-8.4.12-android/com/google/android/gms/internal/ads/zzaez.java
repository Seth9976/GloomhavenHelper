package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;

public final class zzaez extends zzadw {
    private final OnCustomClickListener zzcxn;

    public zzaez(OnCustomClickListener nativeCustomTemplateAd$OnCustomClickListener0) {
        this.zzcxn = nativeCustomTemplateAd$OnCustomClickListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadx
    public final void zza(zzadn zzadn0, String s) {
        zzado zzado0 = zzado.zza(zzadn0);
        this.zzcxn.onCustomClick(zzado0, s);
    }
}

