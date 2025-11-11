package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Set;

public final class zzcwl implements zzcyb {
    private final String zzgjl;

    public zzcwl(String s) {
        this.zzgjl = s;
    }

    // 去混淆评级： 低(40)
    private static boolean zze(Set set0) {
        return set0.contains("rewarded") || set0.contains("interstitial") || set0.contains("native") || set0.contains("banner");
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        zzdez.zza(((Bundle)object0), "omid_v", this.zzgjl);
    }
}

