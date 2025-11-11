package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzail {
    public static void zza(zzaii zzaii0, String s, String s1) {
        zzaii0.zzcz(s + "(" + s1 + ");");
    }

    public static void zza(zzaii zzaii0, String s, Map map0) {
        JSONObject jSONObject0;
        try {
            jSONObject0 = zzq.zzkv().zzi(map0);
        }
        catch(JSONException unused_ex) {
            zzawf.zzfa("Could not convert parameters to JSON.");
            return;
        }
        zzaii0.zza(s, jSONObject0);
    }

    public static void zza(zzaii zzaii0, String s, JSONObject jSONObject0) {
        if(jSONObject0 == null) {
            jSONObject0 = new JSONObject();
        }
        zzaii0.zzj(s, jSONObject0.toString());
    }

    public static void zzb(zzaii zzaii0, String s, JSONObject jSONObject0) {
        if(jSONObject0 == null) {
            jSONObject0 = new JSONObject();
        }
        String s1 = "(window.AFMA_ReceiveMessage || function() {})(\'" + s + "\'" + "," + jSONObject0.toString() + ");";
        zzawf.zzeb((s1.length() == 0 ? new String("Dispatching AFMA event: ") : "Dispatching AFMA event: " + s1));
        zzaii0.zzcz(stringBuilder0.toString());
    }
}

