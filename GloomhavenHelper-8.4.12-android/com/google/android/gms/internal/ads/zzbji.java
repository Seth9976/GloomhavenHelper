package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbji implements zzbjj {
    private zzawh zzdsq;

    public zzbji(zzawh zzawh0) {
        this.zzdsq = zzawh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbjj
    public final void zzk(Map map0) {
        this.zzdsq.zzao(Boolean.parseBoolean(((String)map0.get("content_url_opted_out"))));
    }
}

