package com.google.android.gms.internal.ads;

import android.location.Location;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

public final class zzamh implements MediationAdRequest {
    private final String zzabx;
    private final int zzccn;
    private final boolean zzccy;
    private final int zzdeq;
    private final int zzder;
    private final Date zzmg;
    private final Set zzmi;
    private final boolean zzmj;
    private final Location zzmk;

    public zzamh(@Nullable Date date0, int v, @Nullable Set set0, @Nullable Location location0, boolean z, int v1, boolean z1, int v2, String s) {
        this.zzmg = date0;
        this.zzccn = v;
        this.zzmi = set0;
        this.zzmk = location0;
        this.zzmj = z;
        this.zzdeq = v1;
        this.zzccy = z1;
        this.zzder = v2;
        this.zzabx = s;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final Date getBirthday() {
        return this.zzmg;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final int getGender() {
        return this.zzccn;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Set getKeywords() {
        return this.zzmi;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Location getLocation() {
        return this.zzmk;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzccy;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isTesting() {
        return this.zzmj;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int taggedForChildDirectedTreatment() {
        return this.zzdeq;
    }
}

