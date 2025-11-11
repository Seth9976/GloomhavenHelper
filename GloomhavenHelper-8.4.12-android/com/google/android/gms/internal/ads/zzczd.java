package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzczd implements zzcyb {
    private String zzglj;

    public zzczd(String s) {
        this.zzglj = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            JSONObject jSONObject1 = zzayf.zzb(jSONObject0, "pii");
            if(!TextUtils.isEmpty(this.zzglj)) {
                jSONObject1.put("attok", this.zzglj);
            }
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Failed putting attestation token.", jSONException0);
        }
    }
}

