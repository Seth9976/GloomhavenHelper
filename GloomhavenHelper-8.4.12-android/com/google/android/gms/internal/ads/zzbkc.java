package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

public final class zzbkc implements zzeej {
    private final zzeew zzfet;
    private final zzeew zzfeu;
    private final zzeew zzfev;
    private final zzeew zzfew;
    private final zzeew zzfex;

    private zzbkc(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzfet = zzeew0;
        this.zzfeu = zzeew1;
        this.zzfev = zzeew2;
        this.zzfew = zzeew3;
        this.zzfex = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbkb(((zzakt)this.zzfet.get()), ((zzbjz)this.zzfeu.get()), ((Executor)this.zzfev.get()), ((zzbjs)this.zzfew.get()), ((Clock)this.zzfex.get()));
    }

    public static zzbkc zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzbkc(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

