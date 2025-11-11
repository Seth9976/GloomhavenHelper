package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

public final class zzcgz implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeuf;
    private final zzeew zzfev;

    public zzcgz(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfev = zzeew0;
        this.zzeuf = zzeew1;
        this.zzelc = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgx(((Executor)this.zzfev.get()), ((zzazl)this.zzeuf.get()), ((Context)this.zzelc.get()));
    }
}

