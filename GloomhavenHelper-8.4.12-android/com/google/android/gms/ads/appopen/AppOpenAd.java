package com.google.android.gms.ads.appopen;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzrm;
import com.google.android.gms.internal.ads.zzrq;
import com.google.android.gms.internal.ads.zzvx;

public abstract class AppOpenAd {
    public static class AppOpenAdLoadCallback {
        public void onAppOpenAdFailedToLoad(int v) {
        }

        public void onAppOpenAdLoaded(AppOpenAd appOpenAd0) {
        }
    }

    public @interface AppOpenAdOrientation {
    }

    public static final int APP_OPEN_AD_ORIENTATION_LANDSCAPE = 2;
    public static final int APP_OPEN_AD_ORIENTATION_PORTRAIT = 1;

    public static void load(Context context0, String s, AdRequest adRequest0, @AppOpenAdOrientation int v, AppOpenAdLoadCallback appOpenAd$AppOpenAdLoadCallback0) {
        Preconditions.checkNotNull(context0, "Context cannot be null.");
        Preconditions.checkNotNull(s, "adUnitId cannot be null.");
        Preconditions.checkNotNull(adRequest0, "AdRequest cannot be null.");
        new zzrq(context0, s, adRequest0.zzdl(), v, appOpenAd$AppOpenAdLoadCallback0).zzms();
    }

    public static void load(Context context0, String s, PublisherAdRequest publisherAdRequest0, @AppOpenAdOrientation int v, AppOpenAdLoadCallback appOpenAd$AppOpenAdLoadCallback0) {
        Preconditions.checkNotNull(context0, "Context cannot be null.");
        Preconditions.checkNotNull(s, "adUnitId cannot be null.");
        Preconditions.checkNotNull(publisherAdRequest0, "PublisherAdRequest cannot be null.");
        new zzrq(context0, s, publisherAdRequest0.zzdl(), v, appOpenAd$AppOpenAdLoadCallback0).zzms();
    }

    protected abstract void zza(zzrm arg1);

    protected abstract zzvx zzdr();
}

