package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaau {
    public static boolean zzl(Context context0) {
        PackageManager packageManager0 = context0.getPackageManager();
        if(packageManager0 == null) {
            return false;
        }
        Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveInfo0 = packageManager0.resolveActivity(intent0, 0);
        List list0 = packageManager0.queryIntentActivities(intent0, 0x10000);
        if(list0 != null && resolveInfo0 != null) {
            for(int v = 0; v < list0.size(); ++v) {
                ResolveInfo resolveInfo1 = (ResolveInfo)list0.get(v);
                if(resolveInfo0.activityInfo.name.equals(resolveInfo1.activityInfo.name)) {
                    return resolveInfo0.activityInfo.packageName.equals(zzeev.zzch(context0));
                }
            }
        }
        return false;
    }
}

