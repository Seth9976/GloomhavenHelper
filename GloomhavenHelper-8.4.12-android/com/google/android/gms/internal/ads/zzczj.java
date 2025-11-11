package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzczj implements zzcyb {
    private JSONObject zzglm;

    public zzczj(JSONObject jSONObject0) {
        this.zzglm = jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            jSONObject0.put("cache_state", this.zzglm);
        }
        catch(JSONException unused_ex) {
            zzawf.zzee("Unable to get cache_state");
        }
    }
}

