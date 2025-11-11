package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import java.util.List;

public final class zzdaf {
    private final zzaqx zzfyj;
    private final int zzgll;

    public zzdaf(zzaqx zzaqx0, int v) {
        this.zzfyj = zzaqx0;
        this.zzgll = v;
    }

    public final String zzapv() {
        return this.zzfyj.packageName;
    }

    public final String zzapw() {
        return this.zzfyj.zzdmz.getString("ms");
    }

    @Nullable
    public final PackageInfo zzapx() {
        return this.zzfyj.zzdju;
    }

    public final boolean zzapy() {
        return this.zzfyj.zzdna;
    }

    public final List zzapz() {
        return this.zzfyj.zzdke;
    }

    public final ApplicationInfo zzaqa() {
        return this.zzfyj.applicationInfo;
    }

    public final String zzaqb() {
        return this.zzfyj.zzdnb;
    }

    public final int zzaqc() {
        return this.zzgll;
    }
}

