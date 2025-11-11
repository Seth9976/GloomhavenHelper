package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

final class zzcbb implements Callable {
    private final zzdei zzfgp;
    private final zzcay zzfry;
    private final zzdeq zzfsg;
    private final JSONObject zzfsh;

    zzcbb(zzcay zzcay0, zzdeq zzdeq0, zzdei zzdei0, JSONObject jSONObject0) {
        this.zzfry = zzcay0;
        this.zzfsg = zzdeq0;
        this.zzfgp = zzdei0;
        this.zzfsh = jSONObject0;
    }

    @Override
    public final Object call() {
        zzdei zzdei0 = this.zzfgp;
        JSONObject jSONObject0 = this.zzfsh;
        zzbyz zzbyz0 = new zzbyz();
        zzbyz0.zzdj(jSONObject0.optInt("template_id", -1));
        zzbyz0.zzfx(jSONObject0.optString("custom_template_id"));
        JSONObject jSONObject1 = jSONObject0.optJSONObject("omid_settings");
        zzbyz0.zzfy((jSONObject1 == null ? null : jSONObject1.optString("omid_partner_name")));
        zzdeu zzdeu0 = this.zzfsg.zzgql.zzfir;
        String s = Integer.toString(zzbyz0.zzake());
        if(!zzdeu0.zzgqs.contains(s)) {
            throw new zzcpe("Invalid template ID: " + zzbyz0.zzake(), 0);
        }
        if(zzbyz0.zzake() == 3) {
            if(zzbyz0.getCustomTemplateId() == null) {
                throw new zzcpe("No custom template id for custom template ad response.", 0);
            }
            String s1 = zzbyz0.getCustomTemplateId();
            if(!zzdeu0.zzgqt.contains(s1)) {
                throw new zzcpe("Unexpected custom template id in the response.", 0);
            }
        }
        zzbyz0.setStarRating(jSONObject0.optDouble("rating", -1.0));
        String s2 = jSONObject0.optString("headline", null);
        if(zzdei0.zzdnk) {
            s2 = zzawo.zzws() + " : " + s2;
        }
        zzbyz0.zzn("headline", s2);
        zzbyz0.zzn("body", jSONObject0.optString("body", null));
        zzbyz0.zzn("call_to_action", jSONObject0.optString("call_to_action", null));
        zzbyz0.zzn("store", jSONObject0.optString("store", null));
        zzbyz0.zzn("price", jSONObject0.optString("price", null));
        zzbyz0.zzn("advertiser", jSONObject0.optString("advertiser", null));
        return zzbyz0;
    }
}

