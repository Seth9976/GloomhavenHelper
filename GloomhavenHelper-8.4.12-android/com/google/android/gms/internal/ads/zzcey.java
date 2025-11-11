package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

public final class zzcey implements zzeej {
    private final zzeew zzfev;

    public zzcey(zzeew zzeew0) {
        this.zzfev = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzceu(((Executor)this.zzfev.get()));
    }
}

