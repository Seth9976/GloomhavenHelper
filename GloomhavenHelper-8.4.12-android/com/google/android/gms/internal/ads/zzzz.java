package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.regex.Pattern;

public final class zzzz {
    public static boolean zzcq(String s) {
        return zzzz.zzf(((String)zzvh.zzpd().zzd(zzzx.zzcpk)), s);
    }

    private static boolean zzf(String s, String s1) {
        if(s != null && s1 != null) {
            try {
                return Pattern.matches(s, s1);
            }
            catch(RuntimeException runtimeException0) {
                zzq.zzkz().zza(runtimeException0, "NonagonUtil.isPatternMatched");
                return false;
            }
        }
        return false;
    }
}

