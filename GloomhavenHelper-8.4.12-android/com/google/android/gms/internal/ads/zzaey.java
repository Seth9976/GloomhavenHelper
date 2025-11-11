package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;

public final class zzaey extends zzaeb {
    private final OnCustomTemplateAdLoadedListener zzcxm;

    public zzaey(OnCustomTemplateAdLoadedListener nativeCustomTemplateAd$OnCustomTemplateAdLoadedListener0) {
        this.zzcxm = nativeCustomTemplateAd$OnCustomTemplateAdLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzady
    public final void zzb(zzadn zzadn0) {
        zzado zzado0 = zzado.zza(zzadn0);
        this.zzcxm.onCustomTemplateAdLoaded(zzado0);
    }
}

