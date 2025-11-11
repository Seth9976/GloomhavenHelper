package com.google.android.gms.internal.ads;

import android.location.Location;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzczx implements zzcyb {
    private final Location zzmk;

    public zzczx(Location location0) {
        this.zzmk = location0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            if(this.zzmk != null) {
                JSONObject jSONObject1 = new JSONObject();
                Float float0 = (float)(this.zzmk.getAccuracy() * 1000.0f);
                Long long0 = (long)(this.zzmk.getTime() * 1000L);
                Long long1 = (long)(this.zzmk.getLatitude() * 10000000.0);
                Long long2 = (long)(this.zzmk.getLongitude() * 10000000.0);
                jSONObject1.put("radius", float0);
                jSONObject1.put("lat", long1);
                jSONObject1.put("long", long2);
                jSONObject1.put("time", long0);
                jSONObject0.put("uule", jSONObject1);
            }
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Failed adding location to the request JSON.", jSONException0);
        }
    }
}

