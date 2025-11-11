package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;

public final class zzaew extends zzadv {
    private final OnContentAdLoadedListener zzcxk;

    public zzaew(OnContentAdLoadedListener nativeContentAd$OnContentAdLoadedListener0) {
        this.zzcxk = nativeContentAd$OnContentAdLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzads
    public final void zza(zzadj zzadj0) {
        zzadk zzadk0 = new zzadk(zzadj0);
        this.zzcxk.onContentAdLoaded(zzadk0);
    }
}

