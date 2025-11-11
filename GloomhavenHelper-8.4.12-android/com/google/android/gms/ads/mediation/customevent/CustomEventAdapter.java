package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzazh;

@KeepForSdkWithMembers
@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    @VisibleForTesting
    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzekm;
        private final MediationBannerListener zzekn;

        public zza(CustomEventAdapter customEventAdapter0, MediationBannerListener mediationBannerListener0) {
            this.zzekm = customEventAdapter0;
            this.zzekn = mediationBannerListener0;
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzazh.zzeb("Custom event adapter called onAdClicked.");
            this.zzekn.onAdClicked(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzazh.zzeb("Custom event adapter called onAdClosed.");
            this.zzekn.onAdClosed(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int v) {
            zzazh.zzeb("Custom event adapter called onAdFailedToLoad.");
            this.zzekn.onAdFailedToLoad(this.zzekm, v);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzazh.zzeb("Custom event adapter called onAdLeftApplication.");
            this.zzekn.onAdLeftApplication(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener
        public final void onAdLoaded(View view0) {
            zzazh.zzeb("Custom event adapter called onAdLoaded.");
            this.zzekm.zza(view0);
            this.zzekn.onAdLoaded(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzazh.zzeb("Custom event adapter called onAdOpened.");
            this.zzekn.onAdOpened(this.zzekm);
        }
    }

    @VisibleForTesting
    static final class zzb implements CustomEventNativeListener {
        private final CustomEventAdapter zzekm;
        private final MediationNativeListener zzekr;

        public zzb(CustomEventAdapter customEventAdapter0, MediationNativeListener mediationNativeListener0) {
            this.zzekm = customEventAdapter0;
            this.zzekr = mediationNativeListener0;
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzazh.zzeb("Custom event adapter called onAdClicked.");
            this.zzekr.onAdClicked(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzazh.zzeb("Custom event adapter called onAdClosed.");
            this.zzekr.onAdClosed(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int v) {
            zzazh.zzeb("Custom event adapter called onAdFailedToLoad.");
            this.zzekr.onAdFailedToLoad(this.zzekm, v);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdImpression() {
            zzazh.zzeb("Custom event adapter called onAdImpression.");
            this.zzekr.onAdImpression(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzazh.zzeb("Custom event adapter called onAdLeftApplication.");
            this.zzekr.onAdLeftApplication(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdLoaded(NativeAdMapper nativeAdMapper0) {
            zzazh.zzeb("Custom event adapter called onAdLoaded.");
            this.zzekr.onAdLoaded(this.zzekm, nativeAdMapper0);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
        public final void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper0) {
            zzazh.zzeb("Custom event adapter called onAdLoaded.");
            this.zzekr.onAdLoaded(this.zzekm, unifiedNativeAdMapper0);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzazh.zzeb("Custom event adapter called onAdOpened.");
            this.zzekr.onAdOpened(this.zzekm);
        }
    }

    @VisibleForTesting
    final class zzc implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzekm;
        private final MediationInterstitialListener zzeks;
        private final CustomEventAdapter zzekt;

        public zzc(CustomEventAdapter customEventAdapter1, MediationInterstitialListener mediationInterstitialListener0) {
            this.zzekm = customEventAdapter1;
            this.zzeks = mediationInterstitialListener0;
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClicked() {
            zzazh.zzeb("Custom event adapter called onAdClicked.");
            this.zzeks.onAdClicked(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdClosed() {
            zzazh.zzeb("Custom event adapter called onAdClosed.");
            this.zzeks.onAdClosed(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdFailedToLoad(int v) {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzeks.onAdFailedToLoad(this.zzekm, v);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdLeftApplication() {
            zzazh.zzeb("Custom event adapter called onAdLeftApplication.");
            this.zzeks.onAdLeftApplication(this.zzekm);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
        public final void onAdLoaded() {
            zzazh.zzeb("Custom event adapter called onReceivedAd.");
            this.zzeks.onAdLoaded(CustomEventAdapter.this);
        }

        @Override  // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public final void onAdOpened() {
            zzazh.zzeb("Custom event adapter called onAdOpened.");
            this.zzeks.onAdOpened(this.zzekm);
        }
    }

    @VisibleForTesting
    private CustomEventBanner zzeko;
    @VisibleForTesting
    private CustomEventInterstitial zzekp;
    @VisibleForTesting
    private CustomEventNative zzekq;
    private View zzml;

    @Override  // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final View getBannerView() {
        return this.zzml;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        CustomEventBanner customEventBanner0 = this.zzeko;
        if(customEventBanner0 != null) {
            customEventBanner0.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial0 = this.zzekp;
        if(customEventInterstitial0 != null) {
            customEventInterstitial0.onDestroy();
        }
        CustomEventNative customEventNative0 = this.zzekq;
        if(customEventNative0 != null) {
            customEventNative0.onDestroy();
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        CustomEventBanner customEventBanner0 = this.zzeko;
        if(customEventBanner0 != null) {
            customEventBanner0.onPause();
        }
        CustomEventInterstitial customEventInterstitial0 = this.zzekp;
        if(customEventInterstitial0 != null) {
            customEventInterstitial0.onPause();
        }
        CustomEventNative customEventNative0 = this.zzekq;
        if(customEventNative0 != null) {
            customEventNative0.onPause();
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        CustomEventBanner customEventBanner0 = this.zzeko;
        if(customEventBanner0 != null) {
            customEventBanner0.onResume();
        }
        CustomEventInterstitial customEventInterstitial0 = this.zzekp;
        if(customEventInterstitial0 != null) {
            customEventInterstitial0.onResume();
        }
        CustomEventNative customEventNative0 = this.zzekq;
        if(customEventNative0 != null) {
            customEventNative0.onResume();
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public final void requestBannerAd(Context context0, MediationBannerListener mediationBannerListener0, Bundle bundle0, AdSize adSize0, MediationAdRequest mediationAdRequest0, Bundle bundle1) {
        this.zzeko = (CustomEventBanner)CustomEventAdapter.zzao(bundle0.getString("class_name"));
        if(this.zzeko == null) {
            mediationBannerListener0.onAdFailedToLoad(this, 0);
            return;
        }
        Bundle bundle2 = bundle1 == null ? null : bundle1.getBundle(bundle0.getString("class_name"));
        this.zzeko.requestBannerAd(context0, new zza(this, mediationBannerListener0), bundle0.getString("parameter"), adSize0, mediationAdRequest0, bundle2);
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context0, MediationInterstitialListener mediationInterstitialListener0, Bundle bundle0, MediationAdRequest mediationAdRequest0, Bundle bundle1) {
        this.zzekp = (CustomEventInterstitial)CustomEventAdapter.zzao(bundle0.getString("class_name"));
        if(this.zzekp == null) {
            mediationInterstitialListener0.onAdFailedToLoad(this, 0);
            return;
        }
        Bundle bundle2 = bundle1 == null ? null : bundle1.getBundle(bundle0.getString("class_name"));
        this.zzekp.requestInterstitialAd(context0, new zzc(this, this, mediationInterstitialListener0), bundle0.getString("parameter"), mediationAdRequest0, bundle2);
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void requestNativeAd(Context context0, MediationNativeListener mediationNativeListener0, Bundle bundle0, NativeMediationAdRequest nativeMediationAdRequest0, Bundle bundle1) {
        this.zzekq = (CustomEventNative)CustomEventAdapter.zzao(bundle0.getString("class_name"));
        if(this.zzekq == null) {
            mediationNativeListener0.onAdFailedToLoad(this, 0);
            return;
        }
        Bundle bundle2 = bundle1 == null ? null : bundle1.getBundle(bundle0.getString("class_name"));
        this.zzekq.requestNativeAd(context0, new zzb(this, mediationNativeListener0), bundle0.getString("parameter"), nativeMediationAdRequest0, bundle2);
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        this.zzekp.showInterstitial();
    }

    private final void zza(View view0) {
        this.zzml = view0;
    }

    private static Object zzao(String s) {
        try {
            return Class.forName(s).newInstance();
        }
        catch(Throwable throwable0) {
            zzazh.zzfa(("Could not instantiate custom event adapter: " + s + ". " + throwable0.getMessage()));
            return null;
        }
    }
}

