package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdap implements zzcyb {
    private final Map zzgmd;

    public zzdap(Map map0) {
        this.zzgmd = map0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            jSONObject0.put("video_decoders", zzq.zzkv().zzi(this.zzgmd));
        }
        catch(JSONException jSONException0) {
            String s = jSONException0.getMessage();
            zzawf.zzee((s.length() == 0 ? new String("Could not encode video decoder properties: ") : "Could not encode video decoder properties: " + s));
        }
    }
}

