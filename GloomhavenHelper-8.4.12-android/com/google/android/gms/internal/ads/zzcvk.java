package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzcvk implements zzeej {
    private final zzeew zzfex;

    public zzcvk(zzeew zzeew0) {
        this.zzfex = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzdeg)zzeep.zza(new zzdeg(((Clock)this.zzfex.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

