package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.common.internal.Preconditions;

public final class zzaml implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzalq zzdex;
    private NativeAdMapper zzdey;
    private UnifiedNativeAdMapper zzdez;
    private NativeCustomTemplateAd zzdfa;

    public zzaml(zzalq zzalq0) {
        this.zzdex = zzalq0;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClicked(MediationBannerAdapter mediationBannerAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClicked.");
        try {
            this.zzdex.onAdClicked();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClicked.");
        try {
            this.zzdex.onAdClicked();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClicked(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper0 = this.zzdey;
        UnifiedNativeAdMapper unifiedNativeAdMapper0 = this.zzdez;
        if(this.zzdfa == null) {
            if(nativeAdMapper0 == null && unifiedNativeAdMapper0 == null) {
                zzazh.zze("#007 Could not call remote method.", null);
                return;
            }
            if(unifiedNativeAdMapper0 != null && !unifiedNativeAdMapper0.getOverrideClickHandling()) {
                zzazh.zzeb("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
            if(nativeAdMapper0 != null && !nativeAdMapper0.getOverrideClickHandling()) {
                zzazh.zzeb("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzazh.zzeb("Adapter called onAdClicked.");
        try {
            this.zzdex.onAdClicked();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClosed(MediationBannerAdapter mediationBannerAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClosed.");
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClosed.");
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClosed(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdClosed.");
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter0, int v) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb(("Adapter called onAdFailedToLoad with error. " + v));
        try {
            this.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter0, int v) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb(("Adapter called onAdFailedToLoad with error " + v + "."));
        try {
            this.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter0, int v) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb(("Adapter called onAdFailedToLoad with error " + v + "."));
        try {
            this.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdImpression(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper0 = this.zzdey;
        UnifiedNativeAdMapper unifiedNativeAdMapper0 = this.zzdez;
        if(this.zzdfa == null) {
            if(nativeAdMapper0 == null && unifiedNativeAdMapper0 == null) {
                zzazh.zze("#007 Could not call remote method.", null);
                return;
            }
            if(unifiedNativeAdMapper0 != null && !unifiedNativeAdMapper0.getOverrideImpressionRecording()) {
                zzazh.zzeb("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
            if(nativeAdMapper0 != null && !nativeAdMapper0.getOverrideImpressionRecording()) {
                zzazh.zzeb("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzazh.zzeb("Adapter called onAdImpression.");
        try {
            this.zzdex.onAdImpression();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLeftApplication.");
        try {
            this.zzdex.onAdLeftApplication();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLeftApplication.");
        try {
            this.zzdex.onAdLeftApplication();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLeftApplication.");
        try {
            this.zzdex.onAdLeftApplication();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLoaded(MediationBannerAdapter mediationBannerAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLoaded.");
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLoaded.");
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter0, NativeAdMapper nativeAdMapper0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLoaded.");
        this.zzdey = nativeAdMapper0;
        this.zzdez = null;
        zzaml.zza(mediationNativeAdapter0, null, this.zzdey);
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter0, UnifiedNativeAdMapper unifiedNativeAdMapper0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdLoaded.");
        this.zzdez = unifiedNativeAdMapper0;
        this.zzdey = null;
        zzaml.zza(mediationNativeAdapter0, this.zzdez, null);
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdOpened(MediationBannerAdapter mediationBannerAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdOpened.");
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdOpened.");
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdOpened(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAdOpened.");
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onVideoEnd(MediationNativeAdapter mediationNativeAdapter0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onVideoEnd.");
        try {
            this.zzdex.onVideoEnd();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    private static void zza(MediationNativeAdapter mediationNativeAdapter0, @Nullable UnifiedNativeAdMapper unifiedNativeAdMapper0, @Nullable NativeAdMapper nativeAdMapper0) {
        if(mediationNativeAdapter0 instanceof AdMobAdapter) {
            return;
        }
        VideoController videoController0 = new VideoController();
        videoController0.zza(new zzame());
        if(unifiedNativeAdMapper0 != null && unifiedNativeAdMapper0.hasVideoContent()) {
            unifiedNativeAdMapper0.zza(videoController0);
        }
        if(nativeAdMapper0 != null && nativeAdMapper0.hasVideoContent()) {
            nativeAdMapper0.zza(videoController0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void zza(MediationBannerAdapter mediationBannerAdapter0, String s, String s1) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzazh.zzeb("Adapter called onAppEvent.");
        try {
            this.zzdex.onAppEvent(s, s1);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter0, NativeCustomTemplateAd nativeCustomTemplateAd0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        String s = nativeCustomTemplateAd0.getCustomTemplateId();
        zzazh.zzeb((s.length() == 0 ? new String("Adapter called onAdLoaded with template id ") : "Adapter called onAdLoaded with template id " + s));
        this.zzdfa = nativeCustomTemplateAd0;
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter0, NativeCustomTemplateAd nativeCustomTemplateAd0, String s) {
        if(nativeCustomTemplateAd0 instanceof zzado) {
            try {
                zzadn zzadn0 = ((zzado)nativeCustomTemplateAd0).zzrs();
                this.zzdex.zza(zzadn0, s);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
            return;
        }
        zzazh.zzfa("Unexpected native custom template ad type.");
    }

    public final NativeAdMapper zztb() {
        return this.zzdey;
    }

    public final UnifiedNativeAdMapper zztc() {
        return this.zzdez;
    }

    public final NativeCustomTemplateAd zztd() {
        return this.zzdfa;
    }
}

