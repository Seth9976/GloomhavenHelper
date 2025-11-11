package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzczs implements zzcyb {
    private String zzglt;
    private String zzglu;

    public zzczs(String s, String s1) {
        this.zzglt = s;
        this.zzglu = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            JSONObject jSONObject1 = zzayf.zzb(jSONObject0, "pii");
            jSONObject1.put("doritos", this.zzglt);
            jSONObject1.put("doritos_v2", this.zzglu);
        }
        catch(JSONException unused_ex) {
            zzawf.zzee("Failed putting doritos string.");
        }
    }
}

