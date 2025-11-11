package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public final class zzdeo {
    public final List zzgqi;
    public final zzdek zzgqj;
    public final List zzgqk;

    private zzdeo(JsonReader jsonReader0) throws IllegalStateException, IOException, JSONException, NumberFormatException, AssertionError {
        List list0 = Collections.emptyList();
        ArrayList arrayList0 = new ArrayList();
        jsonReader0.beginObject();
        zzdek zzdek0 = null;
        while(jsonReader0.hasNext()) {
            String s = jsonReader0.nextName();
            if("responses".equals(s)) {
                jsonReader0.beginArray();
                jsonReader0.beginObject();
                while(jsonReader0.hasNext()) {
                    String s1 = jsonReader0.nextName();
                    if("ad_configs".equals(s1)) {
                        list0 = new ArrayList();
                        jsonReader0.beginArray();
                        while(jsonReader0.hasNext()) {
                            list0.add(new zzdei(jsonReader0));
                        }
                        jsonReader0.endArray();
                    }
                    else if(s1.equals("common")) {
                        zzdek0 = new zzdek(jsonReader0);
                    }
                    else {
                        jsonReader0.skipValue();
                    }
                }
                jsonReader0.endObject();
                jsonReader0.endArray();
            }
            else if(s.equals("actions")) {
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    jsonReader0.beginObject();
                    String s2 = null;
                    Map map0 = null;
                    while(jsonReader0.hasNext()) {
                        String s3 = jsonReader0.nextName();
                        if("name".equals(s3)) {
                            s2 = jsonReader0.nextString();
                        }
                        else if("info".equals(s3)) {
                            map0 = zzayf.zzb(jsonReader0);
                        }
                        else {
                            jsonReader0.skipValue();
                        }
                    }
                    if(s2 != null) {
                        arrayList0.add(new zzden(s2, map0));
                    }
                    jsonReader0.endObject();
                }
                jsonReader0.endArray();
            }
        }
        this.zzgqk = arrayList0;
        this.zzgqi = list0;
        if(zzdek0 == null) {
            zzdek0 = new zzdek(new JsonReader(new StringReader("{}")));
        }
        this.zzgqj = zzdek0;
    }

    public static zzdeo zza(Reader reader0) throws zzdej {
        try {
            return new zzdeo(new JsonReader(reader0));
        }
        catch(IOException | IllegalStateException | JSONException | NumberFormatException | AssertionError iOException0) {
            throw new zzdej("unable to parse ServerResponse", iOException0);
        }
        finally {
            IOUtils.closeQuietly(reader0);
        }
    }
}

