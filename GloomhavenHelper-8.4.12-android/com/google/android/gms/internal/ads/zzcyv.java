package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzcyv implements zzcyb {
    private final String zzgld;

    public zzcyv(String s) {
        this.zzgld = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            jSONObject0.put("ms", this.zzgld);
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Failed putting Ad ID.", jSONException0);
        }
    }
}

