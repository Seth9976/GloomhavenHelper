package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzdng;
import com.google.android.gms.internal.ads.zzdnt;
import com.google.android.gms.internal.ads.zzdof;
import org.json.JSONObject;

final class zzf implements zzdng {
    static final zzdng zzblf;

    static {
        zzf.zzblf = new zzf();
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        if(((JSONObject)object0).optBoolean("isSuccessful", false)) {
            String s = ((JSONObject)object0).getString("appSettingsJson");
            zzq.zzkz().zzvk().zzeh(s);
        }
        return zzdnt.zzaj(null);
    }
}

