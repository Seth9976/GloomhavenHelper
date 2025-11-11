package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback;
import java.lang.ref.WeakReference;

public final class zzre extends zzrk {
    private final WeakReference zzbrf;

    public zzre(AppOpenAdLoadCallback appOpenAd$AppOpenAdLoadCallback0) {
        this.zzbrf = new WeakReference(appOpenAd$AppOpenAdLoadCallback0);
    }

    @Override  // com.google.android.gms.internal.ads.zzrh
    public final void onAppOpenAdFailedToLoad(int v) {
        boolean z = ((AppOpenAdLoadCallback)this.zzbrf.get()) == null;
    }

    @Override  // com.google.android.gms.internal.ads.zzrh
    public final void zza(zzrg zzrg0) {
        AppOpenAdLoadCallback appOpenAd$AppOpenAdLoadCallback0 = (AppOpenAdLoadCallback)this.zzbrf.get();
        if(appOpenAd$AppOpenAdLoadCallback0 != null) {
            appOpenAd$AppOpenAdLoadCallback0.onAppOpenAdLoaded(new zzrn(zzrg0));
        }
    }
}

