package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;

public final class zzdek {
    public final int responseCode;
    public final String zzcac;
    public final List zzdds;
    public final String zzdmp;
    public final long zzfzs;
    public final int zzgqf;

    zzdek(JsonReader jsonReader0) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        List list0 = Collections.emptyList();
        String s = "";
        String s1 = "";
        jsonReader0.beginObject();
        int v = 0;
        long v1 = 0L;
        int v2 = 0;
        while(jsonReader0.hasNext()) {
            String s2 = jsonReader0.nextName();
            if("nofill_urls".equals(s2)) {
                list0 = zzayf.zza(jsonReader0);
            }
            else if("refresh_interval".equals(s2)) {
                v = jsonReader0.nextInt();
            }
            else if("gws_query_id".equals(s2)) {
                s = jsonReader0.nextString();
            }
            else if("analytics_query_ad_event_id".equals(s2)) {
                s1 = jsonReader0.nextString();
            }
            else if("response_code".equals(s2)) {
                v2 = jsonReader0.nextInt();
            }
            else if("latency".equals(s2)) {
                v1 = jsonReader0.nextLong();
            }
            else {
                jsonReader0.skipValue();
            }
        }
        jsonReader0.endObject();
        this.zzdds = list0;
        this.zzgqf = v;
        this.zzcac = s;
        this.zzdmp = s1;
        this.responseCode = v2;
        this.zzfzs = v1;
    }
}

