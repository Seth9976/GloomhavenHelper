package com.google.android.gms.ads.appopen;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzrd;
import com.google.android.gms.internal.ads.zzuk;
import com.google.android.gms.internal.ads.zzvx;

public final class AppOpenAdView extends ViewGroup {
    private AppOpenAd zzacg;
    private AppOpenAdPresentationCallback zzach;

    public AppOpenAdView(Context context0) {
        super(context0);
        Preconditions.checkNotNull(context0, "Context cannot be null");
    }

    public AppOpenAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public AppOpenAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    private final AdSize getAdSize() {
        zzvx zzvx0 = this.zzacg.zzdr();
        if(zzvx0 != null) {
            try {
                zzuk zzuk0 = zzvx0.zzke();
                if(zzuk0 != null) {
                    return zzuk0.zzot();
                }
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        return null;
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

    public final void setAppOpenAd(AppOpenAd appOpenAd0) {
        try {
            zzvx zzvx0 = appOpenAd0.zzdr();
            if(zzvx0 == null) {
                return;
            }
            IObjectWrapper iObjectWrapper0 = zzvx0.zzkc();
            if(iObjectWrapper0 == null) {
                return;
            }
            View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
            if(view0.getParent() == null) {
                this.removeAllViews();
                this.addView(view0);
                this.zzacg = appOpenAd0;
                this.zzds();
                return;
            }
            zzazh.zzey("Trying to set AppOpenAd which is already in use.");
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setAppOpenAdPresentationCallback(AppOpenAdPresentationCallback appOpenAdPresentationCallback0) {
        this.zzach = appOpenAdPresentationCallback0;
        this.zzds();
    }

    private final void zzds() {
        AppOpenAd appOpenAd0 = this.zzacg;
        if(appOpenAd0 != null) {
            AppOpenAdPresentationCallback appOpenAdPresentationCallback0 = this.zzach;
            if(appOpenAdPresentationCallback0 != null) {
                appOpenAd0.zza(new zzrd(appOpenAdPresentationCallback0));
            }
        }
    }
}

