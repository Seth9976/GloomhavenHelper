package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.internal.ads.zzazh;

@Deprecated
public final class NativeContentAdView extends NativeAdView {
    public NativeContentAdView(Context context0) {
        super(context0);
    }

    public NativeContentAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public NativeContentAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    public NativeContentAdView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    public final View getAdvertiserView() {
        return super.zzbq("1004");
    }

    public final View getBodyView() {
        return super.zzbq("1002");
    }

    public final View getCallToActionView() {
        return super.zzbq("1003");
    }

    public final View getHeadlineView() {
        return super.zzbq("1001");
    }

    public final View getImageView() {
        return super.zzbq("1005");
    }

    public final View getLogoView() {
        return super.zzbq("1006");
    }

    public final MediaView getMediaView() {
        View view0 = super.zzbq("1009");
        if(view0 instanceof MediaView) {
            return (MediaView)view0;
        }
        if(view0 != null) {
            zzazh.zzeb("View is not an instance of MediaView");
        }
        return null;
    }

    public final void setAdvertiserView(View view0) {
        super.zza("1004", view0);
    }

    public final void setBodyView(View view0) {
        super.zza("1002", view0);
    }

    public final void setCallToActionView(View view0) {
        super.zza("1003", view0);
    }

    public final void setHeadlineView(View view0) {
        super.zza("1001", view0);
    }

    public final void setImageView(View view0) {
        super.zza("1005", view0);
    }

    public final void setLogoView(View view0) {
        super.zza("1006", view0);
    }

    public final void setMediaView(MediaView mediaView0) {
        super.zza("1009", mediaView0);
    }
}

