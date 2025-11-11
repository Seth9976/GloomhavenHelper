package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Executor;

public final class zzckx implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfgm;
    private final zzeew zzfzn;
    private final zzeew zzfzo;
    private final zzeew zzfzp;
    private final zzeew zzfzq;

    private zzckx(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzelc = zzeew0;
        this.zzfgm = zzeew1;
        this.zzfzn = zzeew2;
        this.zzfzo = zzeew3;
        this.zzfzp = zzeew4;
        this.zzfzq = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzckh(((Context)this.zzelc.get()), ((Executor)this.zzfgm.get()), ((zzaro)this.zzfzn.get()), ((zzbjh)this.zzfzo.get()), ((zzarl)this.zzfzp.get()), ((HashMap)this.zzfzq.get()));
    }

    public static zzckx zzc(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        return new zzckx(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5);
    }
}

