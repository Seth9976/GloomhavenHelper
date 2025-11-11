package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbdv;

@VisibleForTesting
public final class zzk {
    public final int index;
    public final ViewGroup parent;
    public final ViewGroup.LayoutParams zzdip;
    public final Context zzur;

    public zzk(zzbdv zzbdv0) throws zzi {
        this.zzdip = zzbdv0.getLayoutParams();
        ViewParent viewParent0 = zzbdv0.getParent();
        this.zzur = zzbdv0.zzaaa();
        if(viewParent0 == null || !(viewParent0 instanceof ViewGroup)) {
            throw new zzi("Could not get the parent of the WebView for an overlay.");
        }
        this.parent = (ViewGroup)viewParent0;
        View view0 = zzbdv0.getView();
        this.index = this.parent.indexOfChild(view0);
        View view1 = zzbdv0.getView();
        this.parent.removeView(view1);
        zzbdv0.zzax(true);
    }
}

