package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zztz;
import com.google.android.gms.internal.ads.zzxt;

class BaseAdView extends ViewGroup {
    protected final zzxt zzabr;

    public BaseAdView(Context context0, int v) {
        super(context0);
        this.zzabr = new zzxt(this, v);
    }

    public BaseAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0);
        this.zzabr = new zzxt(this, attributeSet0, false, v);
    }

    public BaseAdView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v);
        this.zzabr = new zzxt(this, attributeSet0, false, v1);
    }

    public void destroy() {
        this.zzabr.destroy();
    }

    public AdListener getAdListener() {
        return this.zzabr.getAdListener();
    }

    public AdSize getAdSize() {
        return this.zzabr.getAdSize();
    }

    public String getAdUnitId() {
        return this.zzabr.getAdUnitId();
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        return this.zzabr.getMediationAdapterClassName();
    }

    @Nullable
    public ResponseInfo getResponseInfo() {
        return this.zzabr.getResponseInfo();
    }

    public boolean isLoading() {
        return this.zzabr.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest0) {
        this.zzabr.zza(adRequest0.zzdl());
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
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
    protected void onMeasure(int v, int v1) {
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

    public void pause() {
        this.zzabr.pause();
    }

    public void resume() {
        this.zzabr.resume();
    }

    public void setAdListener(AdListener adListener0) {
        this.zzabr.setAdListener(adListener0);
        if(adListener0 == null) {
            this.zzabr.zza(null);
            this.zzabr.setAppEventListener(null);
            return;
        }
        if(adListener0 instanceof zztz) {
            this.zzabr.zza(((zztz)adListener0));
        }
        if(adListener0 instanceof AppEventListener) {
            this.zzabr.setAppEventListener(((AppEventListener)adListener0));
        }
    }

    public void setAdSize(AdSize adSize0) {
        this.zzabr.setAdSizes(new AdSize[]{adSize0});
    }

    public void setAdUnitId(String s) {
        this.zzabr.setAdUnitId(s);
    }

    public void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        this.zzabr.setOnPaidEventListener(onPaidEventListener0);
    }
}

