package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

public final class zzcwu implements zzeej {
    private final zzeew zzfju;
    private final zzeew zzfxt;

    private zzcwu(zzeew zzeew0, zzeew zzeew1) {
        this.zzfxt = zzeew0;
        this.zzfju = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcwu.zza(((ApplicationInfo)this.zzfxt.get()), ((PackageInfo)this.zzfju.get()));
    }

    public static zzcwr zza(ApplicationInfo applicationInfo0, PackageInfo packageInfo0) {
        return new zzcwr(applicationInfo0, packageInfo0);
    }

    public static zzcwu zzaw(zzeew zzeew0, zzeew zzeew1) {
        return new zzcwu(zzeew0, zzeew1);
    }
}

