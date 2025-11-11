package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcyp implements zzcyb {
    private final Info zzgkz;
    private final String zzgla;

    public zzcyp(Info advertisingIdClient$Info0, String s) {
        this.zzgkz = advertisingIdClient$Info0;
        this.zzgla = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        try {
            JSONObject jSONObject1 = zzayf.zzb(jSONObject0, "pii");
            if(this.zzgkz != null && !TextUtils.isEmpty(this.zzgkz.getId())) {
                jSONObject1.put("rdid", this.zzgkz.getId());
                jSONObject1.put("is_lat", this.zzgkz.isLimitAdTrackingEnabled());
                jSONObject1.put("idtype", "adid");
                return;
            }
            jSONObject1.put("pdid", this.zzgla);
            jSONObject1.put("pdidtype", "ssaid");
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Failed putting Ad ID.", jSONException0);
        }
    }
}

