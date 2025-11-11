package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcnc implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzgbg;
    private final zzeew zzgbw;

    public zzcnc(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzelc = zzeew0;
        this.zzfgm = zzeew1;
        this.zzgbg = zzeew2;
        this.zzgbw = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcnb(((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzbwt)this.zzgbg.get()), ((zzdeg)this.zzgbw.get()));
    }
}

