package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzdni extends zzdnf {
    zzdni(zzdlq zzdlq0, boolean z) {
        super(zzdlq0, true);
        this.zzaum();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnf
    public final Object zzi(List list0) {
        ArrayList arrayList0 = zzdlz.zzdz(list0.size());
        for(Object object0: list0) {
            zzdla zzdla0 = (zzdla)object0;
            arrayList0.add((zzdla0 == null ? null : zzdla0.zzats()));
        }
        return Collections.unmodifiableList(arrayList0);
    }
}

