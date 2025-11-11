package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

public final class zzbuu extends zzbtk implements zzpt {
    private final zzdei zzfhg;
    @GuardedBy("this")
    private Map zzflo;
    private final Context zzur;

    public zzbuu(Context context0, Set set0, zzdei zzdei0) {
        super(set0);
        this.zzflo = new WeakHashMap(1);
        this.zzur = context0;
        this.zzfhg = zzdei0;
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        synchronized(this) {
            this.zza(new zzbux(zzpu0));
        }
    }

    public final void zzq(View view0) {
        synchronized(this) {
            zzpp zzpp0 = (zzpp)this.zzflo.get(view0);
            if(zzpp0 == null) {
                zzpp0 = new zzpp(this.zzur, view0);
                zzpp0.zza(this);
                this.zzflo.put(view0, zzpp0);
            }
            if(this.zzfhg != null && this.zzfhg.zzdmr && ((Boolean)zzvh.zzpd().zzd(zzzx.zzclc)).booleanValue()) {
                zzpp0.zzen(((long)(((Long)zzvh.zzpd().zzd(zzzx.zzclb)))));
                return;
            }
            zzpp0.zzlu();
        }
    }

    public final void zzr(View view0) {
        synchronized(this) {
            if(this.zzflo.containsKey(view0)) {
                ((zzpp)this.zzflo.get(view0)).zzb(this);
                this.zzflo.remove(view0);
            }
        }
    }
}

