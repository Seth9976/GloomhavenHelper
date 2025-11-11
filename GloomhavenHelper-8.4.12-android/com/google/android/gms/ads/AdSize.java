package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzayx;
import com.google.android.gms.internal.ads.zzuk;

@VisibleForTesting
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = null;
    public static final AdSize FLUID = null;
    public static final AdSize FULL_BANNER = null;
    public static final int FULL_WIDTH = -1;
    public static final AdSize INVALID;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SEARCH;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final int height;
    private final int width;
    public static final AdSize zzabj;
    private final String zzabk;
    private boolean zzabl;
    private boolean zzabm;
    private int zzabn;

    static {
        AdSize.BANNER = new AdSize(320, 50, "320x50_mb");
        AdSize.FULL_BANNER = new AdSize(468, 60, "468x60_as");
        AdSize.LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        AdSize.LEADERBOARD = new AdSize(728, 90, "728x90_as");
        AdSize.MEDIUM_RECTANGLE = new AdSize(300, 0xFA, "300x250_as");
        AdSize.WIDE_SKYSCRAPER = new AdSize(0xA0, 600, "160x600_as");
        AdSize.SMART_BANNER = new AdSize(-1, -2, "smart_banner");
        AdSize.FLUID = new AdSize(-3, -4, "fluid");
        AdSize.INVALID = new AdSize(0, 0, "invalid");
        AdSize.zzabj = new AdSize(50, 50, "50x50_mb");
        AdSize.SEARCH = new AdSize(-3, 0, "search_v2");
    }

    public AdSize(int v, int v1) {
        this(v, v1, (v == -1 ? "FULL" : String.valueOf(v)) + "x" + (v1 == -2 ? "AUTO" : String.valueOf(v1)) + "_as");
    }

    AdSize(int v, int v1, String s) {
        if(v < 0 && (v != -3 && v != -1)) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + v);
        }
        if(v1 < 0 && (v1 != -4 && v1 != -2)) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + v1);
        }
        this.width = v;
        this.height = v1;
        this.zzabk = s;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof AdSize ? this.width == ((AdSize)object0).width && this.height == ((AdSize)object0).height && this.zzabk.equals(((AdSize)object0).zzabk) : false;
    }

    public static AdSize getCurrentOrientationAnchoredAdaptiveBannerAdSize(Context context0, int v) {
        AdSize adSize0 = zzayx.zza(context0, v, 50, 0);
        adSize0.zzabl = true;
        return adSize0;
    }

    @Deprecated
    public static AdSize getCurrentOrientationBannerAdSizeWithWidth(Context context0, int v) {
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context0, v);
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHeightInPixels(Context context0) {
        switch(this.height) {
            case -4: 
            case -3: {
                return -1;
            }
            case -2: {
                return zzuk.zzc(context0.getResources().getDisplayMetrics());
            }
            default: {
                return zzayx.zzb(context0, this.height);
            }
        }
    }

    public static AdSize getLandscapeAnchoredAdaptiveBannerAdSize(Context context0, int v) {
        AdSize adSize0 = zzayx.zza(context0, v, 50, 2);
        adSize0.zzabl = true;
        return adSize0;
    }

    @Deprecated
    public static AdSize getLandscapeBannerAdSizeWithWidth(Context context0, int v) {
        return AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(context0, v);
    }

    public static AdSize getPortraitAnchoredAdaptiveBannerAdSize(Context context0, int v) {
        AdSize adSize0 = zzayx.zza(context0, v, 50, 1);
        adSize0.zzabl = true;
        return adSize0;
    }

    @Deprecated
    public static AdSize getPortraitBannerAdSizeWithWidth(Context context0, int v) {
        return AdSize.getPortraitAnchoredAdaptiveBannerAdSize(context0, v);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getWidthInPixels(Context context0) {
        int v = this.width;
        if(v != -1) {
            return v == -4 || v == -3 ? -1 : zzayx.zzb(context0, this.width);
        }
        return zzuk.zzb(context0.getResources().getDisplayMetrics());
    }

    @Override
    public final int hashCode() {
        return this.zzabk.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.height == -2;
    }

    public final boolean isFluid() {
        return this.width == -3 && this.height == -4;
    }

    public final boolean isFullWidth() {
        return this.width == -1;
    }

    @Override
    public final String toString() {
        return this.zzabk;
    }

    final void zzc(boolean z) {
        this.zzabm = true;
    }

    final boolean zzdm() {
        return this.zzabl;
    }

    final boolean zzdn() {
        return this.zzabm;
    }

    final int zzdo() {
        return this.zzabn;
    }

    final void zzl(int v) {
        this.zzabn = v;
    }
}

