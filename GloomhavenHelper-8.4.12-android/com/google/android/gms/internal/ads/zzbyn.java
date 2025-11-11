package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

public final class zzbyn implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;
    private final zzeew zzfex;
    private final zzeew zzfoc;

    public zzbyn(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfoc = zzeew0;
        this.zzfev = zzeew1;
        this.zzelc = zzeew2;
        this.zzfex = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzpo zzpo0 = (zzpo)this.zzfoc.get();
        Executor executor0 = (Executor)this.zzfev.get();
        Context context0 = (Context)this.zzelc.get();
        Clock clock0 = (Clock)this.zzfex.get();
        return (zzbkk)zzeep.zza(new zzbkk(executor0, new zzbjz(context0, zzpo0), clock0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

