package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;

public final class zzaex extends zzadq {
    private final OnAppInstallAdLoadedListener zzcxl;

    public zzaex(OnAppInstallAdLoadedListener nativeAppInstallAd$OnAppInstallAdLoadedListener0) {
        this.zzcxl = nativeAppInstallAd$OnAppInstallAdLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadr
    public final void zza(zzadf zzadf0) {
        zzadg zzadg0 = new zzadg(zzadf0);
        this.zzcxl.onAppInstallAdLoaded(zzadg0);
    }
}

