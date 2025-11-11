package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzeev {
    private static String zzigp;

    public static String zzch(Context context0) {
        String s = zzeev.zzigp;
        if(s != null) {
            return s;
        }
        PackageManager packageManager0 = context0.getPackageManager();
        Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveInfo0 = packageManager0.resolveActivity(intent0, 0);
        String s1 = resolveInfo0 == null ? null : resolveInfo0.activityInfo.packageName;
        List list0 = packageManager0.queryIntentActivities(intent0, 0);
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            ResolveInfo resolveInfo1 = (ResolveInfo)object0;
            Intent intent1 = new Intent();
            intent1.setAction("android.support.customtabs.action.CustomTabsService");
            intent1.setPackage(resolveInfo1.activityInfo.packageName);
            if(packageManager0.resolveService(intent1, 0) != null) {
                arrayList0.add(resolveInfo1.activityInfo.packageName);
            }
        }
        if(arrayList0.isEmpty()) {
            zzeev.zzigp = null;
            return null;
        }
        if(arrayList0.size() == 1) {
            zzeev.zzigp = (String)arrayList0.get(0);
            return zzeev.zzigp;
        }
        if(!TextUtils.isEmpty(s1) && !zzeev.zzd(context0, intent0) && arrayList0.contains(s1)) {
            zzeev.zzigp = s1;
            return zzeev.zzigp;
        }
        if(arrayList0.contains("com.android.chrome")) {
            zzeev.zzigp = "com.android.chrome";
            return "com.android.chrome";
        }
        if(arrayList0.contains("com.chrome.beta")) {
            zzeev.zzigp = "com.chrome.beta";
            return "com.chrome.beta";
        }
        if(arrayList0.contains("com.chrome.dev")) {
            zzeev.zzigp = "com.chrome.dev";
            return "com.chrome.dev";
        }
        if(arrayList0.contains("com.google.android.apps.chrome")) {
            zzeev.zzigp = "com.google.android.apps.chrome";
        }
        return zzeev.zzigp;
    }

    private static boolean zzd(Context context0, Intent intent0) {
        try {
            List list0 = context0.getPackageManager().queryIntentActivities(intent0, 0x40);
            if(list0 == null || list0.size() == 0) {
                return false;
            }
            Iterator iterator0 = list0.iterator();
            while(true) {
            label_4:
                if(!iterator0.hasNext()) {
                    return false;
                }
                Object object0 = iterator0.next();
                ResolveInfo resolveInfo0 = (ResolveInfo)object0;
                IntentFilter intentFilter0 = resolveInfo0.filter;
                if(intentFilter0 != null && intentFilter0.countDataAuthorities() != 0 && intentFilter0.countDataPaths() != 0) {
                    break;
                }
            }
        }
        catch(RuntimeException unused_ex) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
            return false;
        }
        if(resolveInfo0.activityInfo != null) {
            return true;
        }
        goto label_4;
    }
}

