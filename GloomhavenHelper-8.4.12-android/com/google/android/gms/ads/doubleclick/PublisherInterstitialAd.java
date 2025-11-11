package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzxv;

public final class PublisherInterstitialAd {
    private final zzxv zzabs;

    public PublisherInterstitialAd(Context context0) {
        this.zzabs = new zzxv(context0, this);
        Preconditions.checkNotNull(context0, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzabs.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzabs.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzabs.getAppEventListener();
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzabs.getMediationAdapterClassName();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzabs.getOnCustomRenderedAdLoadedListener();
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        return this.zzabs.getResponseInfo();
    }

    public final boolean isLoaded() {
        return this.zzabs.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzabs.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest0) {
        this.zzabs.zza(publisherAdRequest0.zzdl());
    }

    public final void setAdListener(AdListener adListener0) {
        this.zzabs.setAdListener(adListener0);
    }

    public final void setAdUnitId(String s) {
        this.zzabs.setAdUnitId(s);
    }

    public final void setAppEventListener(AppEventListener appEventListener0) {
        this.zzabs.setAppEventListener(appEventListener0);
    }

    @KeepForSdk
    @Deprecated
    public final void setCorrelator(Correlator correlator0) {
    }

    public final void setImmersiveMode(boolean z) {
        this.zzabs.setImmersiveMode(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener0) {
        this.zzabs.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener0);
    }

    public final void show() {
        this.zzabs.show();
    }
}

