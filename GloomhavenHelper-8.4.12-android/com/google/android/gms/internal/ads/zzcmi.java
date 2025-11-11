package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcmi implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzfgu;
    private final zzeew zzfrj;
    private final zzeew zzgbg;

    public zzcmi(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzgbg = zzeew0;
        this.zzelc = zzeew1;
        this.zzfgm = zzeew2;
        this.zzfrj = zzeew3;
        this.zzfgu = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcme(((zzbku)this.zzgbg.get()), ((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzceb)this.zzfrj.get()), ((zzdeu)this.zzfgu.get()));
    }
}

