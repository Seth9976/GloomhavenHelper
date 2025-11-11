package com.google.android.gms.internal.ads;

import java.util.List;
import org.json.JSONObject;

final class zzcbh implements zzdku {
    private final JSONObject zzfej;
    private final zzcbc zzfso;

    zzcbh(zzcbc zzcbc0, JSONObject jSONObject0) {
        this.zzfso = zzcbc0;
        this.zzfej = jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        return this.zzfso.zza(this.zzfej, ((List)object0));
    }
}

