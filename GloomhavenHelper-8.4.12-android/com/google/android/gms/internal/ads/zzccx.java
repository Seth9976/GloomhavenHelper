package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzccx implements zzdng {
    private final String zzczs;
    private final zzccv zzfti;
    private final JSONObject zzftm;

    zzccx(zzccv zzccv0, String s, JSONObject jSONObject0) {
        this.zzfti = zzccv0;
        this.zzczs = s;
        this.zzftm = jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return this.zzfti.zza(this.zzczs, this.zzftm, ((zzbdv)object0));
    }
}

