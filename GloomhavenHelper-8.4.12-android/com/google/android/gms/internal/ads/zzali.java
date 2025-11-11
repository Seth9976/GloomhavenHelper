package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzali {
    public static List zza(JSONObject jSONObject0, String s) throws JSONException {
        JSONArray jSONArray0 = jSONObject0.optJSONArray(s);
        if(jSONArray0 != null) {
            ArrayList arrayList0 = new ArrayList(jSONArray0.length());
            for(int v = 0; v < jSONArray0.length(); ++v) {
                arrayList0.add(jSONArray0.getString(v));
            }
            return Collections.unmodifiableList(arrayList0);
        }
        return null;
    }
}

