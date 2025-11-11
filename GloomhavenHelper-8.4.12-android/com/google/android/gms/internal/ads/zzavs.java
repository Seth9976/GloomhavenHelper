package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzavs {
    private String zzdkq;
    private final long zzdrv;
    private final List zzdrw;
    private final List zzdrx;
    private final Map zzdry;
    private String zzdrz;
    private JSONObject zzdsa;
    private boolean zzdsb;

    public zzavs(String s, long v) {
        this.zzdrw = new ArrayList();
        this.zzdrx = new ArrayList();
        this.zzdry = new HashMap();
        this.zzdsb = false;
        this.zzdkq = s;
        this.zzdrv = v;
        if(!TextUtils.isEmpty(s)) {
            try {
                this.zzdsa = new JSONObject(s);
                if(this.zzdsa.optInt("status", -1) != 1) {
                    this.zzdsb = false;
                    zzawf.zzfa("App settings could not be fetched successfully.");
                    return;
                }
                this.zzdsb = true;
                this.zzdrz = this.zzdsa.optString("app_id");
                JSONArray jSONArray0 = this.zzdsa.optJSONArray("ad_unit_id_settings");
                if(jSONArray0 != null) {
                    for(int v2 = 0; v2 < jSONArray0.length(); ++v2) {
                        JSONObject jSONObject0 = jSONArray0.getJSONObject(v2);
                        String s1 = jSONObject0.optString("format");
                        String s2 = jSONObject0.optString("ad_unit_id");
                        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
                            if("interstitial".equalsIgnoreCase(s1)) {
                                this.zzdrx.add(s2);
                            }
                            else if("rewarded".equalsIgnoreCase(s1)) {
                                JSONObject jSONObject1 = jSONObject0.optJSONObject("mediation_config");
                                if(jSONObject1 != null) {
                                    zzalj zzalj0 = new zzalj(jSONObject1);
                                    this.zzdry.put(s2, zzalj0);
                                }
                            }
                        }
                    }
                }
                JSONArray jSONArray1 = this.zzdsa.optJSONArray("persistable_banner_ad_unit_ids");
                if(jSONArray1 != null) {
                    for(int v1 = 0; v1 < jSONArray1.length(); ++v1) {
                        String s3 = jSONArray1.optString(v1);
                        this.zzdrw.add(s3);
                    }
                }
            }
            catch(JSONException jSONException0) {
                zzawf.zzd("Exception occurred while processing app setting json", jSONException0);
                zzq.zzkz().zza(jSONException0, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    public final long zzvo() {
        return this.zzdrv;
    }

    public final boolean zzvp() {
        return this.zzdsb;
    }

    public final String zzvq() {
        return this.zzdkq;
    }

    public final String zzvr() {
        return this.zzdrz;
    }

    public final Map zzvs() {
        return this.zzdry;
    }

    public final JSONObject zzvt() {
        return this.zzdsa;
    }
}

