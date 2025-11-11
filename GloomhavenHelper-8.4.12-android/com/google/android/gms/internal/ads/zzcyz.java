package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcyz implements zzcyb {
    private final JSONObject zzglf;

    public zzcyz(JSONObject jSONObject0) {
        this.zzglf = jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            JSONObject jSONObject1 = zzayf.zzb(jSONObject0, "content_info");
            JSONObject jSONObject2 = this.zzglf;
            Iterator iterator0 = jSONObject2.keys();
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                jSONObject1.put(((String)object1), jSONObject2.get(((String)object1)));
            }
        }
        catch(JSONException unused_ex) {
            zzawf.zzee("Failed putting app indexing json.");
        }
    }
}

