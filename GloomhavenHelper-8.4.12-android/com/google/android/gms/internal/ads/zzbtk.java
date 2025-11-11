package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class zzbtk {
    @GuardedBy("this")
    private final Map zzfkw;

    protected zzbtk(Set set0) {
        this.zzfkw = new HashMap();
        this.zzb(set0);
    }

    protected final void zza(zzbtm zzbtm0) {
        synchronized(this) {
            for(Object object0: this.zzfkw.entrySet()) {
                Object object1 = ((Map.Entry)object0).getKey();
                ((Executor)((Map.Entry)object0).getValue()).execute(new zzbtj(zzbtm0, object1));
            }
        }
    }

    public final void zza(zzbuv zzbuv0) {
        synchronized(this) {
            this.zza(zzbuv0.zzflp, zzbuv0.executor);
        }
    }

    public final void zza(Object object0, Executor executor0) {
        synchronized(this) {
            this.zzfkw.put(object0, executor0);
        }
    }

    private final void zzb(Set set0) {
        synchronized(this) {
            for(Object object0: set0) {
                this.zza(((zzbuv)object0));
            }
        }
    }
}

