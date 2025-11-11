package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import android.util.JsonWriter;
import androidx.annotation.Nullable;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdem implements zzayk {
    @Nullable
    public final String zzdiu;
    @Nullable
    public final String zzdiw;
    public final JSONObject zzfnc;
    private final JSONObject zzgqg;

    zzdem(JsonReader jsonReader0) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        this.zzgqg = zzayf.zzc(jsonReader0);
        this.zzdiw = this.zzgqg.optString("ad_html", null);
        this.zzdiu = this.zzgqg.optString("ad_base_url", null);
        this.zzfnc = this.zzgqg.optJSONObject("ad_json");
    }

    @Override  // com.google.android.gms.internal.ads.zzayk
    public final void zza(JsonWriter jsonWriter0) throws IOException {
        zzayf.zza(jsonWriter0, this.zzgqg);
    }
}

