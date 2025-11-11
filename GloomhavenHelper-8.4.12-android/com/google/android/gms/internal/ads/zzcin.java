package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public final class zzcin implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgx;

    private zzcin(zzeew zzeew0, zzeew zzeew1) {
        this.zzfgx = zzeew0;
        this.zzelc = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdif zzdif0 = (zzdif)this.zzfgx.get();
        Context context0 = (Context)this.zzelc.get();
        return (zzdof)zzeep.zza(zzdif0.zzu(zzdig.zzgvw).zzc(new zzcij(context0)).zza(1L, TimeUnit.SECONDS).zza(Exception.class, zzcii.zzfxq).zzata(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcin zzai(zzeew zzeew0, zzeew zzeew1) {
        return new zzcin(zzeew0, zzeew1);
    }
}

