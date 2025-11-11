package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.internal.ads.zzazh;

@Deprecated
public final class NativeAppInstallAdView extends NativeAdView {
    public NativeAppInstallAdView(Context context0) {
        super(context0);
    }

    public NativeAppInstallAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public NativeAppInstallAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    public NativeAppInstallAdView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    public final View getBodyView() {
        return super.zzbq("2004");
    }

    public final View getCallToActionView() {
        return super.zzbq("2002");
    }

    public final View getHeadlineView() {
        return super.zzbq("2001");
    }

    public final View getIconView() {
        return super.zzbq("2003");
    }

    public final View getImageView() {
        return super.zzbq("2007");
    }

    public final MediaView getMediaView() {
        View view0 = super.zzbq("2011");
        if(view0 instanceof MediaView) {
            return (MediaView)view0;
        }
        if(view0 != null) {
            zzazh.zzeb("View is not an instance of MediaView");
        }
        return null;
    }

    public final View getPriceView() {
        return super.zzbq("2006");
    }

    public final View getStarRatingView() {
        return super.zzbq("2008");
    }

    public final View getStoreView() {
        return super.zzbq("2005");
    }

    public final void setBodyView(View view0) {
        super.zza("2004", view0);
    }

    public final void setCallToActionView(View view0) {
        super.zza("2002", view0);
    }

    public final void setHeadlineView(View view0) {
        super.zza("2001", view0);
    }

    public final void setIconView(View view0) {
        super.zza("2003", view0);
    }

    public final void setImageView(View view0) {
        super.zza("2007", view0);
    }

    public final void setMediaView(MediaView mediaView0) {
        super.zza("2011", mediaView0);
    }

    public final void setPriceView(View view0) {
        super.zza("2006", view0);
    }

    public final void setStarRatingView(View view0) {
        super.zza("2008", view0);
    }

    public final void setStoreView(View view0) {
        super.zza("2005", view0);
    }
}

