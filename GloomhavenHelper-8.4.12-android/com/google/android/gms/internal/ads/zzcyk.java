package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.LocaleList;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class zzcyk implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcyk(zzdoe zzdoe0, Context context0) {
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    private static ResolveInfo zza(PackageManager packageManager0, String s) {
        return packageManager0.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)), 0x10000);
    }

    private static String zza(Context context0, PackageManager packageManager0) {
        ResolveInfo resolveInfo0 = zzcyk.zza(packageManager0, "market://details?id=com.google.android.gms.ads");
        if(resolveInfo0 == null) {
            return null;
        }
        ActivityInfo activityInfo0 = resolveInfo0.activityInfo;
        if(activityInfo0 == null) {
            return null;
        }
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo(activityInfo0.packageName, 0);
            return packageInfo0 == null ? null : packageInfo0.versionCode + "." + activityInfo0.packageName;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcyj zzcyj0 = () -> {
            PackageManager packageManager0 = this.zzur.getPackageManager();
            Locale locale0 = Locale.getDefault();
            boolean z = zzcyk.zza(packageManager0, "geo:0,0?q=donuts") != null;
            boolean z1 = zzcyk.zza(packageManager0, "http://www.google.com") != null;
            String s = locale0.getCountry();
            boolean z2 = Build.DEVICE.startsWith("generic");
            boolean z3 = DeviceProperties.isLatchsky(this.zzur);
            boolean z4 = DeviceProperties.isSidewinder(this.zzur);
            String s1 = locale0.getLanguage();
            ArrayList arrayList0 = new ArrayList();
            if(Build.VERSION.SDK_INT >= 24) {
                LocaleList localeList0 = LocaleList.getDefault();
                for(int v = 0; v < localeList0.size(); ++v) {
                    arrayList0.add(localeList0.get(v).getLanguage());
                }
            }
            String s2 = zzcyk.zza(this.zzur, packageManager0);
            String s3 = zzcyk.zzy(this.zzur);
            Context context0 = this.zzur;
            if(packageManager0 != null) {
                Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
                ResolveInfo resolveInfo0 = packageManager0.resolveActivity(intent0, 0);
                List list0 = packageManager0.queryIntentActivities(intent0, 0x10000);
                if(list0 != null && resolveInfo0 != null) {
                    for(int v1 = 0; v1 < list0.size(); ++v1) {
                        ResolveInfo resolveInfo1 = (ResolveInfo)list0.get(v1);
                        if(resolveInfo0.activityInfo.name.equals(resolveInfo1.activityInfo.name)) {
                            return new zzcyh(z, z1, s, z2, z3, z4, s1, arrayList0, s2, s3, "google/sunfish/sunfish:13/TQ2A.230405.003/9719927:user/release-keys", resolveInfo0.activityInfo.packageName.equals(zzeev.zzch(context0)), Build.MODEL, zzq.zzkx().zzwx());
                        }
                    }
                }
            }
            return new zzcyh(z, z1, s, z2, z3, z4, s1, arrayList0, s2, s3, "google/sunfish/sunfish:13/TQ2A.230405.003/9719927:user/release-keys", false, Build.MODEL, zzq.zzkx().zzwx());
        };
        return this.zzfrv.zzd(zzcyj0);
    }

    // 检测为 Lambda 实现
    final zzcyh zzapr() throws Exception [...]

    private static String zzy(Context context0) {
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo("com.android.vending", 0x80);
            return packageInfo0 == null ? null : packageInfo0.versionCode + "." + packageInfo0.packageName;
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

