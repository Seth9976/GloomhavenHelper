package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

public final class zzcbm {
    private final Executor executor;
    private final zzcbc zzfrw;

    public zzcbm(Executor executor0, zzcbc zzcbc0) {
        this.executor = executor0;
        this.zzfrw = zzcbc0;
    }

    // This method was un-flattened
    public final zzdof zzg(JSONObject jSONObject0, String s) {
        zzdof zzdof0;
        JSONArray jSONArray0 = jSONObject0.optJSONArray(s);
        if(jSONArray0 == null) {
            return zzdnt.zzaj(Collections.emptyList());
        }
        ArrayList arrayList0 = new ArrayList();
        int v = jSONArray0.length();
        for(int v1 = 0; v1 < v; ++v1) {
            JSONObject jSONObject1 = jSONArray0.optJSONObject(v1);
            if(jSONObject1 == null) {
                zzdof0 = zzdnt.zzaj(null);
            }
            else {
                String s1 = jSONObject1.optString("name");
                if(s1 != null) {
                    String s2 = jSONObject1.optString("type");
                    if("string".equals(s2)) {
                        zzdof0 = zzdnt.zzaj(new zzcbr(s1, jSONObject1.optString("string_value")));
                    }
                    else if("image".equals(s2)) {
                        zzdof0 = zzdnt.zzb(this.zzfrw.zzc(jSONObject1, "image_value"), new zzcbo(s1), this.executor);
                    }
                }
            }
            arrayList0.add(zzdof0);
        }
        return zzdnt.zzb(zzdnt.zzg(arrayList0), zzcbp.zzdpv, this.executor);
    }
}

