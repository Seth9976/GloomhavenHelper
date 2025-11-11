package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcmq implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzfgu;
    private final zzeew zzfrj;
    private final zzeew zzgbg;
    private final zzeew zzgbl;

    public zzcmq(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzgbg = zzeew0;
        this.zzelc = zzeew1;
        this.zzfgm = zzeew2;
        this.zzfrj = zzeew3;
        this.zzfgu = zzeew4;
        this.zzgbl = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcml(((zzbmc)this.zzgbg.get()), ((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzceb)this.zzfrj.get()), ((zzdeu)this.zzfgu.get()), ((zzdku)this.zzgbl.get()));
    }
}

