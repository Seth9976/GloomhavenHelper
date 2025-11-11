package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbmb implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffv;
    private final zzbln zzfgr;

    public zzbmb(zzbln zzbln0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfgr = zzbln0;
        this.zzffv = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbna)this.zzffv.get()), ((Executor)this.zzfev.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

