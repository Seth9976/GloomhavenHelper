package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzq;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdab implements zzcyb {
    private Bundle zzgly;

    public zzdab(Bundle bundle0) {
        this.zzgly = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        JSONObject jSONObject0 = (JSONObject)object0;
        if(this.zzgly != null) {
            try {
                zzayf.zzb(zzayf.zzb(jSONObject0, "device"), "play_store").put("parental_controls", zzq.zzkv().zzd(this.zzgly));
            }
            catch(JSONException unused_ex) {
                zzawf.zzee("Failed putting parental controls bundle.");
            }
        }
    }
}

