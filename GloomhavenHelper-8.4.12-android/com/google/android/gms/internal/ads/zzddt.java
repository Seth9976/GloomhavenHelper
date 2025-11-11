package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzddt implements zzeej {
    private final zzeew zzela;
    private final zzeew zzfbm;
    private final zzeew zzfgm;
    private final zzeew zzgmp;
    private final zzeew zzgmq;
    private final zzeew zzgmr;
    private final zzeew zzgms;

    public zzddt(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6) {
        this.zzgmp = zzeew0;
        this.zzfgm = zzeew1;
        this.zzela = zzeew2;
        this.zzgmq = zzeew3;
        this.zzgmr = zzeew4;
        this.zzgms = zzeew5;
        this.zzfbm = zzeew6;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzddq(((Context)this.zzgmp.get()), ((Executor)this.zzfgm.get()), ((zzbgk)this.zzela.get()), ((zzdco)this.zzgmq.get()), ((zzdct)this.zzgmr.get()), ((zzdew)this.zzgms.get()), ((zzdep)this.zzfbm.get()));
    }
}

