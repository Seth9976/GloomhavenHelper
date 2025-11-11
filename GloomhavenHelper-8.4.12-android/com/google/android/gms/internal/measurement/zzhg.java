package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzhg extends zzhh {
    zzhg(int v) {
        super(v, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhh
    public final void zza() {
        if(!this.zzb()) {
            for(int v = 0; v < this.zzc(); ++v) {
                Map.Entry map$Entry0 = this.zzb(v);
                if(((zzev)map$Entry0.getKey()).zzd()) {
                    map$Entry0.setValue(Collections.unmodifiableList(((List)map$Entry0.getValue())));
                }
            }
            for(Object object0: this.zzd()) {
                Map.Entry map$Entry1 = (Map.Entry)object0;
                if(((zzev)map$Entry1.getKey()).zzd()) {
                    map$Entry1.setValue(Collections.unmodifiableList(((List)map$Entry1.getValue())));
                }
            }
        }
        super.zza();
    }
}

