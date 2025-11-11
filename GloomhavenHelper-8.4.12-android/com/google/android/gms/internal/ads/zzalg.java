package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzalg {
    private final String zzdcr;
    private final String zzdcs;
    public final List zzdct;
    private final String zzdcu;
    private final String zzdcv;
    private final List zzdcw;
    private final List zzdcx;
    private final List zzdcy;
    private final List zzdcz;
    private final List zzdda;
    public final String zzddb;
    private final List zzddc;
    private final List zzddd;
    private final List zzdde;
    private final String zzddf;
    private final String zzddg;
    @Nullable
    private final String zzddh;
    @Nullable
    private final String zzddi;
    private final String zzddj;
    @Nullable
    private final List zzddk;
    private final String zzddl;
    @Nullable
    public final String zzddm;
    private final long zzddn;

    public zzalg(JSONObject jSONObject0) throws JSONException {
        this.zzdcs = jSONObject0.optString("id");
        JSONArray jSONArray0 = jSONObject0.getJSONArray("adapters");
        ArrayList arrayList0 = new ArrayList(jSONArray0.length());
        for(int v = 0; v < jSONArray0.length(); ++v) {
            arrayList0.add(jSONArray0.getString(v));
        }
        this.zzdct = Collections.unmodifiableList(arrayList0);
        this.zzdcu = jSONObject0.optString("allocation_id", null);
        this.zzdcw = zzali.zza(jSONObject0, "clickurl");
        this.zzdcx = zzali.zza(jSONObject0, "imp_urls");
        this.zzdcy = zzali.zza(jSONObject0, "downloaded_imp_urls");
        this.zzdda = zzali.zza(jSONObject0, "fill_urls");
        this.zzddc = zzali.zza(jSONObject0, "video_start_urls");
        this.zzdde = zzali.zza(jSONObject0, "video_complete_urls");
        this.zzddd = zzali.zza(jSONObject0, "video_reward_urls");
        this.zzddf = jSONObject0.optString("transaction_id");
        this.zzddg = jSONObject0.optString("valid_from_timestamp");
        JSONObject jSONObject1 = jSONObject0.optJSONObject("ad");
        this.zzdcz = jSONObject1 == null ? null : zzali.zza(jSONObject1, "manual_impression_urls");
        this.zzdcr = jSONObject1 == null ? null : jSONObject1.toString();
        JSONObject jSONObject2 = jSONObject0.optJSONObject("data");
        this.zzddb = jSONObject2 == null ? null : jSONObject2.toString();
        this.zzdcv = jSONObject2 == null ? null : jSONObject2.optString("class_name");
        this.zzddh = jSONObject0.optString("html_template", null);
        this.zzddi = jSONObject0.optString("ad_base_url", null);
        JSONObject jSONObject3 = jSONObject0.optJSONObject("assets");
        this.zzddj = jSONObject3 == null ? null : jSONObject3.toString();
        this.zzddk = zzali.zza(jSONObject0, "template_ids");
        JSONObject jSONObject4 = jSONObject0.optJSONObject("ad_loader_options");
        this.zzddl = jSONObject4 == null ? null : jSONObject4.toString();
        this.zzddm = jSONObject0.optString("response_type", null);
        this.zzddn = jSONObject0.optLong("ad_network_timeout_millis", -1L);
    }
}

