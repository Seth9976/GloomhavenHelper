package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;

@Deprecated
public class NativeContentAdMapper extends NativeAdMapper {
    private String zzdln;
    private String zzejs;
    private List zzejt;
    private String zzejv;
    private Image zzekb;
    private String zzekc;

    public final String getAdvertiser() {
        return this.zzekc;
    }

    public final String getBody() {
        return this.zzdln;
    }

    public final String getCallToAction() {
        return this.zzejv;
    }

    public final String getHeadline() {
        return this.zzejs;
    }

    public final List getImages() {
        return this.zzejt;
    }

    public final Image getLogo() {
        return this.zzekb;
    }

    public final void setAdvertiser(String s) {
        this.zzekc = s;
    }

    public final void setBody(String s) {
        this.zzdln = s;
    }

    public final void setCallToAction(String s) {
        this.zzejv = s;
    }

    public final void setHeadline(String s) {
        this.zzejs = s;
    }

    public final void setImages(List list0) {
        this.zzejt = list0;
    }

    public final void setLogo(Image nativeAd$Image0) {
        this.zzekb = nativeAd$Image0;
    }
}

