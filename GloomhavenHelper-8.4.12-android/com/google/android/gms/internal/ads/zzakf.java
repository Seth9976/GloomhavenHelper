package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@ParametersAreNonnullByDefault
public final class zzakf {
    private final Object zzdbp;
    private final Object zzdbq;
    @GuardedBy("lockClient")
    private zzakk zzdbr;
    @GuardedBy("lockService")
    private zzakk zzdbs;

    public zzakf() {
        this.zzdbp = new Object();
        this.zzdbq = new Object();
    }

    public final zzakk zza(Context context0, zzazo zzazo0) {
        synchronized(this.zzdbq) {
            if(this.zzdbs == null) {
                this.zzdbs = new zzakk(zzakf.zzm(context0), zzazo0, ((String)zzabr.zzcvs.get()));
            }
            return this.zzdbs;
        }
    }

    public final zzakk zzb(Context context0, zzazo zzazo0) {
        synchronized(this.zzdbp) {
            if(this.zzdbr == null) {
                this.zzdbr = new zzakk(zzakf.zzm(context0), zzazo0, ((String)zzvh.zzpd().zzd(zzzx.zzcha)));
            }
            return this.zzdbr;
        }
    }

    private static Context zzm(Context context0) {
        Context context1 = context0.getApplicationContext();
        return context1 == null ? context0 : context1;
    }
}

