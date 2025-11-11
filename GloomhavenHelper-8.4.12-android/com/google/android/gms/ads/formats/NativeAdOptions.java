package com.google.android.gms.ads.formats;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;

public final class NativeAdOptions {
    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        private boolean zzbkd;
        private int zzbke;
        private int zzbkf;
        private boolean zzbkg;
        private int zzbkh;
        private VideoOptions zzbki;
        private boolean zzbkj;

        public Builder() {
            this.zzbkd = false;
            this.zzbke = -1;
            this.zzbkf = 0;
            this.zzbkg = false;
            this.zzbkh = 1;
            this.zzbkj = false;
        }

        public final NativeAdOptions build() {
            return new NativeAdOptions(this, null);
        }

        public final Builder setAdChoicesPlacement(@AdChoicesPlacement int v) {
            this.zzbkh = v;
            return this;
        }

        @Deprecated
        public final Builder setImageOrientation(int v) {
            this.zzbke = v;
            return this;
        }

        public final Builder setMediaAspectRatio(@NativeMediaAspectRatio int v) {
            this.zzbkf = v;
            return this;
        }

        public final Builder setRequestCustomMuteThisAd(boolean z) {
            this.zzbkj = z;
            return this;
        }

        public final Builder setRequestMultipleImages(boolean z) {
            this.zzbkg = z;
            return this;
        }

        public final Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzbkd = z;
            return this;
        }

        public final Builder setVideoOptions(VideoOptions videoOptions0) {
            this.zzbki = videoOptions0;
            return this;
        }
    }

    public @interface NativeMediaAspectRatio {
    }

    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_ANY = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE = 2;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_PORTRAIT = 3;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_SQUARE = 4;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_UNKNOWN = 0;
    @Deprecated
    public static final int ORIENTATION_ANY = 0;
    @Deprecated
    public static final int ORIENTATION_LANDSCAPE = 2;
    @Deprecated
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzbkd;
    private final int zzbke;
    private final int zzbkf;
    private final boolean zzbkg;
    private final int zzbkh;
    private final VideoOptions zzbki;
    private final boolean zzbkj;

    private NativeAdOptions(Builder nativeAdOptions$Builder0) {
        this.zzbkd = nativeAdOptions$Builder0.zzbkd;
        this.zzbke = nativeAdOptions$Builder0.zzbke;
        this.zzbkf = nativeAdOptions$Builder0.zzbkf;
        this.zzbkg = nativeAdOptions$Builder0.zzbkg;
        this.zzbkh = nativeAdOptions$Builder0.zzbkh;
        this.zzbki = nativeAdOptions$Builder0.zzbki;
        this.zzbkj = nativeAdOptions$Builder0.zzbkj;
    }

    NativeAdOptions(Builder nativeAdOptions$Builder0, zza zza0) {
        this(nativeAdOptions$Builder0);
    }

    public final int getAdChoicesPlacement() {
        return this.zzbkh;
    }

    @Deprecated
    public final int getImageOrientation() {
        return this.zzbke;
    }

    public final int getMediaAspectRatio() {
        return this.zzbkf;
    }

    @Nullable
    public final VideoOptions getVideoOptions() {
        return this.zzbki;
    }

    public final boolean shouldRequestMultipleImages() {
        return this.zzbkg;
    }

    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zzbkd;
    }

    public final boolean zzjp() {
        return this.zzbkj;
    }
}

