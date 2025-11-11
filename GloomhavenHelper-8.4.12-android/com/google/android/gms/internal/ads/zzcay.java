package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import org.json.JSONObject;

public final class zzcay {
    private final zzdoe zzfrv;
    private final zzcbc zzfrw;
    private final zzcbm zzfrx;

    public zzcay(zzdoe zzdoe0, zzcbc zzcbc0, zzcbm zzcbm0) {
        this.zzfrv = zzdoe0;
        this.zzfrw = zzcbc0;
        this.zzfrx = zzcbm0;
    }

    public final zzdof zza(zzdeq zzdeq0, zzdei zzdei0, JSONObject jSONObject0) {
        zzdof zzdof6;
        zzcbb zzcbb0 = new zzcbb(this, zzdeq0, zzdei0, jSONObject0);
        zzdof zzdof0 = this.zzfrv.zzd(zzcbb0);
        zzdof zzdof1 = this.zzfrw.zzd(jSONObject0, "images");
        zzdof zzdof2 = this.zzfrw.zzc(jSONObject0, "secondary_image");
        zzdof zzdof3 = this.zzfrw.zzc(jSONObject0, "app_icon");
        zzdof zzdof4 = this.zzfrw.zze(jSONObject0, "attribution");
        zzdof zzdof5 = this.zzfrw.zzm(jSONObject0);
        zzcbc zzcbc0 = this.zzfrw;
        if(jSONObject0.optBoolean("enable_omid")) {
            JSONObject jSONObject1 = jSONObject0.optJSONObject("omid_settings");
            if(jSONObject1 == null) {
                zzdof6 = zzdnt.zzaj(null);
            }
            else {
                String s = jSONObject1.optString("omid_html");
                zzdof6 = TextUtils.isEmpty(s) ? zzdnt.zzaj(null) : zzdnt.zzb(zzdnt.zzaj(null), new zzcbg(zzcbc0, s), zzazq.zzdxo);
            }
        }
        else {
            zzdof6 = zzdnt.zzaj(null);
        }
        zzdof zzdof7 = this.zzfrx.zzg(jSONObject0, "custom_assets");
        return zzdnt.zza(new zzdof[]{zzdof0, zzdof1, zzdof2, zzdof3, zzdof4, zzdof5, zzdof6, zzdof7}).zza(new zzcba(this, zzdof0, zzdof1, zzdof3, zzdof2, zzdof4, jSONObject0, zzdof5, zzdof6, zzdof7), this.zzfrv);
    }
}

