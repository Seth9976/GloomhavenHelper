package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class zzamn extends zzalw {
    private final NativeAppInstallAdMapper zzdfc;

    public zzamn(NativeAppInstallAdMapper nativeAppInstallAdMapper0) {
        this.zzdfc = nativeAppInstallAdMapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getBody() {
        return this.zzdfc.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getCallToAction() {
        return this.zzdfc.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final Bundle getExtras() {
        return this.zzdfc.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getHeadline() {
        return this.zzdfc.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final List getImages() {
        List list0 = this.zzdfc.getImages();
        if(list0 != null) {
            List list1 = new ArrayList();
            for(Object object0: list0) {
                list1.add(new zzacd(((Image)object0).getDrawable(), ((Image)object0).getUri(), ((Image)object0).getScale(), ((Image)object0).getWidth(), ((Image)object0).getHeight()));
            }
            return list1;
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final boolean getOverrideClickHandling() {
        return this.zzdfc.getOverrideClickHandling();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final boolean getOverrideImpressionRecording() {
        return this.zzdfc.getOverrideImpressionRecording();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getPrice() {
        return this.zzdfc.getPrice();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final double getStarRating() {
        return this.zzdfc.getStarRating();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final String getStore() {
        return this.zzdfc.getStore();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzxj getVideoController() {
        return this.zzdfc.getVideoController() == null ? null : this.zzdfc.getVideoController().zzdq();
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void recordImpression() {
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzc(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) {
        HashMap hashMap0 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper1);
        HashMap hashMap1 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper2);
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzacr zzrk() {
        Image nativeAd$Image0 = this.zzdfc.getIcon();
        return nativeAd$Image0 != null ? new zzacd(nativeAd$Image0.getDrawable(), nativeAd$Image0.getUri(), nativeAd$Image0.getScale(), nativeAd$Image0.getWidth(), nativeAd$Image0.getHeight()) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final zzacj zzrl() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzrm() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzsz() {
        View view0 = this.zzdfc.getAdChoicesContent();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final IObjectWrapper zzta() {
        View view0 = this.zzdfc.zzace();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzu(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzv(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzdfc.trackView(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalx
    public final void zzw(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }
}

