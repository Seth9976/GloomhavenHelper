package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

public final class zzaav extends zzaat {
    private final OnCustomRenderedAdLoadedListener zzcfg;

    public zzaav(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener0) {
        this.zzcfg = onCustomRenderedAdLoadedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaaq
    public final void zza(zzaap zzaap0) {
        zzaam zzaam0 = new zzaam(zzaap0);
        this.zzcfg.onCustomRenderedAdLoaded(zzaam0);
    }
}

