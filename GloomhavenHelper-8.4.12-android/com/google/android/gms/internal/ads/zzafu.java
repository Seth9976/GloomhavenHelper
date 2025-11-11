package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzafu implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        if(map0.keySet().contains("start")) {
            ((zzbdv)object0).zzaaf().zzabf();
            return;
        }
        if(map0.keySet().contains("stop")) {
            ((zzbdv)object0).zzaaf().zzabg();
            return;
        }
        if(map0.keySet().contains("cancel")) {
            ((zzbdv)object0).zzaaf().zzabh();
        }
    }
}

