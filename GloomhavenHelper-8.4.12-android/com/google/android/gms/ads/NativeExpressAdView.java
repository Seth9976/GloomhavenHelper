package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

public final class NativeExpressAdView extends BaseAdView {
    public NativeExpressAdView(Context context0) {
        super(context0, 1);
    }

    public NativeExpressAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0, 1);
    }

    public NativeExpressAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v, 1);
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void destroy() {
        super.destroy();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final AdListener getAdListener() {
        return super.getAdListener();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final AdSize getAdSize() {
        return super.getAdSize();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final String getAdUnitId() {
        return super.getAdUnitId();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    @Deprecated
    public final String getMediationAdapterClassName() {
        return super.getMediationAdapterClassName();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    @Nullable
    public final ResponseInfo getResponseInfo() {
        return super.getResponseInfo();
    }

    public final VideoController getVideoController() {
        return this.zzabr.getVideoController();
    }

    public final VideoOptions getVideoOptions() {
        return this.zzabr.getVideoOptions();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final boolean isLoading() {
        return super.isLoading();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest0) {
        super.loadAd(adRequest0);
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void pause() {
        super.pause();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void resume() {
        super.resume();
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void setAdListener(AdListener adListener0) {
        super.setAdListener(adListener0);
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void setAdSize(AdSize adSize0) {
        super.setAdSize(adSize0);
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void setAdUnitId(String s) {
        super.setAdUnitId(s);
    }

    @Override  // com.google.android.gms.ads.BaseAdView
    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        super.setOnPaidEventListener(onPaidEventListener0);
    }

    public final void setVideoOptions(VideoOptions videoOptions0) {
        this.zzabr.setVideoOptions(videoOptions0);
    }
}

