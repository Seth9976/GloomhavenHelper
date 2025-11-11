package com.google.android.gms.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzb {
    public static AdSize zza(int v, int v1) {
        AdSize adSize0 = new AdSize(v, 0);
        adSize0.zzc(true);
        adSize0.zzl(v1);
        return adSize0;
    }

    public static AdSize zza(int v, int v1, String s) {
        return new AdSize(v, v1, s);
    }

    public static boolean zza(AdSize adSize0) {
        return adSize0.zzdm();
    }

    public static boolean zzb(AdSize adSize0) {
        return adSize0.zzdn();
    }

    public static int zzc(AdSize adSize0) {
        return adSize0.zzdo();
    }
}

