package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public final class zzcgd implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfev;
    private final zzeew zzfvk;
    private final zzeew zzfvl;

    public zzcgd(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfvk = zzeew0;
        this.zzelc = zzeew1;
        this.zzfev = zzeew2;
        this.zzfvl = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        String s = (String)this.zzfvk.get();
        Context context0 = (Context)this.zzelc.get();
        Executor executor0 = (Executor)this.zzfev.get();
        Map map0 = (Map)this.zzfvl.get();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcot)).booleanValue()) {
            zzsn zzsn0 = new zzsn(new zzss(context0));
            zzsn0.zza(new zzcgf(s));
            return (Set)zzeep.zza(Collections.singleton(new zzbuv(new zzcgc(zzsn0, map0), executor0)), "Cannot return null from a non-@Nullable @Provides method");
        }
        return (Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

