package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

public final class zzud extends zzvn {
    private final AdListener zzcck;

    public zzud(AdListener adListener0) {
        this.zzcck = adListener0;
    }

    public final AdListener getAdListener() {
        return this.zzcck;
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdClicked() {
        this.zzcck.onAdClicked();
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdClosed() {
        this.zzcck.onAdClosed();
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdFailedToLoad(int v) {
        this.zzcck.onAdFailedToLoad(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdImpression() {
        this.zzcck.onAdImpression();
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdLeftApplication() {
        this.zzcck.onAdLeftApplication();
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdLoaded() {
        this.zzcck.onAdLoaded();
    }

    @Override  // com.google.android.gms.internal.ads.zzvk
    public final void onAdOpened() {
        this.zzcck.onAdOpened();
    }
}

