package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;
import java.util.Map;

public class UnifiedNativeAdMapper {
    private Bundle extras;
    private VideoController zzcfe;
    private String zzdln;
    private String zzejs;
    private List zzejt;
    private Image zzeju;
    private String zzejv;
    private String zzejx;
    private String zzejy;
    private View zzejz;
    private boolean zzeka;
    private String zzekc;
    private Double zzekd;
    private View zzeke;
    private Object zzekf;
    private boolean zzekg;
    private boolean zzekh;
    private float zzeki;

    public UnifiedNativeAdMapper() {
        this.extras = new Bundle();
    }

    public View getAdChoicesContent() {
        return this.zzeke;
    }

    public final String getAdvertiser() {
        return this.zzekc;
    }

    public final String getBody() {
        return this.zzdln;
    }

    public final String getCallToAction() {
        return this.zzejv;
    }

    public float getCurrentTime() [...] // Inlined contents

    public float getDuration() [...] // Inlined contents

    public final Bundle getExtras() {
        return this.extras;
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

    public float getMediaContentAspectRatio() {
        return this.zzeki;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzekh;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzekg;
    }

    public final String getPrice() {
        return this.zzejy;
    }

    public final Double getStarRating() {
        return this.zzekd;
    }

    public final String getStore() {
        return this.zzejx;
    }

    public final VideoController getVideoController() {
        return this.zzcfe;
    }

    public void handleClick(View view0) {
    }

    public boolean hasVideoContent() {
        return this.zzeka;
    }

    public void recordImpression() {
    }

    public void setAdChoicesContent(View view0) {
        this.zzeke = view0;
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

    public final void setExtras(Bundle bundle0) {
        this.extras = bundle0;
    }

    public void setHasVideoContent(boolean z) {
        this.zzeka = z;
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

    public void setMediaContentAspectRatio(float f) {
        this.zzeki = f;
    }

    public void setMediaView(View view0) {
        this.zzejz = view0;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.zzekh = z;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.zzekg = z;
    }

    public final void setPrice(String s) {
        this.zzejy = s;
    }

    public final void setStarRating(Double double0) {
        this.zzekd = double0;
    }

    public final void setStore(String s) {
        this.zzejx = s;
    }

    public void trackViews(View view0, Map map0, Map map1) {
    }

    public void untrackView(View view0) {
    }

    public final void zza(VideoController videoController0) {
        this.zzcfe = videoController0;
    }

    public final View zzace() {
        return this.zzejz;
    }

    public final Object zzjt() {
        return this.zzekf;
    }

    public final void zzn(Object object0) {
        this.zzekf = object0;
    }
}

