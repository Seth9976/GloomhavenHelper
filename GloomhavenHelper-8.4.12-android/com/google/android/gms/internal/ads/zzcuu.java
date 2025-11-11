package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcuu implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;
    private final zzeew zzfst;

    private zzcuu(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzfst = zzeew1;
        this.zzfev = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcuq(((Context)this.zzelc.get()), ((ScheduledExecutorService)this.zzfst.get()), ((Executor)this.zzfev.get()));
    }

    public static zzcuu zzo(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcuu(zzeew0, zzeew1, zzeew2);
    }
}

