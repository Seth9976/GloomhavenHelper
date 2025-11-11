package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzbmv implements zzeej {
    private final zzeew zzepn;
    private final zzeew zzfah;
    private final zzeew zzffc;
    private final zzeew zzfgm;

    public zzbmv(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzepn = zzeew0;
        this.zzffc = zzeew1;
        this.zzfah = zzeew2;
        this.zzfgm = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbmm(((zzbne)this.zzepn.get()), ((zzael)this.zzffc.get()), ((Runnable)this.zzfah.get()), ((Executor)this.zzfgm.get()));
    }
}

