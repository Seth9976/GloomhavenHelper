package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;

public final class zzbir implements zzeej {
    private final zzeew zzelc;

    public zzbir(zzeew zzeew0) {
        this.zzelc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzdjh)zzeep.zza(new zzdjh(((Context)this.zzelc.get()), zzq.zzlj().zzxg()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

