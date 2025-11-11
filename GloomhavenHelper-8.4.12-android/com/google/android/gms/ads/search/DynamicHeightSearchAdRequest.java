package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.ads.zzxr;

public final class DynamicHeightSearchAdRequest {
    public static final class Builder {
        private final com.google.android.gms.ads.search.SearchAdRequest.Builder zzgwn;
        private final Bundle zzgwo;

        public Builder() {
            this.zzgwn = new com.google.android.gms.ads.search.SearchAdRequest.Builder();
            this.zzgwo = new Bundle();
        }

        public final Builder addCustomEventExtrasBundle(Class class0, Bundle bundle0) {
            this.zzgwn.addCustomEventExtrasBundle(class0, bundle0);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras0) {
            this.zzgwn.addNetworkExtras(networkExtras0);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class class0, Bundle bundle0) {
            this.zzgwn.addNetworkExtrasBundle(class0, bundle0);
            return this;
        }

        public final DynamicHeightSearchAdRequest build() {
            this.zzgwn.addNetworkExtrasBundle(AdMobAdapter.class, this.zzgwo);
            return new DynamicHeightSearchAdRequest(this, null);
        }

        public final Builder setAdBorderSelectors(String s) {
            this.zzgwo.putString("csa_adBorderSelectors", s);
            return this;
        }

        public final Builder setAdTest(boolean z) {
            this.zzgwo.putString("csa_adtest", (z ? "on" : "off"));
            return this;
        }

        public final Builder setAdjustableLineHeight(int v) {
            this.zzgwo.putString("csa_adjustableLineHeight", Integer.toString(v));
            return this;
        }

        public final Builder setAdvancedOptionValue(String s, String s1) {
            this.zzgwo.putString(s, s1);
            return this;
        }

        public final Builder setAttributionSpacingBelow(int v) {
            this.zzgwo.putString("csa_attributionSpacingBelow", Integer.toString(v));
            return this;
        }

        public final Builder setBorderSelections(String s) {
            this.zzgwo.putString("csa_borderSelections", s);
            return this;
        }

        public final Builder setChannel(String s) {
            this.zzgwo.putString("csa_channel", s);
            return this;
        }

        public final Builder setColorAdBorder(String s) {
            this.zzgwo.putString("csa_colorAdBorder", s);
            return this;
        }

        public final Builder setColorAdSeparator(String s) {
            this.zzgwo.putString("csa_colorAdSeparator", s);
            return this;
        }

        public final Builder setColorAnnotation(String s) {
            this.zzgwo.putString("csa_colorAnnotation", s);
            return this;
        }

        public final Builder setColorAttribution(String s) {
            this.zzgwo.putString("csa_colorAttribution", s);
            return this;
        }

        public final Builder setColorBackground(String s) {
            this.zzgwo.putString("csa_colorBackground", s);
            return this;
        }

        public final Builder setColorBorder(String s) {
            this.zzgwo.putString("csa_colorBorder", s);
            return this;
        }

        public final Builder setColorDomainLink(String s) {
            this.zzgwo.putString("csa_colorDomainLink", s);
            return this;
        }

        public final Builder setColorText(String s) {
            this.zzgwo.putString("csa_colorText", s);
            return this;
        }

        public final Builder setColorTitleLink(String s) {
            this.zzgwo.putString("csa_colorTitleLink", s);
            return this;
        }

        public final Builder setCssWidth(int v) {
            this.zzgwo.putString("csa_width", Integer.toString(v));
            return this;
        }

        public final Builder setDetailedAttribution(boolean z) {
            this.zzgwo.putString("csa_detailedAttribution", Boolean.toString(z));
            return this;
        }

        public final Builder setFontFamily(String s) {
            this.zzgwo.putString("csa_fontFamily", s);
            return this;
        }

        public final Builder setFontFamilyAttribution(String s) {
            this.zzgwo.putString("csa_fontFamilyAttribution", s);
            return this;
        }

        public final Builder setFontSizeAnnotation(int v) {
            this.zzgwo.putString("csa_fontSizeAnnotation", Integer.toString(v));
            return this;
        }

