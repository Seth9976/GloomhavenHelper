package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.util.JsonReader;
import com.google.android.gms.ads.internal.zzq;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import org.json.JSONException;

public final class zzctj {
    public final String zzghm;
    public String zzghn;
    private Bundle zzgho;

    public zzctj(JsonReader jsonReader0) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        this.zzgho = new Bundle();
        String s = "";
        Map map0 = new HashMap();
        jsonReader0.beginObject();
        while(jsonReader0.hasNext()) {
            String s1 = jsonReader0.nextName();
            if(s1 == null) {
                s1 = "";
            }
            switch(s1) {
                case "params": {
                    s = jsonReader0.nextString();
                    break;
                }
                case "signal_dictionary": {
                    map0 = zzayf.zzb(jsonReader0);
                    break;
                }
                default: {
                    jsonReader0.skipValue();
                }
            }
        }
        this.zzghm = s;
        jsonReader0.endObject();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getKey() != null && map$Entry0.getValue() != null) {
                this.zzgho.putString(((String)map$Entry0.getKey()), ((String)map$Entry0.getValue()));
            }
        }
    }

    final zzctj zzn(Bundle bundle0) {
        try {
            this.zzghn = zzq.zzkv().zzd(bundle0).toString();
        }
        catch(JSONException unused_ex) {
            this.zzghn = "{}";
        }
        return this;
    }
}

