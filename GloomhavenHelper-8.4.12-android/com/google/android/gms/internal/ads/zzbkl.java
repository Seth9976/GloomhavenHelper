package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public final class zzbkl implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzffc;
    private final zzeew zzffd;

    private zzbkl(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzffc = zzeew0;
        this.zzfev = zzeew1;
        this.zzffd = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbkb zzbkb0 = (zzbkb)this.zzffc.get();
        Executor executor0 = (Executor)this.zzfev.get();
        return ((JSONObject)this.zzffd.get()) == null ? ((Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method")) : ((Set)zzeep.zza(Collections.singleton(new zzbuv(zzbkb0, executor0)), "Cannot return null from a non-@Nullable @Provides method"));
    }

    public static zzbkl zze(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbkl(zzeew0, zzeew1, zzeew2);
    }
}

