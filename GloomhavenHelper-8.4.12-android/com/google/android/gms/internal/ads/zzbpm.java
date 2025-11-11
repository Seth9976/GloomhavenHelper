package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.util.List;

public final class zzbpm {
    private final String packageName;
    private final zzazo zzblr;
    private final PackageInfo zzdju;
    private final List zzdke;
    private final String zzdnb;
    private final zzawh zzdsq;
    private final zzdif zzfis;
    private final ApplicationInfo zzfjn;
    private final zzeed zzfjo;
    private final zzcyd zzfjp;

    public zzbpm(zzdif zzdif0, zzazo zzazo0, ApplicationInfo applicationInfo0, String s, List list0, @Nullable PackageInfo packageInfo0, zzeed zzeed0, zzawh zzawh0, String s1, zzcyd zzcyd0) {
        this.zzfis = zzdif0;
        this.zzblr = zzazo0;
        this.zzfjn = applicationInfo0;
        this.packageName = s;
        this.zzdke = list0;
        this.zzdju = packageInfo0;
        this.zzfjo = zzeed0;
        this.zzdsq = zzawh0;
        this.zzdnb = s1;
        this.zzfjp = zzcyd0;
    }

    public final zzdof zzahs() {
        zzdhv zzdhv0 = this.zzfis.zzu(zzdig.zzgve);
        Bundle bundle0 = new Bundle();
        return zzdhv0.zze(this.zzfjp.zzt(bundle0)).zzata();
    }

    public final zzdof zzaht() {
        zzdof zzdof0 = this.zzahs();
        zzdof[] arr_zzdof = {zzdof0, ((zzdof)this.zzfjo.get())};
        return this.zzfis.zza(zzdig.zzgvf, arr_zzdof).zzb(() -> {
            Object object0 = zzdof0.get();
            Object object1 = ((zzdof)this.zzfjo.get()).get();
            boolean z = this.zzdsq.zzwd();
            return new zzaqx(((Bundle)object0), this.zzblr, this.zzfjn, this.packageName, this.zzdke, this.zzdju, ((String)object1), z, this.zzdnb, null, null);
        }).zzata();
    }

    // 检测为 Lambda 实现
    final zzaqx zzc(zzdof zzdof0) throws Exception [...]
}

