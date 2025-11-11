package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final class zzazd implements zzazi {
    private final int zzdxb;
    private final Map zzdxc;

    zzazd(int v, Map map0) {
        this.zzdxb = v;
        this.zzdxc = map0;
    }

    @Override  // com.google.android.gms.internal.ads.zzazi
    public final void zzb(JsonWriter jsonWriter0) {
        zzazb.zza(this.zzdxb, this.zzdxc, jsonWriter0);
    }
}

