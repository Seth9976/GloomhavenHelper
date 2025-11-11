package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbhb implements zzeej {
    private final zzeew zzelc;

    public zzbhb(zzeew zzeew0) {
        this.zzelc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        return (zzarl)zzeep.zza(new zzarj(context0, new zzarq(context0).zzue()), "Cannot return null from a non-@Nullable @Provides method");
    }
}

