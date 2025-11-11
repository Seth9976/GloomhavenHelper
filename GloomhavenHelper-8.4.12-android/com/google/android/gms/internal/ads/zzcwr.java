package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;

public final class zzcwr implements zzcyb, zzcye {
    private final ApplicationInfo applicationInfo;
    private final PackageInfo zzdju;

    zzcwr(ApplicationInfo applicationInfo0, @Nullable PackageInfo packageInfo0) {
        this.applicationInfo = applicationInfo0;
        this.zzdju = packageInfo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return zzdnt.zzaj(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        Integer integer0 = this.zzdju == null ? null : this.zzdju.versionCode;
        ((Bundle)object0).putString("pn", this.applicationInfo.packageName);
        if(integer0 != null) {
            ((Bundle)object0).putInt("vc", ((int)integer0));
        }
    }
}

