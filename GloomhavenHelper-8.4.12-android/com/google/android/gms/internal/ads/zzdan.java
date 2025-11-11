package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

final class zzdan implements zzcyb {
    static final zzcyb zzgkg;

    static {
        zzdan.zzgkg = new zzdan();
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            jSONObject0.getJSONObject("sdk_env").put("container_version", 12451009);
        }
        catch(JSONException unused_ex) {
        }
    }
}

