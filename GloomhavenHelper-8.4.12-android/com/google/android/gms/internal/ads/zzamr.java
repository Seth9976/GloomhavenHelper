package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

public final class zzamr implements MediationBannerListener, MediationInterstitialListener {
    private final zzalq zzdex;

    public zzamr(zzalq zzalq0) {
        this.zzdex = zzalq0;
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onClick(MediationBannerAdapter mediationBannerAdapter0) {
        zzazh.zzeb("Adapter called onClick.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamq zzamq0 = new zzamq(this);
            zzayx.zzyy.post(zzamq0);
            return;
        }
        try {
            this.zzdex.onAdClicked();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onDismissScreen(MediationBannerAdapter mediationBannerAdapter0) {
        zzazh.zzeb("Adapter called onDismissScreen.");
        if(!zzayx.zzxj()) {
            zzazh.zzfa("#008 Must be called on the main UI thread.");
            zzamv zzamv0 = new zzamv(this);
            zzayx.zzyy.post(zzamv0);
            return;
        }
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationInterstitialListener
    public final void onDismissScreen(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        zzazh.zzeb("Adapter called onDismissScreen.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamy zzamy0 = new zzamy(this);
            zzayx.zzyy.post(zzamy0);
            return;
        }
        try {
            this.zzdex.onAdClosed();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onFailedToReceiveAd(MediationBannerAdapter mediationBannerAdapter0, ErrorCode adRequest$ErrorCode0) {
        zzazh.zzeb(("Adapter called onFailedToReceiveAd with error. " + adRequest$ErrorCode0));
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamu zzamu0 = new zzamu(this, adRequest$ErrorCode0);
            zzayx.zzyy.post(zzamu0);
            return;
        }
        try {
            int v = zzand.zza(adRequest$ErrorCode0);
            this.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationInterstitialListener
    public final void onFailedToReceiveAd(MediationInterstitialAdapter mediationInterstitialAdapter0, ErrorCode adRequest$ErrorCode0) {
        zzazh.zzeb(("Adapter called onFailedToReceiveAd with error " + adRequest$ErrorCode0 + "."));
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzanb zzanb0 = new zzanb(this, adRequest$ErrorCode0);
            zzayx.zzyy.post(zzanb0);
            return;
        }
        try {
            int v = zzand.zza(adRequest$ErrorCode0);
            this.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onLeaveApplication(MediationBannerAdapter mediationBannerAdapter0) {
        zzazh.zzeb("Adapter called onLeaveApplication.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamx zzamx0 = new zzamx(this);
            zzayx.zzyy.post(zzamx0);
            return;
        }
        try {
            this.zzdex.onAdLeftApplication();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationInterstitialListener
    public final void onLeaveApplication(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        zzazh.zzeb("Adapter called onLeaveApplication.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzana zzana0 = new zzana(this);
            zzayx.zzyy.post(zzana0);
            return;
        }
        try {
            this.zzdex.onAdLeftApplication();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onPresentScreen(MediationBannerAdapter mediationBannerAdapter0) {
        zzazh.zzeb("Adapter called onPresentScreen.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamw zzamw0 = new zzamw(this);
            zzayx.zzyy.post(zzamw0);
            return;
        }
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationInterstitialListener
    public final void onPresentScreen(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        zzazh.zzeb("Adapter called onPresentScreen.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamt zzamt0 = new zzamt(this);
            zzayx.zzyy.post(zzamt0);
            return;
        }
        try {
            this.zzdex.onAdOpened();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationBannerListener
    public final void onReceivedAd(MediationBannerAdapter mediationBannerAdapter0) {
        zzazh.zzeb("Adapter called onReceivedAd.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzamz zzamz0 = new zzamz(this);
            zzayx.zzyy.post(zzamz0);
            return;
        }
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.ads.mediation.MediationInterstitialListener
    public final void onReceivedAd(MediationInterstitialAdapter mediationInterstitialAdapter0) {
        zzazh.zzeb("Adapter called onReceivedAd.");
        if(!zzayx.zzxj()) {
            zzazh.zze("#008 Must be called on the main UI thread.", null);
            zzams zzams0 = new zzams(this);
            zzayx.zzyy.post(zzams0);
            return;
        }
        try {
            this.zzdex.onAdLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

