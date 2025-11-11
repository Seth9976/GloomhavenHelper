package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzafv implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s = (String)map0.get("action");
        if("pause".equals(s)) {
            ((zzbdv)object0).zzka();
            return;
        }
        if("resume".equals(s)) {
            ((zzbdv)object0).zzkb();
        }
    }
}