        public final Builder setFontSizeAttribution(int v) {
            this.zzgwo.putString("csa_fontSizeAttribution", Integer.toString(v));
            return this;
        }

        public final Builder setFontSizeDescription(int v) {
            this.zzgwo.putString("csa_fontSizeDescription", Integer.toString(v));
            return this;
        }

        public final Builder setFontSizeDomainLink(int v) {
            this.zzgwo.putString("csa_fontSizeDomainLink", Integer.toString(v));
            return this;
        }

        public final Builder setFontSizeTitle(int v) {
            this.zzgwo.putString("csa_fontSizeTitle", Integer.toString(v));
            return this;
        }

        public final Builder setHostLanguage(String s) {
            this.zzgwo.putString("csa_hl", s);
            return this;
        }

        public final Builder setIsClickToCallEnabled(boolean z) {
            this.zzgwo.putString("csa_clickToCall", Boolean.toString(z));
            return this;
        }

        public final Builder setIsLocationEnabled(boolean z) {
            this.zzgwo.putString("csa_location", Boolean.toString(z));
            return this;
        }

        public final Builder setIsPlusOnesEnabled(boolean z) {
            this.zzgwo.putString("csa_plusOnes", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSellerRatingsEnabled(boolean z) {
            this.zzgwo.putString("csa_sellerRatings", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSiteLinksEnabled(boolean z) {
            this.zzgwo.putString("csa_siteLinks", Boolean.toString(z));
            return this;
        }

        public final Builder setIsTitleBold(boolean z) {
            this.zzgwo.putString("csa_titleBold", Boolean.toString(z));
            return this;
        }

        public final Builder setIsTitleUnderlined(boolean z) {
            this.zzgwo.putString("csa_noTitleUnderline", Boolean.toString(!z));
            return this;
        }

        public final Builder setLocationColor(String s) {
            this.zzgwo.putString("csa_colorLocation", s);
            return this;
        }

        public final Builder setLocationFontSize(int v) {
            this.zzgwo.putString("csa_fontSizeLocation", Integer.toString(v));
            return this;
        }

        public final Builder setLongerHeadlines(boolean z) {
            this.zzgwo.putString("csa_longerHeadlines", Boolean.toString(z));
            return this;
        }

        public final Builder setNumber(int v) {
            this.zzgwo.putString("csa_number", Integer.toString(v));
            return this;
        }

        public final Builder setPage(int v) {
            this.zzgwo.putString("csa_adPage", Integer.toString(v));
            return this;
        }

        public final Builder setQuery(String s) {
            this.zzgwn.setQuery(s);
            return this;
        }

        public final Builder setVerticalSpacing(int v) {
            this.zzgwo.putString("csa_verticalSpacing", Integer.toString(v));
            return this;
        }
    }

    private final SearchAdRequest zzgwm;

    private DynamicHeightSearchAdRequest(Builder dynamicHeightSearchAdRequest$Builder0) {
        this.zzgwm = dynamicHeightSearchAdRequest$Builder0.zzgwn.build();
    }

    DynamicHeightSearchAdRequest(Builder dynamicHeightSearchAdRequest$Builder0, zza zza0) {
        this(dynamicHeightSearchAdRequest$Builder0);
    }

    public final Bundle getCustomEventExtrasBundle(Class class0) {
        return this.zzgwm.getCustomEventExtrasBundle(class0);
    }

    @Deprecated
    public final NetworkExtras getNetworkExtras(Class class0) {
        return this.zzgwm.getNetworkExtras(class0);
    }

    public final Bundle getNetworkExtrasBundle(Class class0) {
        return this.zzgwm.getNetworkExtrasBundle(class0);
    }

    public final String getQuery() {
        return this.zzgwm.getQuery();
    }

    public final boolean isTestDevice(Context context0) {
        return this.zzgwm.isTestDevice(context0);
    }

    final zzxr zzdl() {
        return this.zzgwm.zzdl();
    }
}

