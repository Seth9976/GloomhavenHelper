package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzcxv;
import com.google.android.gms.internal.ads.zzuh;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;

final class zzo {
    private final String zzbmc;
    private final Map zzbmd;
    private String zzbme;
    private String zzbmf;
    private final Context zzur;

    public zzo(Context context0, String s) {
        this.zzur = context0.getApplicationContext();
        this.zzbmc = s;
        this.zzbmd = new TreeMap();
    }

    public final String getQuery() {
        return this.zzbme;
    }

    public final void zza(zzuh zzuh0, zzazo zzazo0) {
        this.zzbme = zzuh0.zzccr.zzbme;
        Bundle bundle0 = zzuh0.zzcct == null ? null : zzuh0.zzcct.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if(bundle0 == null) {
            return;
        }
        String s = (String)zzabb.zzctr.get();
        for(Object object0: bundle0.keySet()) {
            String s1 = (String)object0;
            if(s.equals(s1)) {
                this.zzbmf = bundle0.getString(s1);
            }
            else if(s1.startsWith("csa_")) {
                String s2 = bundle0.getString(s1);
                this.zzbmd.put(s1.substring(4), s2);
            }
        }
        this.zzbmd.put("SDKVersion", zzazo0.zzbmj);
        if(((Boolean)zzabb.zzctp.get()).booleanValue()) {
            try {
                JSONArray jSONArray0 = new JSONArray(((String)zzabb.zzctq.get()));
                Bundle bundle1 = zzcxv.zza(this.zzur, jSONArray0);
                for(Object object1: bundle1.keySet()) {
                    String s3 = bundle1.get(((String)object1)).toString();
                    this.zzbmd.put(((String)object1), s3);
                }
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Flag gads:afs:csa_tcf_data_to_collect not a valid JSON array", jSONException0);
            }
        }
    }

    public final String zzkl() {
        return this.zzbmf;
    }

    public final String zzkm() {
        return this.zzbmc;
    }

    public final Map zzkn() {
        return this.zzbmd;
    }
}

