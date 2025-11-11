package com.google.android.gms.internal.ads;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzagc {
    private final View view;
    private final zzdq zzbma;
    private final Context zzur;

    public zzagc(Context context0, zzdq zzdq0, View view0) {
        this.zzur = context0;
        this.zzbma = zzdq0;
        this.view = view0;
    }

    private static Intent zza(Intent intent0, ResolveInfo resolveInfo0) {
        Intent intent1 = new Intent(intent0);
        intent1.setClassName(resolveInfo0.activityInfo.packageName, resolveInfo0.activityInfo.name);
        return intent1;
    }

    @VisibleForTesting
    private final ResolveInfo zza(Intent intent0, ArrayList arrayList0) {
        ResolveInfo resolveInfo0 = null;
        try {
            PackageManager packageManager0 = this.zzur.getPackageManager();
            if(packageManager0 == null) {
                return null;
            }
            List list0 = packageManager0.queryIntentActivities(intent0, 0x10000);
            ResolveInfo resolveInfo1 = packageManager0.resolveActivity(intent0, 0x10000);
            if(list0 != null && resolveInfo1 != null) {
                for(int v = 0; v < list0.size(); ++v) {
                    ResolveInfo resolveInfo2 = (ResolveInfo)list0.get(v);
                    if(resolveInfo1 != null && resolveInfo1.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo0 = resolveInfo1;
                        break;
                    }
                }
            }
            arrayList0.addAll(list0);
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zza(throwable0, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
        }
        return resolveInfo0;
    }

    @VisibleForTesting
    private final ResolveInfo zzb(Intent intent0) {
        return this.zza(intent0, new ArrayList());
    }

    @VisibleForTesting
    public final Intent zzc(Map map0) {
        ActivityManager activityManager0 = (ActivityManager)this.zzur.getSystemService("activity");
        String s = (String)map0.get("u");
        Uri uri0 = null;
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        Uri uri1 = Uri.parse(s);
        Uri uri2 = zzagd.zzf(zzagd.zza(this.zzur, this.zzbma, uri1, this.view, null));
        boolean z = Boolean.parseBoolean(((String)map0.get("use_first_package")));
        boolean z1 = Boolean.parseBoolean(((String)map0.get("use_running_process")));
        boolean z2 = Boolean.parseBoolean(((String)map0.get("use_custom_tabs"))) || ((Boolean)zzvh.zzpd().zzd(zzzx.zzcog)).booleanValue();
        if("http".equalsIgnoreCase(uri2.getScheme())) {
            uri0 = uri2.buildUpon().scheme("https").build();
        }
        else if("https".equalsIgnoreCase(uri2.getScheme())) {
            uri0 = uri2.buildUpon().scheme("http").build();
        }
        ArrayList arrayList0 = new ArrayList();
        Intent intent0 = zzagc.zze(uri2);
        Intent intent1 = zzagc.zze(uri0);
        if(z2) {
            zzawo.zzb(this.zzur, intent0);
            zzawo.zzb(this.zzur, intent1);
        }
        ResolveInfo resolveInfo0 = this.zza(intent0, arrayList0);
        if(resolveInfo0 != null) {
            return zzagc.zza(intent0, resolveInfo0);
        }
        if(intent1 != null) {
            ResolveInfo resolveInfo1 = this.zzb(intent1);
            if(resolveInfo1 != null) {
                Intent intent2 = zzagc.zza(intent0, resolveInfo1);
                if(this.zzb(intent2) != null) {
                    return intent2;
                }
            }
        }
        if(arrayList0.size() == 0) {
            return intent0;
        }
        if(z1 && activityManager0 != null) {
            List list0 = activityManager0.getRunningAppProcesses();
            if(list0 != null) {
                int v = arrayList0.size();
                int v1 = 0;
                while(v1 < v) {
                    Object object0 = arrayList0.get(v1);
                    ++v1;
                    ResolveInfo resolveInfo2 = (ResolveInfo)object0;
                    for(Object object1: list0) {
                        if(((ActivityManager.RunningAppProcessInfo)object1).processName.equals(resolveInfo2.activityInfo.packageName)) {
                            return zzagc.zza(intent0, resolveInfo2);
                        }
                        if(false) {
                            break;
                        }
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return z ? zzagc.zza(intent0, ((ResolveInfo)arrayList0.get(0))) : intent0;
    }

    private static Intent zze(Uri uri0) {
        if(uri0 == null) {
            return null;
        }
        Intent intent0 = new Intent("android.intent.action.VIEW");
        intent0.addFlags(0x10000000);
        intent0.setData(uri0);
        intent0.setAction("android.intent.action.VIEW");
        return intent0;
    }
}

