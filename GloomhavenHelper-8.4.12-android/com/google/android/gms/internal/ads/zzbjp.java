package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbjp implements zzbjj {
    private final zzawh zzdsq;

    public zzbjp(zzawh zzawh0) {
        this.zzdsq = zzawh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbjj
    public final void zzk(Map map0) {
        String s = (String)map0.get("key");
        String s1 = (String)map0.get("value");
        if("auto_collect_location".equals(s)) {
            this.zzdsq.zzaq(Boolean.parseBoolean(s1));
        }
    }
}

