package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcyf implements Callable {
    private final Object zzdcl;
    private final List zzgjw;

    zzcyf(List list0, Object object0) {
        this.zzgjw = list0;
        this.zzdcl = object0;
    }

    @Override
    public final Object call() {
        Object object0 = this.zzdcl;
        for(Object object1: this.zzgjw) {
            zzcyb zzcyb0 = (zzcyb)((zzdof)object1).get();
            if(zzcyb0 != null) {
                zzcyb0.zzs(object0);
            }
        }
        return object0;
    }
}

