package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import org.json.JSONException;
import org.json.JSONObject;

final class zzcku implements zzakh {
    @Override  // com.google.android.gms.internal.ads.zzakh
    public final JSONObject zzj(Object object0) throws JSONException {
        JSONObject jSONObject0 = new JSONObject();
        JSONObject jSONObject1 = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject1.put("base_url", ((zzckv)object0).zzfzh.zzug());
        jSONObject1.put("signals", ((zzckv)object0).zzfzi);
        jSONObject2.put("body", ((zzckv)object0).zzfzk.zzdln);
        jSONObject2.put("headers", zzq.zzkv().zzi(((zzckv)object0).zzfzk.zzab));
        jSONObject2.put("response_code", ((zzckv)object0).zzfzk.zzfzr);
        jSONObject2.put("latency", ((zzckv)object0).zzfzk.zzfzs);
        jSONObject0.put("request", jSONObject1);
        jSONObject0.put("response", jSONObject2);
        jSONObject0.put("flags", ((zzckv)object0).zzfzh.zzuj());
        return jSONObject0;
    }
}

