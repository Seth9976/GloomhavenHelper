package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.Map;

public final class zzbnj implements zzbng {
    private final Map zzfhz;

    zzbnj(Map map0) {
        this.zzfhz = map0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbng
    @Nullable
    public final zzcly zzd(int v, String s) {
        return (zzcly)this.zzfhz.get(s);
    }
}

