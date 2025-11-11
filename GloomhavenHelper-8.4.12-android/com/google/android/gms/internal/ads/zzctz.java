package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Set;

final class zzctz implements zzcye {
    private final Set zzgib;

    zzctz(Set set0) {
        this.zzgib = set0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.zzgib) {
            arrayList0.add(((String)object0));
        }
        return zzdnt.zzaj(new zzcuc(arrayList0));
    }
}

