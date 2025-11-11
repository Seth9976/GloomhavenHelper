package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class zzamm extends zzamb {
    private final NativeContentAdMapper zzdfb;

    public zzamm(NativeContentAdMapper nativeContentAdMapper0) {
        this.zzdfb = nativeContentAdMapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final String getAdvertiser() {
        return this.zzdfb.getAdvertiser();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final String getBody() {
        return this.zzdfb.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final String getCallToAction() {
        return this.zzdfb.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final Bundle getExtras() {
        return this.zzdfb.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final String getHeadline() {
        return this.zzdfb.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final List getImages() {
        List list0 = this.zzdfb.getImages();
        if(list0 != null) {
            List list1 = new ArrayList();
            for(Object object0: list0) {
                list1.add(new zzacd(((Image)object0).getDrawable(), ((Image)object0).getUri(), ((Image)object0).getScale(), ((Image)object0).getWidth(), ((Image)object0).getHeight()));
            }
            return list1;
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final boolean getOverrideClickHandling() {
        return this.zzdfb.getOverrideClickHandling();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final boolean getOverrideImpressionRecording() {
        return this.zzdfb.getOverrideImpressionRecording();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final zzxj getVideoController() {
        return this.zzdfb.getVideoController() == null ? null : this.zzdfb.getVideoController().zzdq();
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final void recordImpression() {
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final void zzc(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) {
        HashMap hashMap0 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper1);
        HashMap hashMap1 = (HashMap)ObjectWrapper.unwrap(iObjectWrapper2);
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final zzacj zzrl() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final IObjectWrapper zzrm() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final zzacr zzrn() {
        Image nativeAd$Image0 = this.zzdfb.getLogo();
        return nativeAd$Image0 != null ? new zzacd(nativeAd$Image0.getDrawable(), nativeAd$Image0.getUri(), nativeAd$Image0.getScale(), nativeAd$Image0.getWidth(), nativeAd$Image0.getHeight()) : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final IObjectWrapper zzsz() {
        View view0 = this.zzdfb.getAdChoicesContent();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final IObjectWrapper zzta() {
        View view0 = this.zzdfb.zzace();
        return view0 == null ? null : ObjectWrapper.wrap(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final void zzu(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final void zzv(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzdfb.trackView(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaly
    public final void zzw(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
    }
}

