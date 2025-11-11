package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzvx;
import com.google.android.gms.internal.ads.zzxt;

public final class PublisherAdView extends ViewGroup {
    private final zzxt zzaci;

    public PublisherAdView(Context context0) {
        super(context0);
        this.zzaci = new zzxt(this);
    }

    public PublisherAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.zzaci = new zzxt(this, attributeSet0, true);
        Preconditions.checkNotNull(context0, "Context cannot be null");
    }

    public PublisherAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.zzaci = new zzxt(this, attributeSet0, true);
    }

    public final void destroy() {
        this.zzaci.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzaci.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzaci.getAdSize();
    }

    public final AdSize[] getAdSizes() {
        return this.zzaci.getAdSizes();
    }

    public final String getAdUnitId() {
        return this.zzaci.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzaci.getAppEventListener();
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzaci.getMediationAdapterClassName();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzaci.getOnCustomRenderedAdLoadedListener();
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        return this.zzaci.getResponseInfo();
    }

    public final VideoController getVideoController() {
        return this.zzaci.getVideoController();
    }

    public final VideoOptions getVideoOptions() {
        return this.zzaci.getVideoOptions();
    }

    public final boolean isLoading() {
        return this.zzaci.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest0) {
        this.zzaci.zza(publisherAdRequest0.zzdl());
    }

    @Override  // android.view.ViewGroup
    protected final void onLayout(boolean z, int v, int v1, int v2, int v3) {
        View view0 = this.getChildAt(0);
        if(view0 != null && view0.getVisibility() != 8) {
            int v4 = view0.getMeasuredWidth();
            int v5 = view0.getMeasuredHeight();
            int v6 = (v2 - v - v4) / 2;
            int v7 = (v3 - v1 - v5) / 2;
            view0.layout(v6, v7, v4 + v6, v5 + v7);
        }
    }

    @Override  // android.view.View
    protected final void onMeasure(int v, int v1) {
        int v3;
        AdSize adSize0;
        int v2 = 0;
        View view0 = this.getChildAt(0);
        if(view0 == null || view0.getVisibility() == 8) {
            try {
                adSize0 = this.getAdSize();
            }
            catch(NullPointerException nullPointerException0) {
                adSize0 = null;
                zzazh.zzc("Unable to retrieve ad size.", nullPointerException0);
            }
            if(adSize0 == null) {
                v3 = 0;
            }
            else {
                Context context0 = this.getContext();
                int v4 = adSize0.getWidthInPixels(context0);
                v3 = adSize0.getHeightInPixels(context0);
                v2 = v4;
            }
        }
        else {
            this.measureChild(view0, v, v1);
            v2 = view0.getMeasuredWidth();
            v3 = view0.getMeasuredHeight();
        }
        int v5 = Math.max(v2, this.getSuggestedMinimumWidth());
        int v6 = Math.max(v3, this.getSuggestedMinimumHeight());
        this.setMeasuredDimension(View.resolveSize(v5, v), View.resolveSize(v6, v1));
    }

    public final void pause() {
        this.zzaci.pause();
    }

    public final void recordManualImpression() {
        this.zzaci.recordManualImpression();
    }

    public final void resume() {
        this.zzaci.resume();
    }

    public final void setAdListener(AdListener adListener0) {
        this.zzaci.setAdListener(adListener0);
    }

    public final void setAdSizes(AdSize[] arr_adSize) {
        if(arr_adSize == null || arr_adSize.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzaci.zza(arr_adSize);
    }

    public final void setAdUnitId(String s) {
        this.zzaci.setAdUnitId(s);
    }

    public final void setAppEventListener(AppEventListener appEventListener0) {
        this.zzaci.setAppEventListener(appEventListener0);
    }

    @KeepForSdk
    @Deprecated
    public final void setCorrelator(Correlator correlator0) {
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzaci.setManualImpressionsEnabled(z);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener0) {
        this.zzaci.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener0);
    }

    public final void setVideoOptions(VideoOptions videoOptions0) {
        this.zzaci.setVideoOptions(videoOptions0);
    }

    public final boolean zza(zzvx zzvx0) {
        return this.zzaci.zza(zzvx0);
    }
}

