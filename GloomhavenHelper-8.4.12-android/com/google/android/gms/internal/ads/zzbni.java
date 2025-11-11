package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbni implements zzeej {
    private final zzeew zzfhy;

    private zzbni(zzeew zzeew0) {
        this.zzfhy = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbnj(((Map)this.zzfhy.get()));
    }

    public static zzbni zzd(zzeew zzeew0) {
        return new zzbni(zzeew0);
    }
}

