package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcnz implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzffi;
    private final zzeew zzfgm;
    private final zzeew zzgbg;

    public zzcnz(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzelc = zzeew0;
        this.zzffi = zzeew1;
        this.zzgbg = zzeew2;
        this.zzfgm = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcnx(((Context)this.zzelc.get()), ((zzazo)this.zzffi.get()), ((zzbwt)this.zzgbg.get()), ((Executor)this.zzfgm.get()));
    }
}

