package com.google.android.gms.internal.ads;

import android.view.View;

public final class zzblr implements zzeej {
    private final zzbln zzfgr;

    public zzblr(zzbln zzbln0) {
        this.zzfgr = zzbln0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzblr.zzb(this.zzfgr);
    }

    public static View zzb(zzbln zzbln0) {
        return (View)zzeep.zza(zzbln0.zzagm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

