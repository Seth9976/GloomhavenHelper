package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcop implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzgbg;

    public zzcop(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzgbg = zzeew1;
        this.zzfgm = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcom(((Context)this.zzelc.get()), ((zzbxo)this.zzgbg.get()), ((Executor)this.zzfgm.get()));
    }
}

