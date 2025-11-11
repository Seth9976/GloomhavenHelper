package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzczw implements zzcyb {
    private List zzdke;

    public zzczw(List list0) {
        this.zzdke = list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            jSONObject0.put("eid", TextUtils.join(",", this.zzdke));
        }
        catch(JSONException unused_ex) {
            zzawf.zzee("Failed putting experiment ids.");
        }
    }
}

