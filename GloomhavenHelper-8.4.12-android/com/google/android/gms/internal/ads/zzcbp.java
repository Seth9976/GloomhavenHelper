package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

final class zzcbp implements zzdku {
    static final zzdku zzdpv;

    static {
        zzcbp.zzdpv = new zzcbp();
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object1: ((List)object0)) {
            zzcbr zzcbr0 = (zzcbr)object1;
            if(zzcbr0 != null) {
                arrayList0.add(zzcbr0);
            }
        }
        return arrayList0;
    }
}

