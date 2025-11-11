package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzalj {
    private final long zzddn;
    public final List zzddo;
    private final List zzddp;
    private final List zzddq;
    private final List zzddr;
    private final List zzdds;
    private final List zzddt;
    private final boolean zzddu;
    private final String zzddv;
    private final long zzddw;
    private final String zzddx;
    private final int zzddy;
    private final int zzddz;
    private final long zzdea;
    private final boolean zzdeb;
    private final boolean zzdec;
    private final boolean zzded;
    private final boolean zzdee;
    private int zzdef;
    private int zzdeg;
    private boolean zzdeh;

    public zzalj(JSONObject jSONObject0) throws JSONException {
        zzalg zzalg0;
        boolean z;
        if(zzawf.isLoggable(2)) {
            String s = jSONObject0.toString(2);
            zzawf.zzee((s.length() == 0 ? new String("Mediation Response JSON: ") : "Mediation Response JSON: " + s));
        }
        JSONArray jSONArray0 = jSONObject0.getJSONArray("ad_networks");
        ArrayList arrayList0 = new ArrayList(jSONArray0.length());
        int v = 0;
        int v1 = -1;
        while(v < jSONArray0.length()) {
            try {
                z = true;
                zzalg0 = new zzalg(jSONArray0.getJSONObject(v));
            }
            catch(JSONException unused_ex) {
                goto label_23;
            }
            if("banner".equalsIgnoreCase(zzalg0.zzddm)) {
                this.zzdeh = true;
            }
            arrayList0.add(zzalg0);
            if(v1 < 0) {
                for(Object object0: zzalg0.zzdct) {
                    if(!((String)object0).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                        continue;
                    }
                    goto label_21;
                }
                z = false;
            label_21:
                if(z) {
                    v1 = v;
                }
            }
        label_23:
            ++v;
        }
        this.zzdef = v1;
        this.zzdeg = jSONArray0.length();
        this.zzddo = Collections.unmodifiableList(arrayList0);
        this.zzddv = jSONObject0.optString("qdata");
        this.zzddz = jSONObject0.optInt("fs_model_type", -1);
        long v2 = -1L;
        this.zzdea = jSONObject0.optLong("timeout_ms", -1L);
        JSONObject jSONObject1 = jSONObject0.optJSONObject("settings");
        if(jSONObject1 != null) {
            this.zzddn = jSONObject1.optLong("ad_network_timeout_millis", -1L);
            this.zzddp = zzali.zza(jSONObject1, "click_urls");
            this.zzddq = zzali.zza(jSONObject1, "imp_urls");
            this.zzddr = zzali.zza(jSONObject1, "downloaded_imp_urls");
            this.zzdds = zzali.zza(jSONObject1, "nofill_urls");
            this.zzddt = zzali.zza(jSONObject1, "remote_ping_urls");
            this.zzddu = jSONObject1.optBoolean("render_in_browser", false);
            long v3 = jSONObject1.optLong("refresh", -1L);
            if(v3 > 0L) {
                v2 = 1000L * v3;
            }
            this.zzddw = v2;
            zzasq zzasq0 = zzasq.zza(jSONObject1.optJSONArray("rewards"));
            if(zzasq0 == null) {
                this.zzddx = null;
                this.zzddy = 0;
            }
            else {
                this.zzddx = zzasq0.type;
                this.zzddy = zzasq0.zzdot;
            }
            this.zzdeb = jSONObject1.optBoolean("use_displayed_impression", false);
            this.zzdec = jSONObject1.optBoolean("allow_pub_rendered_attribution", false);
            this.zzded = jSONObject1.optBoolean("allow_pub_owned_ad_view", false);
            this.zzdee = jSONObject1.optBoolean("allow_custom_click_gesture", false);
            return;
        }
        this.zzddn = -1L;
        this.zzddp = null;
        this.zzddq = null;
        this.zzddr = null;
        this.zzdds = null;
        this.zzddt = null;
        this.zzddw = -1L;
        this.zzddx = null;
        this.zzddy = 0;
        this.zzdeb = false;
        this.zzddu = false;
        this.zzdec = false;
        this.zzded = false;
        this.zzdee = false;
    }
}

