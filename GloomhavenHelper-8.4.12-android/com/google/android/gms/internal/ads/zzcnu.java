package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcnu implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzffi;
    private final zzeew zzfgm;
    private final zzeew zzfgu;
    private final zzeew zzfrj;
    private final zzeew zzgbg;

    public zzcnu(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzelc = zzeew0;
        this.zzffi = zzeew1;
        this.zzfgu = zzeew2;
        this.zzfgm = zzeew3;
        this.zzgbg = zzeew4;
        this.zzfrj = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcnp(((Context)this.zzelc.get()), ((zzazo)this.zzffi.get()), ((zzdeu)this.zzfgu.get()), ((Executor)this.zzfgm.get()), ((zzbwt)this.zzgbg.get()), ((zzceb)this.zzfrj.get()));
    }
}

