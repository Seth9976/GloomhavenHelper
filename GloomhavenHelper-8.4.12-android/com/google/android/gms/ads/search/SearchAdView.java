package com.google.android.gms.ads.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzxr;
import com.google.android.gms.internal.ads.zzxt;

public final class SearchAdView extends ViewGroup {
    private final zzxt zzaci;

    public SearchAdView(Context context0) {
        super(context0);
        this.zzaci = new zzxt(this);
    }

    public SearchAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.zzaci = new zzxt(this, attributeSet0, false);
    }

    public SearchAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.zzaci = new zzxt(this, attributeSet0, false);
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

    public final String getAdUnitId() {
        return this.zzaci.getAdUnitId();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest0) {
        AdSize adSize0 = this.getAdSize();
        if(!AdSize.SEARCH.equals(adSize0)) {
            throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
        }
        zzxr zzxr0 = dynamicHeightSearchAdRequest0.zzdl();
        this.zzaci.zza(zzxr0);
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(SearchAdRequest searchAdRequest0) {
        this.zzaci.zza(searchAdRequest0.zzdl());
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

    public final void resume() {
        this.zzaci.resume();
    }

    public final void setAdListener(AdListener adListener0) {
        this.zzaci.setAdListener(adListener0);
    }

    public final void setAdSize(AdSize adSize0) {
        this.zzaci.setAdSizes(new AdSize[]{adSize0});
    }

    public final void setAdUnitId(String s) {
        this.zzaci.setAdUnitId(s);
    }
}

