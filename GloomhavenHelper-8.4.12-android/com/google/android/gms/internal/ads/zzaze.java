package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final class zzaze implements zzazi {
    private final String zzczs;
    private final String zzczz;
    private final Map zzdac;
    private final byte[] zzdxd;

    zzaze(String s, String s1, Map map0, byte[] arr_b) {
        this.zzczz = s;
        this.zzczs = s1;
        this.zzdac = map0;
        this.zzdxd = arr_b;
    }

    @Override  // com.google.android.gms.internal.ads.zzazi
    public final void zzb(JsonWriter jsonWriter0) {
        zzazb.zza(this.zzczz, this.zzczs, this.zzdac, this.zzdxd, jsonWriter0);
    }
}

