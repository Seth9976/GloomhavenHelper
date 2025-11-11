package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

public final class zzcaz implements zzeej {
    private final zzeew zzeus;
    private final zzeew zzfev;
    private final zzeew zzfex;

    public zzcaz(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzeus = zzeew0;
        this.zzfex = zzeew1;
        this.zzfev = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcax(((zzaxx)this.zzeus.get()), ((Clock)this.zzfex.get()), ((Executor)this.zzfev.get()));
    }
}

