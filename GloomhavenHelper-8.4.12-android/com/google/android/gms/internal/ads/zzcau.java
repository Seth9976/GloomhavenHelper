package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcau implements zzeej {
    private final zzeew zzffc;
    private final zzeew zzfgm;
    private final zzcao zzfrq;

    public zzcau(zzcao zzcao0, zzeew zzeew0, zzeew zzeew1) {
        this.zzfrq = zzcao0;
        this.zzffc = zzeew0;
        this.zzfgm = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzcdj)this.zzffc.get()), ((Executor)this.zzfgm.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

