package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzxr {
    private final int zzabv;
    private final int zzabw;
    private final String zzabx;
    private final boolean zzbkp;
    private final int zzccn;
    private final String zzccq;
    private final String zzccs;
    private final Bundle zzccu;
    private final String zzccw;
    private final boolean zzccy;
    private final List zzccz;
    private final Bundle zzcet;
    private final AdInfo zzcex;
    private final Map zzcey;
    private final SearchAdRequest zzcez;
    private final Set zzcfa;
    private final Set zzcfb;
    private final Date zzmg;
    private final Set zzmi;
    private final Location zzmk;

    public zzxr(zzxq zzxq0) {
        this(zzxq0, null);
    }

    public zzxr(zzxq zzxq0, SearchAdRequest searchAdRequest0) {
        this.zzmg = zzxq0.zzmg;
        this.zzccs = zzxq0.zzccs;
        this.zzccz = zzxq0.zzccz;
        this.zzccn = zzxq0.zzccn;
        this.zzmi = Collections.unmodifiableSet(zzxq0.zzces);
        this.zzmk = zzxq0.zzmk;
        this.zzbkp = zzxq0.zzbkp;
        this.zzcet = zzxq0.zzcet;
        this.zzcey = Collections.unmodifiableMap(zzxq0.zzceu);
        this.zzccq = zzxq0.zzccq;
        this.zzccw = zzxq0.zzccw;
        this.zzcez = searchAdRequest0;
        this.zzabv = zzxq0.zzabv;
        this.zzcfa = Collections.unmodifiableSet(zzxq0.zzcev);
        this.zzccu = zzxq0.zzccu;
        this.zzcfb = Collections.unmodifiableSet(zzxq0.zzcew);
        this.zzccy = zzxq.zzp(zzxq0);
        this.zzcex = zzxq.zzq(zzxq0);
        this.zzabw = zzxq.zzr(zzxq0);
        this.zzabx = zzxq.zzs(zzxq0);
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzmg;
    }

    public final String getContentUrl() {
        return this.zzccs;
    }

    public final Bundle getCustomEventExtrasBundle(Class class0) {
        Bundle bundle0 = this.zzcet.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        return bundle0 == null ? null : bundle0.getBundle(class0.getName());
    }

    public final Bundle getCustomTargeting() {
        return this.zzccu;
    }

    @Deprecated
    public final int getGender() {
        return this.zzccn;
    }

    public final Set getKeywords() {
        return this.zzmi;
    }

    public final Location getLocation() {
        return this.zzmk;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbkp;
    }

    @Nullable
    public final String getMaxAdContentRating() {
        return this.zzabx;
    }

    @Deprecated
    public final NetworkExtras getNetworkExtras(Class class0) {
        return (NetworkExtras)this.zzcey.get(class0);
    }

    public final Bundle getNetworkExtrasBundle(Class class0) {
        return this.zzcet.getBundle(class0.getName());
    }

    public final String getPublisherProvidedId() {
        return this.zzccq;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzccy;
    }

    public final boolean isTestDevice(Context context0) {
        RequestConfiguration requestConfiguration0 = zzxu.zzpy().getRequestConfiguration();
        String s = zzayx.zzbl(context0);
        return this.zzcfa.contains(s) || requestConfiguration0.getTestDeviceIds().contains(s);
    }

    public final List zzpp() {
        return new ArrayList(this.zzccz);
    }

    public final String zzpq() {
        return this.zzccw;
    }

    public final SearchAdRequest zzpr() {
        return this.zzcez;
    }

    public final Map zzps() {
        return this.zzcey;
    }

    public final Bundle zzpt() {
        return this.zzcet;
    }

    public final int zzpu() {
        return this.zzabv;
    }

    public final Set zzpv() {
        return this.zzcfb;
    }

    @Nullable
    public final AdInfo zzpw() {
        return this.zzcex;
    }

    public final int zzpx() {
        return this.zzabw;
    }
}

