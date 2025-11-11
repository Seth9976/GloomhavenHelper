package com.google.android.gms.ads.search;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxr;

@Deprecated
public final class SearchAdRequest {
    public static final class Builder {
        private final zzxq zzabi;
        private String zzbme;

        public Builder() {
            this.zzabi = new zzxq();
        }

        public final Builder addCustomEventExtrasBundle(Class class0, Bundle bundle0) {
            this.zzabi.zzb(class0, bundle0);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras0) {
            this.zzabi.zza(networkExtras0);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class class0, Bundle bundle0) {
            this.zzabi.zza(class0, bundle0);
            return this;
        }

        @Deprecated
        public final Builder addTestDevice(String s) {
            this.zzabi.zzch(s);
            return this;
        }

        public final SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }

        @Deprecated
        public final Builder setAnchorTextColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setBackgroundColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setBackgroundGradient(int v, int v1) {
            return this;
        }

        @Deprecated
        public final Builder setBorderColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setBorderThickness(int v) {
            return this;
        }

        @Deprecated
        public final Builder setBorderType(int v) {
            return this;
        }

        @Deprecated
        public final Builder setCallButtonColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setCustomChannels(String s) {
            return this;
        }

        @Deprecated
        public final Builder setDescriptionTextColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setFontFace(String s) {
            return this;
        }

        @Deprecated
        public final Builder setHeaderTextColor(int v) {
            return this;
        }

        @Deprecated
        public final Builder setHeaderTextSize(int v) {
            return this;
        }

        public final Builder setLocation(Location location0) {
            this.zzabi.zza(location0);
            return this;
        }

        public final Builder setQuery(String s) {
            this.zzbme = s;
            return this;
        }

        public final Builder setRequestAgent(String s) {
            this.zzabi.zzcl(s);
            return this;
        }

        public final Builder tagForChildDirectedTreatment(boolean z) {
            this.zzabi.zzz(z);
            return this;
        }
    }

    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final zzxr zzabh;
    private final String zzbme;

    private SearchAdRequest(Builder searchAdRequest$Builder0) {
        this.zzbme = searchAdRequest$Builder0.zzbme;
        this.zzabh = new zzxr(searchAdRequest$Builder0.zzabi, this);
    }

    SearchAdRequest(Builder searchAdRequest$Builder0, zzb zzb0) {
        this(searchAdRequest$Builder0);
    }

    @Deprecated
    public final int getAnchorTextColor() {
        return 0;
    }

    @Deprecated
    public final int getBackgroundColor() {
        return 0;
    }

    @Deprecated
    public final int getBackgroundGradientBottom() {
        return 0;
    }

    @Deprecated
    public final int getBackgroundGradientTop() {
        return 0;
    }

    @Deprecated
    public final int getBorderColor() {
        return 0;
    }

    @Deprecated
    public final int getBorderThickness() {
        return 0;
    }

    @Deprecated
    public final int getBorderType() {
        return 0;
    }

    @Deprecated
    public final int getCallButtonColor() {
        return 0;
    }

    @Deprecated
    public final String getCustomChannels() {
        return null;
    }

    public final Bundle getCustomEventExtrasBundle(Class class0) {
        return this.zzabh.getCustomEventExtrasBundle(class0);
    }

    @Deprecated
    public final int getDescriptionTextColor() {
        return 0;
    }

    @Deprecated
    public final String getFontFace() {
        return null;
    }

    @Deprecated
    public final int getHeaderTextColor() {
        return 0;
    }

    @Deprecated
    public final int getHeaderTextSize() {
        return 0;
    }

    public final Location getLocation() {
        return this.zzabh.getLocation();
    }

    @Deprecated
    public final NetworkExtras getNetworkExtras(Class class0) {
        return this.zzabh.getNetworkExtras(class0);
    }

    public final Bundle getNetworkExtrasBundle(Class class0) {
        return this.zzabh.getNetworkExtrasBundle(class0);
    }

    public final String getQuery() {
        return this.zzbme;
    }

    public final boolean isTestDevice(Context context0) {
        return this.zzabh.isTestDevice(context0);
    }

    final zzxr zzdl() {
        return this.zzabh;
    }
}

