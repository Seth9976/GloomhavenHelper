package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class zzdeh {
    public final int height;
    public final int width;
    public final boolean zzgpj;

    public zzdeh(int v, int v1, boolean z) {
        this.width = v;
        this.height = v1;
        this.zzgpj = z;
    }

    static List zze(JsonReader jsonReader0) throws IllegalStateException, IOException, NumberFormatException {
        List list0 = new ArrayList();
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            jsonReader0.beginObject();
            int v = 0;
            int v1 = 0;
            boolean z = false;
            while(jsonReader0.hasNext()) {
                String s = jsonReader0.nextName();
                if("width".equals(s)) {
                    v = jsonReader0.nextInt();
                }
                else if("height".equals(s)) {
                    v1 = jsonReader0.nextInt();
                }
                else if("is_fluid_height".equals(s)) {
                    z = jsonReader0.nextBoolean();
                }
                else {
                    jsonReader0.skipValue();
                }
            }
            jsonReader0.endObject();
            ((ArrayList)list0).add(new zzdeh(v, v1, z));
        }
        jsonReader0.endArray();
        return list0;
    }
}

