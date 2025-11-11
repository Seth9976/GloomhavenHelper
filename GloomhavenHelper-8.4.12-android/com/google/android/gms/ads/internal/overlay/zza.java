package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzzx;

public final class zza {
    private static boolean zza(Context context0, Intent intent0, zzv zzv0) {
        try {
            String s = intent0.toURI();
            zzawf.zzee((s.length() == 0 ? new String("Launching an intent: ") : "Launching an intent: " + s));
            zzawo.zza(context0, intent0);
            if(zzv0 != null) {
                zzv0.zzub();
            }
            return true;
        }
        catch(ActivityNotFoundException activityNotFoundException0) {
            zzawf.zzfa(activityNotFoundException0.getMessage());
            return false;
        }
    }

    public static boolean zza(Context context0, zzb zzb0, zzv zzv0) {
        int v = 0;
        if(zzb0 == null) {
            zzawf.zzfa("No intent data for launcher overlay.");
            return false;
        }
        zzzx.initialize(context0);
        if(zzb0.intent != null) {
            return zza.zza(context0, zzb0.intent, zzv0);
        }
        Intent intent0 = new Intent();
        if(TextUtils.isEmpty(zzb0.url)) {
            zzawf.zzfa("Open GMSG did not contain a URL.");
            return false;
        }
        if(TextUtils.isEmpty(zzb0.mimeType)) {
            intent0.setData(Uri.parse(zzb0.url));
        }
        else {
            intent0.setDataAndType(Uri.parse(zzb0.url), zzb0.mimeType);
        }
        intent0.setAction("android.intent.action.VIEW");
        if(!TextUtils.isEmpty(zzb0.packageName)) {
            intent0.setPackage(zzb0.packageName);
        }
        if(!TextUtils.isEmpty(zzb0.zzdhq)) {
            String[] arr_s = zzb0.zzdhq.split("/", 2);
            if(arr_s.length < 2) {
                String s = String.valueOf(zzb0.zzdhq);
                zzawf.zzfa((s.length() == 0 ? new String("Could not parse component name from open GMSG: ") : "Could not parse component name from open GMSG: " + s));
                return false;
            }
            intent0.setClassName(arr_s[0], arr_s[1]);
        }
        String s1 = zzb0.zzdhr;
        if(!TextUtils.isEmpty(s1)) {
            try {
                v = Integer.parseInt(s1);
            }
            catch(NumberFormatException unused_ex) {
                zzawf.zzfa("Could not parse intent flags.");
            }
            intent0.addFlags(v);
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoh)).booleanValue()) {
            intent0.addFlags(0x10000000);
            intent0.putExtra("android.support.customtabs.extra.user_opt_out", true);
            return zza.zza(context0, intent0, zzv0);
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcog)).booleanValue()) {
            zzawo.zzb(context0, intent0);
        }
        return zza.zza(context0, intent0, zzv0);
    }
}

