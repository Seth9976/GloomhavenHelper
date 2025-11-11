package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import java.util.concurrent.Executor;

public final class zzcdg implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzfkh;
    private final zzeew zzfnx;
    private final zzeew zzfsr;
    private final zzeew zzfty;

    public zzcdg(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzelc = zzeew0;
        this.zzfgm = zzeew1;
        this.zzfnx = zzeew2;
        this.zzfkh = zzeew3;
        this.zzfsr = zzeew4;
        this.zzfty = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcdf(((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzdq)this.zzfnx.get()), ((zzazo)this.zzfkh.get()), ((zza)this.zzfsr.get()), ((zzbee)this.zzfty.get()));
    }
}

