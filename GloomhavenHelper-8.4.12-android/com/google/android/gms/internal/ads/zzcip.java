package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public final class zzcip implements zzeej {
    private final zzeew zzelc;

    private zzcip(zzeew zzeew0) {
        this.zzelc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcip.zzcd(((Context)this.zzelc.get()));
    }

    public static zzcip zzab(zzeew zzeew0) {
        return new zzcip(zzeew0);
    }

    public static ApplicationInfo zzcd(Context context0) {
        return (ApplicationInfo)zzeep.zza(context0.getApplicationInfo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

