package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcce implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzfgu;
    private final zzeew zzfrj;

    public zzcce(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzelc = zzeew0;
        this.zzfgu = zzeew1;
        this.zzfgm = zzeew2;
        this.zzfrj = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcbt(((Context)this.zzelc.get()), ((zzdeu)this.zzfgu.get()), ((Executor)this.zzfgm.get()), ((zzceb)this.zzfrj.get()));
    }
}

