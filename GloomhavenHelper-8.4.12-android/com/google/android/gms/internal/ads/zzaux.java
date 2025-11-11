package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzaux {
    @VisibleForTesting
    private static Uri zza(String s, String s1, String s2) {
        int v = s.indexOf("&adurl");
        if(v == -1) {
            v = s.indexOf("?adurl");
        }
        return v == -1 ? Uri.parse(s).buildUpon().appendQueryParameter(s1, s2).build() : Uri.parse((s.substring(0, v + 1) + s1 + "=" + s2 + "&" + s.substring(v + 1)));
    }

    public static String zzb(Uri uri0, Context context0) {
        if(!zzq.zzlt().zzad(context0)) {
            return uri0.toString();
        }
        String s = zzq.zzlt().zzai(context0);
        if(s == null) {
            return uri0.toString();
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzciv)).booleanValue()) {
            String s1 = (String)zzvh.zzpd().zzd(zzzx.zzciw);
            String s2 = uri0.toString();
            if(s2.contains(s1)) {
                zzq.zzlt().zzh(context0, s);
                return s2.replace(s1, s);
            }
        }
        else if(TextUtils.isEmpty(uri0.getQueryParameter("fbs_aeid"))) {
            uri0 = zzaux.zza(uri0.toString(), "fbs_aeid", s);
            zzq.zzlt().zzh(context0, s);
        }
        return uri0.toString();
    }

    public static String zzb(String s, Context context0, boolean z) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjd)).booleanValue() && !z) {
            return s;
        }
        if(zzq.zzlt().zzad(context0) && !TextUtils.isEmpty(s)) {
            String s1 = zzq.zzlt().zzai(context0);
            if(s1 == null) {
                return s;
            }
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzciv)).booleanValue()) {
                String s2 = (String)zzvh.zzpd().zzd(zzzx.zzciw);
                if(s.contains(s2)) {
                    if(zzq.zzkv().zzel(s)) {
                        zzq.zzlt().zzh(context0, s1);
                        return s.replace(s2, s1);
                    }
                    if(zzq.zzkv().zzem(s)) {
                        zzq.zzlt().zzi(context0, s1);
                        return s.replace(s2, s1);
                    }
                }
            }
            else if(!s.contains("fbs_aeid")) {
                if(zzq.zzkv().zzel(s)) {
                    zzq.zzlt().zzh(context0, s1);
                    return zzaux.zza(s, "fbs_aeid", s1).toString();
                }
                if(zzq.zzkv().zzem(s)) {
                    zzq.zzlt().zzi(context0, s1);
                    return zzaux.zza(s, "fbs_aeid", s1).toString();
                }
            }
            return s;
        }
        return s;
    }
}

