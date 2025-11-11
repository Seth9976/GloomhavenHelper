package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbpw implements zzeej {
    private final zzeew zzfjj;
    private final zzbpt zzfkc;

    private zzbpw(zzbpt zzbpt0, zzeew zzeew0) {
        this.zzfkc = zzbpt0;
        this.zzfjj = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzfjj.get();
        return (Context)zzeep.zza(this.zzfkc.zzcb(context0), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpw zza(zzbpt zzbpt0, zzeew zzeew0) {
        return new zzbpw(zzbpt0, zzeew0);
    }
}

