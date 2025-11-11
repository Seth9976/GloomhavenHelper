package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

public final class zzdiu implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzelt;
    private final zzeew zzfbm;
    private final zzeew zzfed;
    private final zzeew zzfev;
    private final zzeew zzfex;
    private final zzeew zzffi;
    private final zzeew zzgin;
    private final zzeew zzgio;
    private final zzeew zzgwh;

    private zzdiu(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        this.zzfev = zzeew0;
        this.zzelt = zzeew1;
        this.zzgwh = zzeew2;
        this.zzffi = zzeew3;
        this.zzgin = zzeew4;
        this.zzgio = zzeew5;
        this.zzelc = zzeew6;
        this.zzfbm = zzeew7;
        this.zzfex = zzeew8;
        this.zzfed = zzeew9;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdis(((Executor)this.zzfev.get()), ((zzazl)this.zzelt.get()), ((zzcpc)this.zzgwh.get()), ((zzazo)this.zzffi.get()), ((String)this.zzgin.get()), ((String)this.zzgio.get()), ((Context)this.zzelc.get()), ((zzdep)this.zzfbm.get()), ((Clock)this.zzfex.get()), ((zzdq)this.zzfed.get()));
    }

    public static zzdiu zzb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        return new zzdiu(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6, zzeew7, zzeew8, zzeew9);
    }
}

