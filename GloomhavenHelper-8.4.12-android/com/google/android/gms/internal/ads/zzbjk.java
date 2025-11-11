package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbjk implements zzbjj {
    private zzawh zzdsq;

    public zzbjk(zzawh zzawh0) {
        this.zzdsq = zzawh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbjj
    public final void zzk(Map map0) {
        this.zzdsq.zzap(Boolean.parseBoolean(((String)map0.get("content_vertical_opted_out"))));
    }
}

