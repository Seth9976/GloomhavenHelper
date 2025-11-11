package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.util.Iterator;

final class zzeo {
    static zzdpf zzyl;

    // 去混淆评级： 低(24)
    static boolean zzb(zzei zzei0) throws IllegalAccessException, InvocationTargetException {
        byte[] arr_b;
        if(zzeo.zzyl != null) {
            return true;
        }
        String s = (String)zzvh.zzpd().zzd(zzzx.zzcmc);
        if(s == null || s.length() == 0) {
            if(zzei0 == null) {
                s = null;
            }
            else {
                Method method0 = zzei0.zza("YmR6FQNezGZtzakNCdp/X8yOsex9KR4dfN+xewlK0xIWqlIYp672W4ywsMH2gHbP", "NklASs7aYWmFe9YJqrin6meKy0Kr6kcwDcOgqDcpN3U=");
                s = method0 == null ? null : ((String)method0.invoke(null));
            }
            if(s == null) {
                return false;
            }
        }
        try {
            arr_b = zzci.zza(s, true);
        }
        catch(IllegalArgumentException unused_ex) {
            return false;
        }
        try {
            zzdpk zzdpk0 = zzdpo.zzk(arr_b);
            Iterator iterator0 = zzdrh.zzhfe.zzazt().iterator();
            if(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                zzduj zzduj0 = (zzduj)object0;
                return false;
            }
            zzeo.zzyl = zzdrl.zza(zzdpk0, null);
            return zzeo.zzyl != null;
        }
        catch(GeneralSecurityException unused_ex) {
            return false;
        }
    }
}

