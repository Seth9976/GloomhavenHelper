package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONObject;

public final class zzcfb {
    public static String zza(JSONObject jSONObject0, String s, String s1) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcli)).booleanValue()) {
            return "";
        }
        if(jSONObject0 == null) {
            return "";
        }
        JSONArray jSONArray0 = jSONObject0.optJSONArray(s1);
        if(jSONArray0 == null) {
            return "";
        }
        for(int v = 0; v < jSONArray0.length(); ++v) {
            JSONObject jSONObject1 = jSONArray0.optJSONObject(v);
            if(jSONObject1 != null) {
                JSONArray jSONArray1 = jSONObject1.optJSONArray("including");
                JSONArray jSONArray2 = jSONObject1.optJSONArray("excluding");
                if(zzcfb.zza(jSONArray1, s) && !zzcfb.zza(jSONArray2, s)) {
                    return jSONObject1.optString("effective_ad_unit_id", "");
                }
            }
        }
        return "";
    }

    private static boolean zza(JSONArray jSONArray0, String s) {
        if(jSONArray0 != null && s != null) {
            for(int v = 0; v < jSONArray0.length(); ++v) {
                String s1 = jSONArray0.optString(v);
                try {
                    if(Pattern.compile(s1).matcher(s).lookingAt()) {
                        return true;
                    }
                }
                catch(PatternSyntaxException patternSyntaxException0) {
                    zzq.zzkz().zza(patternSyntaxException0, "RtbAdapterMap.hasAtleastOneRegexMatch");
                }
            }
            return false;
        }
        return false;
    }
}

