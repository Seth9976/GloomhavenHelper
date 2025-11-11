package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzafh implements zzafz {
    private final zzafg zzcxv;

    public zzafh(zzafg zzafg0) {
        this.zzcxv = zzafg0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s = (String)map0.get("name");
        if(s == null) {
            zzawf.zzfa("App event with no name parameter.");
            return;
        }
        String s1 = (String)map0.get("info");
        this.zzcxv.onAppEvent(s, s1);
    }
}

