package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MediationAdConfiguration {
    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForChildDirectedTreatment {
    }

    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    private final String zzabx;
    private final int zzdeq;
    private final String zzdfr;
    private final String zzejm;
    private final Bundle zzejn;
    private final Bundle zzejo;
    private final int zzejp;
    private final boolean zzmj;
    private final Location zzmk;
    private final Context zzur;

    public MediationAdConfiguration(Context context0, String s, Bundle bundle0, Bundle bundle1, boolean z, @Nullable Location location0, int v, int v1, @Nullable String s1, String s2) {
        this.zzejm = s;
        this.zzejn = bundle0;
        this.zzejo = bundle1;
        this.zzur = context0;
        this.zzmj = z;
        this.zzmk = location0;
        this.zzdeq = v;
        this.zzejp = v1;
        this.zzabx = s1;
        this.zzdfr = s2;
    }

    public String getBidResponse() {
        return this.zzejm;
    }

    public Context getContext() {
        return this.zzur;
    }

    public Location getLocation() {
        return this.zzmk;
    }

    @Nullable
    public String getMaxAdContentRating() {
        return this.zzabx;
    }

    public Bundle getMediationExtras() {
        return this.zzejo;
    }

    public Bundle getServerParameters() {
        return this.zzejn;
    }

    public String getWatermark() {
        return this.zzdfr;
    }

    public boolean isTestRequest() {
        return this.zzmj;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzdeq;
    }

    public int taggedForUnderAgeTreatment() {
        return this.zzejp;
    }
}

