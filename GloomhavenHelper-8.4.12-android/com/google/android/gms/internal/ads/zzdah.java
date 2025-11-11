package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;

public final class zzdah implements zzeej {
    private final zzdaf zzgmb;

    public zzdah(zzdaf zzdaf0) {
        this.zzgmb = zzdaf0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzdah.zzb(this.zzgmb);
    }

    public static ApplicationInfo zzb(zzdaf zzdaf0) {
        return (ApplicationInfo)zzeep.zza(zzdaf0.zzaqa(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

