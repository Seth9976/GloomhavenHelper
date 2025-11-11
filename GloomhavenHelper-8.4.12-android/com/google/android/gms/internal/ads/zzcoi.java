package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzcoi implements zzdng {
    static final zzdng zzblf;

    static {
        zzcoi.zzblf = new zzcoi();
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        if(!((JSONObject)object0).optBoolean("success")) {
            throw new zzakd("process json failed");
        }
        return zzdnt.zzaj(((JSONObject)object0).getJSONObject("json").getJSONArray("ads"));
    }
}

