package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzebh extends zzebi {
    zzebh(int v) {
        super(v, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzebi
    public final void zzban() {
        if(!this.isImmutable()) {
            for(int v = 0; v < this.zzbew(); ++v) {
                Map.Entry map$Entry0 = this.zzgx(v);
                if(((zzdyr)map$Entry0.getKey()).zzbcp()) {
                    map$Entry0.setValue(Collections.unmodifiableList(((List)map$Entry0.getValue())));
                }
            }
            for(Object object0: this.zzbex()) {
                Map.Entry map$Entry1 = (Map.Entry)object0;
                if(((zzdyr)map$Entry1.getKey()).zzbcp()) {
                    map$Entry1.setValue(Collections.unmodifiableList(((List)map$Entry1.getValue())));
                }
            }
        }
        super.zzban();
    }
}

