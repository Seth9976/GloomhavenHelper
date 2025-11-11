package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzazh;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
    @VisibleForTesting
    final class zza implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzmo;
        private final MediationInterstitialListener zzmp;
        private final CustomEventAdapter zzmq;

        public zza(CustomEventAdapter customEventAdapter1, MediationInterstitialListener mediationInterstitialListener0) {
            this.zzmo = customEventAdapter1;
            this.zzmp = mediationInterstitialListener0;
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onDismissScreen() {
            zzazh.zzeb("Custom event adapter called onDismissScreen.");
            this.zzmp.onDismissScreen(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onFailedToReceiveAd() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmp.onFailedToReceiveAd(this.zzmo, ErrorCode.NO_FILL);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onLeaveApplication() {
            zzazh.zzeb("Custom event adapter called onLeaveApplication.");
            this.zzmp.onLeaveApplication(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onPresentScreen() {
            zzazh.zzeb("Custom event adapter called onPresentScreen.");
            this.zzmp.onPresentScreen(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventInterstitialListener
        public final void onReceivedAd() {
            zzazh.zzeb("Custom event adapter called onReceivedAd.");
            this.zzmp.onReceivedAd(CustomEventAdapter.this);
        }
    }

    @VisibleForTesting
    static final class zzb implements CustomEventBannerListener {
        private final CustomEventAdapter zzmo;
        private final MediationBannerListener zzmr;

        public zzb(CustomEventAdapter customEventAdapter0, MediationBannerListener mediationBannerListener0) {
            this.zzmo = customEventAdapter0;
            this.zzmr = mediationBannerListener0;
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventBannerListener
        public final void onClick() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmr.onClick(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onDismissScreen() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmr.onDismissScreen(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onFailedToReceiveAd() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmr.onFailedToReceiveAd(this.zzmo, ErrorCode.NO_FILL);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onLeaveApplication() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmr.onLeaveApplication(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventListener
        public final void onPresentScreen() {
            zzazh.zzeb("Custom event adapter called onFailedToReceiveAd.");
            this.zzmr.onPresentScreen(this.zzmo);
        }

        @Override  // com.google.ads.mediation.customevent.CustomEventBannerListener
        public final void onReceivedAd(View view0) {
            zzazh.zzeb("Custom event adapter called onReceivedAd.");
            this.zzmo.zza(view0);
            this.zzmr.onReceivedAd(this.zzmo);
        }
    }

    private View zzml;
    @VisibleForTesting
    private CustomEventBanner zzmm;
    @VisibleForTesting
    private CustomEventInterstitial zzmn;

    @Override  // com.google.ads.mediation.MediationAdapter
    public final void destroy() {
        CustomEventBanner customEventBanner0 = this.zzmm;
        if(customEventBanner0 != null) {
            customEventBanner0.destroy();
        }
        CustomEventInterstitial customEventInterstitial0 = this.zzmn;
        if(customEventInterstitial0 != null) {
            customEventInterstitial0.destroy();
        }
    }

    @Override  // com.google.ads.mediation.MediationAdapter
    public final Class getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    @Override  // com.google.ads.mediation.MediationBannerAdapter
    public final View getBannerView() {
        return this.zzml;
    }

    @Override  // com.google.ads.mediation.MediationAdapter
    public final Class getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    @Override  // com.google.ads.mediation.MediationBannerAdapter
    public final void requestBannerAd(MediationBannerListener mediationBannerListener0, Activity activity0, MediationServerParameters mediationServerParameters0, AdSize adSize0, MediationAdRequest mediationAdRequest0, NetworkExtras networkExtras0) {
        this.requestBannerAd(mediationBannerListener0, activity0, ((CustomEventServerParameters)mediationServerParameters0), adSize0, mediationAdRequest0, ((CustomEventExtras)networkExtras0));
    }

    public final void requestBannerAd(MediationBannerListener mediationBannerListener0, Activity activity0, CustomEventServerParameters customEventServerParameters0, AdSize adSize0, MediationAdRequest mediationAdRequest0, CustomEventExtras customEventExtras0) {
        this.zzmm = (CustomEventBanner)CustomEventAdapter.zzao(customEventServerParameters0.className);
        if(this.zzmm == null) {
            mediationBannerListener0.onFailedToReceiveAd(this, ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object object0 = customEventExtras0 == null ? null : customEventExtras0.getExtra(customEventServerParameters0.label);
        this.zzmm.requestBannerAd(new zzb(this, mediationBannerListener0), activity0, customEventServerParameters0.label, customEventServerParameters0.parameter, adSize0, mediationAdRequest0, object0);
    }

    @Override  // com.google.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener0, Activity activity0, MediationServerParameters mediationServerParameters0, MediationAdRequest mediationAdRequest0, NetworkExtras networkExtras0) {
        this.requestInterstitialAd(mediationInterstitialListener0, activity0, ((CustomEventServerParameters)mediationServerParameters0), mediationAdRequest0, ((CustomEventExtras)networkExtras0));
    }

    public final void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener0, Activity activity0, CustomEventServerParameters customEventServerParameters0, MediationAdRequest mediationAdRequest0, CustomEventExtras customEventExtras0) {
        this.zzmn = (CustomEventInterstitial)CustomEventAdapter.zzao(customEventServerParameters0.className);
        if(this.zzmn == null) {
            mediationInterstitialListener0.onFailedToReceiveAd(this, ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object object0 = customEventExtras0 == null ? null : customEventExtras0.getExtra(customEventServerParameters0.label);
        this.zzmn.requestInterstitialAd(new zza(this, this, mediationInterstitialListener0), activity0, customEventServerParameters0.label, customEventServerParameters0.parameter, mediationAdRequest0, object0);
    }

    @Override  // com.google.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        this.zzmn.showInterstitial();
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

