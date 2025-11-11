package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcpk implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzgbg;

    public zzcpk(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzfgm = zzeew1;
        this.zzgbg = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcpi(((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzcdq)this.zzgbg.get()));
    }
}

