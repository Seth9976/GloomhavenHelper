package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

final class zzczn implements zzcye {
    private final JSONObject zzglq;

    zzczn(Context context0) {
        this.zzglq = zzarj.zzaa(context0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return zzdnt.zzaj(new zzczq(this));
    }

    final void zzp(JSONObject jSONObject0) {
        try {
            jSONObject0.put("gms_sdk_env", this.zzglq);
        }
        catch(JSONException unused_ex) {
            zzawf.zzee("Failed putting version constants.");
        }
    }
}

