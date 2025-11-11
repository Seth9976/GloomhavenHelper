package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public final class zzdgm implements zzdgj {
    private final Object[] zzgtn;

    public zzdgm(zzuh zzuh0, String s, int v, String s1, zzur zzur0) {
        HashSet hashSet0 = new HashSet(Arrays.asList(s1.split(",")));
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(s1);
        arrayList0.add(s);
        if(hashSet0.contains("networkType")) {
            arrayList0.add(v);
        }
        if(hashSet0.contains("birthday")) {
            arrayList0.add(zzuh0.zzccm);
        }
        if(hashSet0.contains("extras")) {
            arrayList0.add(zzdgm.zzq(zzuh0.extras));
        }
        else if(hashSet0.contains("npa")) {
            arrayList0.add(zzuh0.extras.getString("npa"));
        }
        if(hashSet0.contains("gender")) {
            arrayList0.add(zzuh0.zzccn);
        }
        if(hashSet0.contains("keywords")) {
            if(zzuh0.zzcco == null) {
                arrayList0.add(null);
            }
            else {
                arrayList0.add(zzuh0.zzcco.toString());
            }
        }
        if(hashSet0.contains("isTestDevice")) {
            arrayList0.add(Boolean.valueOf(zzuh0.zzccp));
        }
        if(hashSet0.contains("tagForChildDirectedTreatment")) {
            arrayList0.add(zzuh0.zzabv);
        }
        if(hashSet0.contains("manualImpressionsEnabled")) {
            arrayList0.add(Boolean.valueOf(zzuh0.zzbkp));
        }
        if(hashSet0.contains("publisherProvidedId")) {
            arrayList0.add(zzuh0.zzccq);
        }
        if(hashSet0.contains("location")) {
            if(zzuh0.zzmk == null) {
                arrayList0.add(null);
            }
            else {
                arrayList0.add(zzuh0.zzmk.toString());
            }
        }
        if(hashSet0.contains("contentUrl")) {
            arrayList0.add(zzuh0.zzccs);
        }
        if(hashSet0.contains("networkExtras")) {
            arrayList0.add(zzdgm.zzq(zzuh0.zzcct));
        }
        if(hashSet0.contains("customTargeting")) {
            arrayList0.add(zzdgm.zzq(zzuh0.zzccu));
        }
        if(hashSet0.contains("categoryExclusions")) {
            if(zzuh0.zzccv == null) {
                arrayList0.add(null);
            }
            else {
                arrayList0.add(zzuh0.zzccv.toString());
            }
        }
        if(hashSet0.contains("requestAgent")) {
            arrayList0.add(zzuh0.zzccw);
        }
        if(hashSet0.contains("requestPackage")) {
            arrayList0.add(zzuh0.zzccx);
        }
        if(hashSet0.contains("isDesignedForFamilies")) {
            arrayList0.add(Boolean.valueOf(zzuh0.zzccy));
        }
        if(hashSet0.contains("tagForUnderAgeOfConsent")) {
            arrayList0.add(zzuh0.zzabw);
        }
        if(hashSet0.contains("maxAdContentRating")) {
            arrayList0.add(zzuh0.zzabx);
        }
        if(hashSet0.contains("orientation")) {
            if(zzur0 == null) {
                arrayList0.add(null);
            }
            else {
                arrayList0.add(zzur0.orientation);
            }
        }
        this.zzgtn = arrayList0.toArray();
    }

    @Override  // com.google.android.gms.internal.ads.zzdgj
    public final boolean equals(Object object0) {
        return object0 instanceof zzdgm ? Arrays.equals(this.zzgtn, ((zzdgm)object0).zzgtn) : false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgj
    public final int hashCode() {
        return Arrays.hashCode(this.zzgtn);
    }

    @Override
    public final String toString() {
        return "[PoolKey#" + this.hashCode() + " " + Arrays.toString(this.zzgtn) + "]";
    }

    private static String zzq(@Nullable Bundle bundle0) {
        String s;
        if(bundle0 == null) {
            return null;
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: new TreeSet(bundle0.keySet())) {
            Object object1 = bundle0.get(((String)object0));
            if(object1 == null) {
                s = "null";
            }
            else {
                s = object1 instanceof Bundle ? zzdgm.zzq(((Bundle)object1)) : object1.toString();
            }
            stringBuilder0.append(s);
        }
        return stringBuilder0.toString();
    }
}

