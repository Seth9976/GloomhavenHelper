package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class zzanf extends zzamc {
    private final UnifiedNativeAdMapper zzdfm;

    public zzanf(UnifiedNativeAdMapper unifiedNativeAdMapper0) {
        this.zzdfm = unifiedNativeAdMapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getAdvertiser() {
        return this.zzdfm.getAdvertiser();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getBody() {
        return this.zzdfm.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getCallToAction() {
        return this.zzdfm.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final Bundle getExtras() {
        return this.zzdfm.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getHeadline() {
        return this.zzdfm.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final List getImages() {
        List list0 = this.zzdfm.getImages();
        List list1 = new ArrayList();
        if(list0 != null) {
            for(Object object0: list0) {
                list1.add(new zzacd(((Image)object0).getDrawable(), ((Image)object0).getUri(), ((Image)object0).getScale(), ((Image)object0).getWidth(), ((Image)object0).getHeight()));
            }
        }
        return list1;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final float getMediaContentAspectRatio() {
        return this.zzdfm.getMediaContentAspectRatio();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final boolean getOverrideClickHandling() {
        return this.zzdfm.getOverrideClickHandling();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final boolean getOverrideImpressionRecording() {
        return this.zzdfm.getOverrideImpressionRecording();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getPrice() {
        return this.zzdfm.getPrice();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final double getStarRating() {
        return this.zzdfm.getStarRating() == null ? -1.0 : ((double)this.zzdfm.getStarRating());
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final String getStore() {
        return this.zzdfm.getStore();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final zzxj getVideoController() {
        return this.zzdfm.getVideoController() == null ? null : this.zzdfm.getVideoController().zzdq();
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final float getVideoCurrentTime() {
        return 0.0f;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final float getVideoDuration() {
        return 0.0f;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final void recordImpression() {
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final void zzc(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) {
        HashMap hashMap0 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper1);
        HashMap hashMap1 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper2);
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzdfm.trackViews(view0, hashMap0, hashMap1);
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final zzacr zzrk() {
        Image nativeAd$Image0 = this.zzdfm.getIcon();
        return nativeAd$Image0 != null ? new zzacd(nativeAd$Image0.getDrawable(), nativeAd$Image0.getUri(), nativeAd$Image0.getScale(), nativeAd$Image0.getWidth(), nativeAd$Image0.getHeight()) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final zzacj zzrl() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final IObjectWrapper zzrm() {
        Object object0 = this.zzdfm.zzjt();
        return object0 == null ? null : ObjectWrapper.wrap(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final IObjectWrapper zzsz() {
        View view0 = this.zzdfm.getAdChoicesContent();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final IObjectWrapper zzta() {
        View view0 = this.zzdfm.zzace();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final void zzu(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }

    @Override  // com.google.android.gms.internal.ads.zzamd
    public final void zzw(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }
}

