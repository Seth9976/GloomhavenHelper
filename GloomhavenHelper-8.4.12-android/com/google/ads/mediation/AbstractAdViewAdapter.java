package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzayx;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzbgl;
import com.google.android.gms.internal.ads.zztz;
import com.google.android.gms.internal.ads.zzxj;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, zza, MediationRewardedVideoAdAdapter, zzbgl {
    static final class com.google.ads.mediation.AbstractAdViewAdapter.zza extends NativeAppInstallAdMapper {
        private final NativeAppInstallAd zzlz;

        public com.google.ads.mediation.AbstractAdViewAdapter.zza(NativeAppInstallAd nativeAppInstallAd0) {
            this.zzlz = nativeAppInstallAd0;
            this.setHeadline(nativeAppInstallAd0.getHeadline().toString());
            this.setImages(nativeAppInstallAd0.getImages());
            this.setBody(nativeAppInstallAd0.getBody().toString());
            this.setIcon(nativeAppInstallAd0.getIcon());
            this.setCallToAction(nativeAppInstallAd0.getCallToAction().toString());
            if(nativeAppInstallAd0.getStarRating() != null) {
                this.setStarRating(((double)nativeAppInstallAd0.getStarRating()));
            }
            if(nativeAppInstallAd0.getStore() != null) {
                this.setStore(nativeAppInstallAd0.getStore().toString());
            }
            if(nativeAppInstallAd0.getPrice() != null) {
                this.setPrice(nativeAppInstallAd0.getPrice().toString());
            }
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(nativeAppInstallAd0.getVideoController());
        }

        @Override  // com.google.android.gms.ads.mediation.NativeAdMapper
        public final void trackView(View view0) {
            if(view0 instanceof NativeAdView) {
                ((NativeAdView)view0).setNativeAd(this.zzlz);
            }
            NativeAdViewHolder nativeAdViewHolder0 = (NativeAdViewHolder)NativeAdViewHolder.zzbkn.get(view0);
            if(nativeAdViewHolder0 != null) {
                nativeAdViewHolder0.setNativeAd(this.zzlz);
            }
        }
    }

    static final class zzb extends UnifiedNativeAdMapper {
        private final UnifiedNativeAd zzma;

        public zzb(UnifiedNativeAd unifiedNativeAd0) {
            this.zzma = unifiedNativeAd0;
            this.setHeadline(unifiedNativeAd0.getHeadline());
            this.setImages(unifiedNativeAd0.getImages());
            this.setBody(unifiedNativeAd0.getBody());
            this.setIcon(unifiedNativeAd0.getIcon());
            this.setCallToAction(unifiedNativeAd0.getCallToAction());
            this.setAdvertiser(unifiedNativeAd0.getAdvertiser());
            this.setStarRating(unifiedNativeAd0.getStarRating());
            this.setStore(unifiedNativeAd0.getStore());
            this.setPrice(unifiedNativeAd0.getPrice());
            this.zzn(unifiedNativeAd0.zzjt());
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(unifiedNativeAd0.getVideoController());
        }

        @Override  // com.google.android.gms.ads.mediation.UnifiedNativeAdMapper
        public final void trackViews(View view0, Map map0, Map map1) {
            if(view0 instanceof UnifiedNativeAdView) {
                ((UnifiedNativeAdView)view0).setNativeAd(this.zzma);
                return;
            }
            NativeAdViewHolder nativeAdViewHolder0 = (NativeAdViewHolder)NativeAdViewHolder.zzbkn.get(view0);
            if(nativeAdViewHolder0 != null) {
                nativeAdViewHolder0.setNativeAd(this.zzma);
            }
        }
    }

    static final class zzc extends NativeContentAdMapper {
        private final NativeContentAd zzmb;

        public zzc(NativeContentAd nativeContentAd0) {
            this.zzmb = nativeContentAd0;
            this.setHeadline(nativeContentAd0.getHeadline().toString());
            this.setImages(nativeContentAd0.getImages());
            this.setBody(nativeContentAd0.getBody().toString());
            if(nativeContentAd0.getLogo() != null) {
                this.setLogo(nativeContentAd0.getLogo());
            }
            this.setCallToAction(nativeContentAd0.getCallToAction().toString());
            this.setAdvertiser(nativeContentAd0.getAdvertiser().toString());
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(nativeContentAd0.getVideoController());
        }

        @Override  // com.google.android.gms.ads.mediation.NativeAdMapper
        public final void trackView(View view0) {
            if(view0 instanceof NativeAdView) {
                ((NativeAdView)view0).setNativeAd(this.zzmb);
            }
            NativeAdViewHolder nativeAdViewHolder0 = (NativeAdViewHolder)NativeAdViewHolder.zzbkn.get(view0);
            if(nativeAdViewHolder0 != null) {
                nativeAdViewHolder0.setNativeAd(this.zzmb);
            }
        }
    }

    @VisibleForTesting
    static final class zzd extends AdListener implements zztz {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzmc;
        @VisibleForTesting
        private final MediationInterstitialListener zzmd;

        public zzd(AbstractAdViewAdapter abstractAdViewAdapter0, MediationInterstitialListener mediationInterstitialListener0) {
            this.zzmc = abstractAdViewAdapter0;
            this.zzmd = mediationInterstitialListener0;
        }

        @Override  // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zztz
        public final void onAdClicked() {
            this.zzmd.onAdClicked(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzmd.onAdClosed(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int v) {
            this.zzmd.onAdFailedToLoad(this.zzmc, v);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzmd.onAdLeftApplication(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
            this.zzmd.onAdLoaded(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzmd.onAdOpened(this.zzmc);
        }
    }

    @VisibleForTesting
    static final class zze extends AdListener implements AppEventListener, zztz {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzmc;
        @VisibleForTesting
        private final MediationBannerListener zzme;

        public zze(AbstractAdViewAdapter abstractAdViewAdapter0, MediationBannerListener mediationBannerListener0) {
            this.zzmc = abstractAdViewAdapter0;
            this.zzme = mediationBannerListener0;
        }

        @Override  // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zztz
        public final void onAdClicked() {
            this.zzme.onAdClicked(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzme.onAdClosed(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int v) {
            this.zzme.onAdFailedToLoad(this.zzmc, v);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzme.onAdLeftApplication(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
            this.zzme.onAdLoaded(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzme.onAdOpened(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.doubleclick.AppEventListener
        public final void onAppEvent(String s, String s1) {
            this.zzme.zza(this.zzmc, s, s1);
        }
    }

    @VisibleForTesting
    static final class zzf extends AdListener implements OnAppInstallAdLoadedListener, OnContentAdLoadedListener, OnCustomClickListener, OnCustomTemplateAdLoadedListener, OnUnifiedNativeAdLoadedListener {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzmc;
        @VisibleForTesting
        private final MediationNativeListener zzmf;

        public zzf(AbstractAdViewAdapter abstractAdViewAdapter0, MediationNativeListener mediationNativeListener0) {
            this.zzmc = abstractAdViewAdapter0;
            this.zzmf = mediationNativeListener0;
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdClicked() {
            this.zzmf.onAdClicked(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdClosed() {
            this.zzmf.onAdClosed(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdFailedToLoad(int v) {
            this.zzmf.onAdFailedToLoad(this.zzmc, v);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdImpression() {
            this.zzmf.onAdImpression(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLeftApplication() {
            this.zzmf.onAdLeftApplication(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdLoaded() {
        }

        @Override  // com.google.android.gms.ads.AdListener
        public final void onAdOpened() {
            this.zzmf.onAdOpened(this.zzmc);
        }

        @Override  // com.google.android.gms.ads.formats.NativeAppInstallAd$OnAppInstallAdLoadedListener
        public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd0) {
            com.google.ads.mediation.AbstractAdViewAdapter.zza abstractAdViewAdapter$zza0 = new com.google.ads.mediation.AbstractAdViewAdapter.zza(nativeAppInstallAd0);
            this.zzmf.onAdLoaded(this.zzmc, abstractAdViewAdapter$zza0);
        }

        @Override  // com.google.android.gms.ads.formats.NativeContentAd$OnContentAdLoadedListener
        public final void onContentAdLoaded(NativeContentAd nativeContentAd0) {
            zzc abstractAdViewAdapter$zzc0 = new zzc(nativeContentAd0);
            this.zzmf.onAdLoaded(this.zzmc, abstractAdViewAdapter$zzc0);
        }

        @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd$OnCustomClickListener
        public final void onCustomClick(NativeCustomTemplateAd nativeCustomTemplateAd0, String s) {
            this.zzmf.zza(this.zzmc, nativeCustomTemplateAd0, s);
        }

        @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd$OnCustomTemplateAdLoadedListener
        public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd0) {
            this.zzmf.zza(this.zzmc, nativeCustomTemplateAd0);
        }

        @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd$OnUnifiedNativeAdLoadedListener
        public final void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd0) {
            zzb abstractAdViewAdapter$zzb0 = new zzb(unifiedNativeAd0);
            this.zzmf.onAdLoaded(this.zzmc, abstractAdViewAdapter$zzb0);
        }
    }

    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zzls;
    private InterstitialAd zzlt;
    private AdLoader zzlu;
    private Context zzlv;
    private InterstitialAd zzlw;
    private MediationRewardedVideoAdListener zzlx;
    @VisibleForTesting
    private final RewardedVideoAdListener zzly;

    public AbstractAdViewAdapter() {
        this.zzly = new com.google.ads.mediation.zza(this);
    }

    public String getAdUnitId(Bundle bundle0) {
        return bundle0.getString("pubid");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.zzls;
    }

    @Override  // com.google.android.gms.internal.ads.zzbgl
    public Bundle getInterstitialAdapterInfo() {
        return new com.google.android.gms.ads.mediation.MediationAdapter.zza().zzdf(1).zzacd();
    }

    @Override  // com.google.android.gms.ads.mediation.zza
    public zzxj getVideoController() {
        AdView adView0 = this.zzls;
        if(adView0 != null) {
            VideoController videoController0 = adView0.getVideoController();
            return videoController0 == null ? null : videoController0.zzdq();
        }
        return null;
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context0, MediationAdRequest mediationAdRequest0, String s, MediationRewardedVideoAdListener mediationRewardedVideoAdListener0, Bundle bundle0, Bundle bundle1) {
        this.zzlv = context0.getApplicationContext();
        this.zzlx = mediationRewardedVideoAdListener0;
        this.zzlx.onInitializationSucceeded(this);
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return this.zzlx != null;
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(MediationAdRequest mediationAdRequest0, Bundle bundle0, Bundle bundle1) {
        Context context0 = this.zzlv;
        if(context0 != null && this.zzlx != null) {
            this.zzlw = new InterstitialAd(context0);
            this.zzlw.zzd(true);
            this.zzlw.setAdUnitId(this.getAdUnitId(bundle0));
            this.zzlw.setRewardedVideoAdListener(this.zzly);
            this.zzlw.setAdMetadataListener(new com.google.ads.mediation.zzb(this));
            this.zzlw.loadAd(this.zza(this.zzlv, mediationAdRequest0, bundle1, bundle0));
            return;
        }
        zzazh.zzey("AdMobAdapter.loadAd called before initialize.");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        AdView adView0 = this.zzls;
        if(adView0 != null) {
            adView0.destroy();
            this.zzls = null;
        }
        if(this.zzlt != null) {
            this.zzlt = null;
        }
        if(this.zzlu != null) {
            this.zzlu = null;
        }
        if(this.zzlw != null) {
            this.zzlw = null;
        }
    }

    @Override  // com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener
    public void onImmersiveModeUpdated(boolean z) {
        InterstitialAd interstitialAd0 = this.zzlt;
        if(interstitialAd0 != null) {
            interstitialAd0.setImmersiveMode(z);
        }
        InterstitialAd interstitialAd1 = this.zzlw;
        if(interstitialAd1 != null) {
            interstitialAd1.setImmersiveMode(z);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
        AdView adView0 = this.zzls;
        if(adView0 != null) {
            adView0.pause();
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
        AdView adView0 = this.zzls;
        if(adView0 != null) {
            adView0.resume();
        }
    }

    @Override  // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context0, MediationBannerListener mediationBannerListener0, Bundle bundle0, AdSize adSize0, MediationAdRequest mediationAdRequest0, Bundle bundle1) {
        this.zzls = new AdView(context0);
        this.zzls.setAdSize(new AdSize(adSize0.getWidth(), adSize0.getHeight()));
        this.zzls.setAdUnitId(this.getAdUnitId(bundle0));
        this.zzls.setAdListener(new zze(this, mediationBannerListener0));
        this.zzls.loadAd(this.zza(context0, mediationAdRequest0, bundle1, bundle0));
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context0, MediationInterstitialListener mediationInterstitialListener0, Bundle bundle0, MediationAdRequest mediationAdRequest0, Bundle bundle1) {
        this.zzlt = new InterstitialAd(context0);
        this.zzlt.setAdUnitId(this.getAdUnitId(bundle0));
        this.zzlt.setAdListener(new zzd(this, mediationInterstitialListener0));
        this.zzlt.loadAd(this.zza(context0, mediationAdRequest0, bundle1, bundle0));
    }

    @Override  // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(Context context0, MediationNativeListener mediationNativeListener0, Bundle bundle0, NativeMediationAdRequest nativeMediationAdRequest0, Bundle bundle1) {
        zzf abstractAdViewAdapter$zzf0 = new zzf(this, mediationNativeListener0);
        Builder adLoader$Builder0 = new Builder(context0, bundle0.getString("pubid")).withAdListener(abstractAdViewAdapter$zzf0);
        NativeAdOptions nativeAdOptions0 = nativeMediationAdRequest0.getNativeAdOptions();
        if(nativeAdOptions0 != null) {
            adLoader$Builder0.withNativeAdOptions(nativeAdOptions0);
        }
        if(nativeMediationAdRequest0.isUnifiedNativeAdRequested()) {
            adLoader$Builder0.forUnifiedNativeAd(abstractAdViewAdapter$zzf0);
        }
        if(nativeMediationAdRequest0.isAppInstallAdRequested()) {
            adLoader$Builder0.forAppInstallAd(abstractAdViewAdapter$zzf0);
        }
        if(nativeMediationAdRequest0.isContentAdRequested()) {
            adLoader$Builder0.forContentAd(abstractAdViewAdapter$zzf0);
        }
        if(nativeMediationAdRequest0.zzte()) {
            for(Object object0: nativeMediationAdRequest0.zztf().keySet()) {
                adLoader$Builder0.forCustomTemplateAd(((String)object0), abstractAdViewAdapter$zzf0, (((Boolean)nativeMediationAdRequest0.zztf().get(((String)object0))).booleanValue() ? abstractAdViewAdapter$zzf0 : null));
            }
        }
        this.zzlu = adLoader$Builder0.build();
        this.zzlu.loadAd(this.zza(context0, nativeMediationAdRequest0, bundle1, bundle0));
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        this.zzlt.show();
    }

    @Override  // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        this.zzlw.show();
    }

    private final AdRequest zza(Context context0, MediationAdRequest mediationAdRequest0, Bundle bundle0, Bundle bundle1) {
        com.google.android.gms.ads.AdRequest.Builder adRequest$Builder0 = new com.google.android.gms.ads.AdRequest.Builder();
        Date date0 = mediationAdRequest0.getBirthday();
        if(date0 != null) {
            adRequest$Builder0.setBirthday(date0);
        }
        int v = mediationAdRequest0.getGender();
        if(v != 0) {
            adRequest$Builder0.setGender(v);
        }
        Set set0 = mediationAdRequest0.getKeywords();
        if(set0 != null) {
            for(Object object0: set0) {
                adRequest$Builder0.addKeyword(((String)object0));
            }
        }
        Location location0 = mediationAdRequest0.getLocation();
        if(location0 != null) {
            adRequest$Builder0.setLocation(location0);
        }
        if(mediationAdRequest0.isTesting()) {
            adRequest$Builder0.addTestDevice(zzayx.zzbl(context0));
        }
        boolean z = true;
        if(mediationAdRequest0.taggedForChildDirectedTreatment() != -1) {
            if(mediationAdRequest0.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            adRequest$Builder0.tagForChildDirectedTreatment(z);
        }
        adRequest$Builder0.setIsDesignedForFamilies(mediationAdRequest0.isDesignedForFamilies());
        Bundle bundle2 = this.zza(bundle0, bundle1);
        adRequest$Builder0.addNetworkExtrasBundle(AdMobAdapter.class, bundle2);
        return adRequest$Builder0.build();
    }

    static InterstitialAd zza(AbstractAdViewAdapter abstractAdViewAdapter0, InterstitialAd interstitialAd0) {
        abstractAdViewAdapter0.zzlw = null;
        return null;
    }

    static MediationRewardedVideoAdListener zza(AbstractAdViewAdapter abstractAdViewAdapter0) {
        return abstractAdViewAdapter0.zzlx;
    }

    protected abstract Bundle zza(Bundle arg1, Bundle arg2);

    static InterstitialAd zzb(AbstractAdViewAdapter abstractAdViewAdapter0) {
        return abstractAdViewAdapter0.zzlw;
    }
}

