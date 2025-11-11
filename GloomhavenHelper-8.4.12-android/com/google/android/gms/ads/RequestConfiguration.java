package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzazh;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public class RequestConfiguration {
    public static class Builder {
        private int zzabv;
        private int zzabw;
        private String zzabx;
        private final List zzaby;

        public Builder() {
            this.zzabv = -1;
            this.zzabw = -1;
            this.zzabx = null;
            this.zzaby = new ArrayList();
        }

        public RequestConfiguration build() {
            return new RequestConfiguration(this.zzabv, this.zzabw, this.zzabx, this.zzaby, null);
        }

        public Builder setMaxAdContentRating(String s) {
            if(s != null && !"".equals(s)) {
                if(!"G".equals(s) && !"PG".equals(s) && !"T".equals(s) && !"MA".equals(s)) {
                    String s1 = String.valueOf(s);
                    zzazh.zzfa((s1.length() == 0 ? new String("Invalid value passed to setMaxAdContentRating: ") : "Invalid value passed to setMaxAdContentRating: " + s1));
                    return this;
                }
                this.zzabx = s;
                return this;
            }
            this.zzabx = null;
            return this;
        }

        public Builder setTagForChildDirectedTreatment(int v) {
            if(v != -1 && v != 0 && v != 1) {
                zzazh.zzfa(("Invalid value passed to setTagForChildDirectedTreatment: " + v));
                return this;
            }
            this.zzabv = v;
            return this;
        }

        public Builder setTagForUnderAgeOfConsent(int v) {
            if(v != -1 && v != 0 && v != 1) {
                zzazh.zzfa(("Invalid value passed to setTagForUnderAgeOfConsent: " + v));
                return this;
            }
            this.zzabw = v;
            return this;
        }

        public Builder setTestDeviceIds(@Nullable List list0) {
            this.zzaby.clear();
            if(list0 != null) {
                this.zzaby.addAll(list0);
            }
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MaxAdContentRating {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForChildDirectedTreatment {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForUnderAgeOfConsent {
    }

    public static final String MAX_AD_CONTENT_RATING_G = "G";
    public static final String MAX_AD_CONTENT_RATING_MA = "MA";
    public static final String MAX_AD_CONTENT_RATING_PG = "PG";
    public static final String MAX_AD_CONTENT_RATING_T = "T";
    public static final String MAX_AD_CONTENT_RATING_UNSPECIFIED = "";
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_FALSE = 0;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE = 1;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_UNSPECIFIED = -1;
    private final int zzabv;
    private final int zzabw;
    private final String zzabx;
    private final List zzaby;
    public static final List zzabz;

    static {
        RequestConfiguration.zzabz = Arrays.asList(new String[]{"MA", "T", "PG", "G"});
    }

    private RequestConfiguration(int v, int v1, String s, List list0) {
        this.zzabv = v;
        this.zzabw = v1;
        this.zzabx = s;
        this.zzaby = list0;
    }

    RequestConfiguration(int v, int v1, String s, List list0, zzc zzc0) {
        this(v, v1, s, list0);
    }

    public String getMaxAdContentRating() {
        return this.zzabx == null ? "" : this.zzabx;
    }

    public int getTagForChildDirectedTreatment() {
        return this.zzabv;
    }

    public int getTagForUnderAgeOfConsent() {
        return this.zzabw;
    }

    public List getTestDeviceIds() {
        return new ArrayList(this.zzaby);
    }

    public Builder toBuilder() {
        return new Builder().setTagForChildDirectedTreatment(this.zzabv).setTagForUnderAgeOfConsent(this.zzabw).setMaxAdContentRating(this.zzabx).setTestDeviceIds(this.zzaby);
    }
}

