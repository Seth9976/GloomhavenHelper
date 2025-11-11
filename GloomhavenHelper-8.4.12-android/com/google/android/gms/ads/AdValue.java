package com.google.android.gms.ads;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class AdValue {
    @Retention(RetentionPolicy.SOURCE)
    public @interface PrecisionType {
        public static final int ESTIMATED = 1;
        public static final int PRECISE = 3;
        public static final int PUBLISHER_PROVIDED = 2;
        public static final int UNKNOWN;

    }

    private final int zzabo;
    private final String zzabp;
    private final long zzabq;

    private AdValue(int v, String s, long v1) {
        this.zzabo = v;
        this.zzabp = s;
        this.zzabq = v1;
    }

    public final String getCurrencyCode() {
        return this.zzabp;
    }

    public final int getPrecisionType() {
        return this.zzabo;
    }

    public final long getValueMicros() {
        return this.zzabq;
    }

    public static AdValue zza(int v, String s, long v1) {
        return new AdValue(v, s, v1);
    }
}

