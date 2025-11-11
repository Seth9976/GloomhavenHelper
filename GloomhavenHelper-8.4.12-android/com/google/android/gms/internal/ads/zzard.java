package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public final class zzard {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzdkd;
    private final String zzdmp;
    private final boolean zzdmq;
    private final String zzdnd;
    private final List zzdnf;
    private final String zzdng;
    private final String zzdnh;
    private final boolean zzdni;
    private final String zzdnj;
    private final boolean zzdnk;
    private final JSONObject zzdnl;

    public zzard(JSONObject jSONObject0) {
        this.url = jSONObject0.optString("url");
        this.zzdng = jSONObject0.optString("base_uri");
        this.zzdnh = jSONObject0.optString("post_parameters");
        String s = jSONObject0.optString("drt_include");
        int v = 1;
        this.zzdni = s != null && (s.equals("1") || s.equals("true"));
        this.zzdkd = jSONObject0.optString("request_id");
        this.type = jSONObject0.optString("type");
        String s1 = jSONObject0.optString("errors");
        this.zzdnf = s1 == null ? null : Arrays.asList(s1.split(","));
        if(jSONObject0.optInt("valid", 0) == 1) {
            v = -2;
        }
        this.errorCode = v;
        this.zzdnj = jSONObject0.optString("fetched_ad");
        this.zzdnk = jSONObject0.optBoolean("render_test_ad_label");
        JSONObject jSONObject1 = jSONObject0.optJSONObject("preprocessor_flags");
        if(jSONObject1 == null) {
            jSONObject1 = new JSONObject();
        }
        this.zzdnl = jSONObject1;
        this.zzdmp = jSONObject0.optString("analytics_query_ad_event_id");
        this.zzdmq = jSONObject0.optBoolean("is_analytics_logging_enabled");
        this.zzdnd = jSONObject0.optString("pool_key");
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getUrl() {
        return this.url;
    }

    public final List zzuf() {
        return this.zzdnf;
    }

    public final String zzug() {
        return this.zzdng;
    }

    public final String zzuh() {
        return this.zzdnh;
    }

    public final boolean zzui() {
        return this.zzdni;
    }

    public final JSONObject zzuj() {
        return this.zzdnl;
    }

    public final String zzuk() {
        return this.zzdnd;
    }
}

