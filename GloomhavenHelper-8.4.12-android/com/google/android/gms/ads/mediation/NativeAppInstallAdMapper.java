package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;

@Deprecated
public class NativeAppInstallAdMapper extends NativeAdMapper {
    private String zzdln;
    private String zzejs;
    private List zzejt;
    private Image zzeju;
    private String zzejv;
    private double zzejw;
    private String zzejx;
    private String zzejy;

    public final String getBody() {
        return this.zzdln;
    }

    public final String getCallToAction() {
        return this.zzejv;
    }

    public final String getHeadline() {
        return this.zzejs;
    }

    public final Image getIcon() {
        return this.zzeju;
    }

    public final List getImages() {
        return this.zzejt;
    }

    public final String getPrice() {
        return this.zzejy;
    }

    public final double getStarRating() {
        return this.zzejw;
    }

    public final String getStore() {
        return this.zzejx;
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

    public final void setIcon(Image nativeAd$Image0) {
        this.zzeju = nativeAd$Image0;
    }

    public final void setImages(List list0) {
        this.zzejt = list0;
    }

    public final void setPrice(String s) {
        this.zzejy = s;
    }

    public final void setStarRating(double f) {
        this.zzejw = f;
    }

    public final void setStore(String s) {
        this.zzejx = s;
    }
}

