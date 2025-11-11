package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.InitializationStatus;
import java.util.HashMap;
import java.util.Map;

final class zzxz implements InitializationStatus {
    private final zzxu zzcfr;

    zzxz(zzxu zzxu0) {
        this.zzcfr = zzxu0;
    }

    @Override  // com.google.android.gms.ads.initialization.InitializationStatus
    public final Map getAdapterStatusMap() {
        Map map0 = new HashMap();
        map0.put("com.google.android.gms.ads.MobileAds", new zzxy(this.zzcfr));
        return map0;
    }
}

