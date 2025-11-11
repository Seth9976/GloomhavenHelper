package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxr;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.List;
import java.util.Set;

@VisibleForTesting
public final class PublisherAdRequest {
    @VisibleForTesting
    public static final class Builder {
        private final zzxq zzabi;

        public Builder() {
            this.zzabi = new zzxq();
        }

        public final Builder addCategoryExclusion(String s) {
            this.zzabi.zzcm(s);
            return this;
        }

        public final Builder addCustomEventExtrasBundle(Class class0, Bundle bundle0) {
            this.zzabi.zzb(class0, bundle0);
            return this;
        }

        public final Builder addCustomTargeting(String s, String s1) {
            this.zzabi.zze(s, s1);
            return this;
        }

        public final Builder addCustomTargeting(String s, List list0) {
            if(list0 != null) {
                String s1 = TextUtils.join(",", list0);
                this.zzabi.zze(s, s1);
            }
            return this;
        }

        public final Builder addKeyword(String s) {
            this.zzabi.zzcg(s);
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

        public final PublisherAdRequest build() {
            return new PublisherAdRequest(this, null);
        }

        @KeepForSdk
        public final Builder setAdInfo(AdInfo adInfo0) {
            this.zzabi.zza(adInfo0);
            return this;
        }

        @Deprecated
        public final Builder setBirthday(Date date0) {
            this.zzabi.zza(date0);
            return this;
        }

        public final Builder setContentUrl(String s) {
            Preconditions.checkNotNull(s, "Content URL must be non-null.");
            Preconditions.checkNotEmpty(s, "Content URL must be non-empty.");
            Preconditions.checkArgument(s.length() <= 0x200, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[]{0x200, s.length()});
            this.zzabi.zzcj(s);
            return this;
        }

        @Deprecated
        public final Builder setGender(int v) {
            this.zzabi.zzck(v);
            return this;
        }

        @Deprecated
        public final Builder setIsDesignedForFamilies(boolean z) {
            this.zzabi.zzaa(z);
            return this;
        }

        public final Builder setLocation(Location location0) {
            this.zzabi.zza(location0);
            return this;
        }

        @Deprecated
        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzabi.setManualImpressionsEnabled(z);
            return this;
        }

        @Deprecated
        public final Builder setMaxAdContentRating(String s) {
            this.zzabi.zzcn(s);
            return this;
        }

        public final Builder setPublisherProvidedId(String s) {
            this.zzabi.zzck(s);
            return this;
        }

        public final Builder setRequestAgent(String s) {
            this.zzabi.zzcl(s);
            return this;
        }

        @Deprecated
        public final Builder setTagForUnderAgeOfConsent(int v) {
            this.zzabi.zzcl(v);
            return this;
        }

        @Deprecated
        public final Builder tagForChildDirectedTreatment(boolean z) {
            this.zzabi.zzz(z);
            return this;
        }
    }

    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface MaxAdContentRating {
    }

    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForUnderAgeOfConsent {
    }

    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_G = "G";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_MA = "MA";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_PG = "PG";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_T = "T";
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_FALSE = 0;
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE = 1;
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_UNSPECIFIED = -1;
    private final zzxr zzabh;

    private PublisherAdRequest(Builder publisherAdRequest$Builder0) {
        this.zzabh = new zzxr(publisherAdRequest$Builder0.zzabi);
    }

    PublisherAdRequest(Builder publisherAdRequest$Builder0, zza zza0) {
        this(publisherAdRequest$Builder0);
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzabh.getBirthday();
    }

    public final String getContentUrl() {
        return this.zzabh.getContentUrl();
    }

    public final Bundle getCustomEventExtrasBundle(Class class0) {
        return this.zzabh.getCustomEventExtrasBundle(class0);
    }

    public final Bundle getCustomTargeting() {
        return this.zzabh.getCustomTargeting();
    }

    @Deprecated
    public final int getGender() {
        return this.zzabh.getGender();
    }

    public final Set getKeywords() {
        return this.zzabh.getKeywords();
    }

    public final Location getLocation() {
        return this.zzabh.getLocation();
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzabh.getManualImpressionsEnabled();
    }

    @Deprecated
    public final NetworkExtras getNetworkExtras(Class class0) {
        return this.zzabh.getNetworkExtras(class0);
    }

    public final Bundle getNetworkExtrasBundle(Class class0) {
        return this.zzabh.getNetworkExtrasBundle(class0);
    }

    public final String getPublisherProvidedId() {
        return this.zzabh.getPublisherProvidedId();
    }

    public final boolean isTestDevice(Context context0) {
        return this.zzabh.isTestDevice(context0);
    }

    @KeepForSdk
    @Deprecated
    public static void updateCorrelator() {
    }

    public final zzxr zzdl() {
        return this.zzabh;
    }
}

