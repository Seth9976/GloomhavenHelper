package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashSet;

public final class zzbyi implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfhn;

    public zzbyi(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzfhn = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        zzdei zzdei0 = (zzdei)this.zzfhn.get();
        return (zzbuu)zzeep.zza(new zzbuu(context0, new HashSet(), zzdei0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

