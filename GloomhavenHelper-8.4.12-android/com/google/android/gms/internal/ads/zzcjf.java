package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzcjf implements zzdng {
    private final zzcjc zzfyf;
    private final zzub zzfyg;

    zzcjf(zzcjc zzcjc0, zzub zzub0) {
        this.zzfyf = zzcjc0;
        this.zzfyg = zzub0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        String s = (String)object0;
        JSONObject jSONObject0 = new JSONObject();
        JSONObject jSONObject1 = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("headers", new JSONObject());
        jSONObject2.put("body", this.zzfyg.zzcci);
        jSONObject1.put("base_url", "");
        jSONObject1.put("signals", new JSONObject(this.zzfyg.zzccj));
        jSONObject0.put("request", jSONObject1);
        jSONObject0.put("response", jSONObject2);
        jSONObject0.put("flags", new JSONObject());
        return zzdnt.zzaj(jSONObject0);
    }
}

