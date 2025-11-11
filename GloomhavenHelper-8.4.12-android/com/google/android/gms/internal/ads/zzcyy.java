package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

public final class zzcyy implements zzcye {
    private final Executor executor;
    @Nullable
    private final PackageInfo zzdju;
    private final zzavn zzglb;
    private final String zzgle;

    public zzcyy(zzavn zzavn0, Executor executor0, String s, @Nullable PackageInfo packageInfo0) {
        this.zzglb = zzavn0;
        this.executor = executor0;
        this.zzgle = s;
        this.zzdju = packageInfo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzdof zzdof0 = zzdnt.zzb(this.zzglb.zza(this.zzgle, this.zzdju), zzcyx.zzdpv, this.executor);
        zzcza zzcza0 = new zzcza(this);
        return zzdnt.zzb(zzdof0, Throwable.class, zzcza0, this.executor);
    }

    final zzdof zzf(Throwable throwable0) throws Exception {
        return zzdnt.zzaj(new zzcyv(this.zzgle));
    }
}

