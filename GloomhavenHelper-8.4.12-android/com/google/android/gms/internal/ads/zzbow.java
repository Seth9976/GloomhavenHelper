package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzc;

public final class zzbow implements zzeej {
    private final zzeew zzelc;
    private final zzbox zzfjg;
    private final zzeew zzfjh;

    private zzbow(zzbox zzbox0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfjg = zzbox0;
        this.zzelc = zzeew0;
        this.zzfjh = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzc)zzeep.zza(new zzc(((Context)this.zzelc.get()), ((zzaub)this.zzfjh.get()), null), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbow zza(zzbox zzbox0, zzeew zzeew0, zzeew zzeew1) {
        return new zzbow(zzbox0, zzeew0, zzeew1);
    }
}

