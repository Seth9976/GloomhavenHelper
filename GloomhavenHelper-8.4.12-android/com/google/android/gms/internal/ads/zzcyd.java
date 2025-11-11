package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;

public final class zzcyd {
    private final Executor executor;
    private final Set zzgkp;

    public zzcyd(Executor executor0, Set set0) {
        this.executor = executor0;
        this.zzgkp = set0;
    }

    public final zzdof zzt(Object object0) {
        ArrayList arrayList0 = new ArrayList(this.zzgkp.size());
        for(Object object1: this.zzgkp) {
            zzcye zzcye0 = (zzcye)object1;
            zzdof zzdof0 = zzcye0.zzapb();
            if(((Boolean)zzabi.zzcuw.get()).booleanValue()) {
                zzdof0.addListener(new zzcyg(zzcye0, zzq.zzlc().elapsedRealtime()), zzazq.zzdxp);
            }
            arrayList0.add(zzdof0);
        }
        return zzdnt.zzi(arrayList0).zza(new zzcyf(arrayList0, object0), this.executor);
    }
}

