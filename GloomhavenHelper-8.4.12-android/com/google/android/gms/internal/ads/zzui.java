package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class zzui {
    public static final zzui zzcdb;

    static {
        zzui.zzcdb = new zzui();
    }

    public static zzash zza(Context context0, zzxr zzxr0, String s) {
        return new zzash(zzui.zza(context0, zzxr0), s);
    }

    public static zzuh zza(Context context0, zzxr zzxr0) {
        zzub zzub0;
        Date date0 = zzxr0.getBirthday();
        long v = date0 == null ? -1L : date0.getTime();
        String s = zzxr0.getContentUrl();
        int v1 = zzxr0.getGender();
        Set set0 = zzxr0.getKeywords();
        List list0 = set0.isEmpty() ? null : Collections.unmodifiableList(new ArrayList(set0));
        String s1 = "";
        boolean z = zzxr0.isTestDevice(context0);
        Location location0 = zzxr0.getLocation();
        Bundle bundle0 = zzxr0.getNetworkExtrasBundle(AdMobAdapter.class);
        if(zzxr0.zzpw() == null) {
            zzub0 = null;
        }
        else {
            if(zzvh.zzph().containsKey(zzxr0.zzpw().getQueryInfo())) {
                s1 = (String)zzvh.zzph().get(zzxr0.zzpw().getQueryInfo());
            }
            zzub0 = new zzub(zzxr0.zzpw().getAdString(), s1);
        }
        SearchAdRequest searchAdRequest0 = zzxr0.zzpr();
        zzyy zzyy0 = searchAdRequest0 == null ? null : new zzyy(searchAdRequest0);
        String s2 = context0.getApplicationContext() == null ? null : zzayx.zza(Thread.currentThread().getStackTrace(), "com.esotericsoftware.gloomhavenhelper");
        RequestConfiguration requestConfiguration0 = zzxu.zzpy().getRequestConfiguration();
        Object object0 = Collections.max(Arrays.asList(new String[]{zzxr0.getMaxAdContentRating(), ""}), zzul.zzcdk);
        List list1 = zzxr0.zzpp();
        return new zzuh(8, v, bundle0, v1, list0, z, Math.max(zzxr0.zzpu(), requestConfiguration0.getTagForChildDirectedTreatment()), zzxr0.getManualImpressionsEnabled(), zzxr0.getPublisherProvidedId(), zzyy0, location0, s, zzxr0.zzpt(), zzxr0.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzxr0.zzpv())), zzxr0.zzpq(), s2, zzxr0.isDesignedForFamilies(), zzub0, Math.max(zzxr0.zzpx(), requestConfiguration0.getTagForUnderAgeOfConsent()), ((String)object0), list1);
    }

    static final int zzd(String s, String s1) {
        return RequestConfiguration.zzabz.indexOf(s) - RequestConfiguration.zzabz.indexOf(s1);
    }
}

