package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzafi {
    public static final zzafz zzcxw;
    public static final zzafz zzcxx;
    public static final zzafz zzcxy;
    public static final zzafz zzcxz;
    public static final zzafz zzcya;
    public static final zzafz zzcyb;
    public static final zzafz zzcyc;
    public static final zzafz zzcyd;
    public static final zzafz zzcye;
    public static final zzafz zzcyf;
    public static final zzafz zzcyg;
    public static final zzafz zzcyh;
    public static final zzafz zzcyi;
    public static final zzafz zzcyj;
    public static final zzagf zzcyk;
    public static final zzafz zzcyl;
    public static final zzafz zzcym;
    public static final zzafz zzcyn;

    static {
        zzafi.zzcxw = zzafl.zzcyo;
        zzafi.zzcxx = zzafk.zzcyo;
        zzafi.zzcxy = zzafn.zzcyo;
        zzafi.zzcxz = new zzafo();
        zzafi.zzcya = new zzafr();
        zzafi.zzcyb = zzafm.zzcyo;
        zzafi.zzcyc = new zzafq();
        zzafi.zzcyd = new zzaft();
        zzafi.zzcye = zzafp.zzcyo;
        zzafi.zzcyf = new zzafs();
        zzafi.zzcyg = new zzafv();
        zzafi.zzcyh = new zzbct();
        zzafi.zzcyi = new zzbcw();
        zzafi.zzcyj = new zzafj();
        zzafi.zzcyk = new zzagf();
        zzafi.zzcyl = new zzafu();
        zzafi.zzcym = new zzafx();
        zzafi.zzcyn = new zzafw();
    }

    static final void zza(zzaia zzaia0, Map map0) {
        String s = (String)map0.get("u");
        if(s == null) {
            zzawf.zzfa("URL missing from click GMSG.");
            return;
        }
        Uri uri0 = Uri.parse(s);
        try {
            zzdq zzdq0 = ((zzbfd)zzaia0).zzaai();
            if(zzdq0 != null && zzdq0.zzb(uri0)) {
                uri0 = zzdq0.zza(uri0, ((zzbev)zzaia0).getContext(), ((zzbff)zzaia0).getView(), ((zzbev)zzaia0).zzys());
            }
        }
        catch(zzdt unused_ex) {
            String s1 = String.valueOf(s);
            zzawf.zzfa((s1.length() == 0 ? new String("Unable to append parameter to URL: ") : "Unable to append parameter to URL: " + s1));
        }
        String s2 = zzaux.zzb(uri0, ((zzbev)zzaia0).getContext());
        new zzayo(((zzbev)zzaia0).getContext(), ((zzbfg)zzaia0).zzyw().zzbmj, s2).zzvw();
    }

    static final void zza(zzbev zzbev0, Map map0) {
        String s = (String)map0.get("u");
        if(s == null) {
            zzawf.zzfa("URL missing from httpTrack GMSG.");
            return;
        }
        new zzayo(zzbev0.getContext(), ((zzbfg)zzbev0).zzyw().zzbmj, s).zzvw();
    }

    static final void zza(zzbfd zzbfd0, Map map0) {
        String s = (String)map0.get("tx");
        String s1 = (String)map0.get("ty");
        String s2 = (String)map0.get("td");
        try {
            int v = Integer.parseInt(s);
            int v1 = Integer.parseInt(s1);
            int v2 = Integer.parseInt(s2);
            zzdq zzdq0 = zzbfd0.zzaai();
            if(zzdq0 != null) {
                zzdq0.zzcb().zza(v, v1, v2);
            }
        }
        catch(NumberFormatException unused_ex) {
            zzawf.zzfa("Could not parse touch parameters from gmsg.");
        }
    }

    static final void zzb(zzbev zzbev0, Map map0) {
        JSONObject jSONObject2;
        JSONArray jSONArray0;
        JSONObject jSONObject0;
        PackageManager packageManager0 = zzbev0.getContext().getPackageManager();
        String s = (String)map0.get("data");
        try {
            jSONObject0 = new JSONObject(s);
        }
        catch(JSONException unused_ex) {
            ((zzaia)zzbev0).zza("openableIntents", new JSONObject());
            return;
        }
        try {
            jSONArray0 = jSONObject0.getJSONArray("intents");
        }
        catch(JSONException unused_ex) {
            ((zzaia)zzbev0).zza("openableIntents", new JSONObject());
            return;
        }
        JSONObject jSONObject1 = new JSONObject();
        for(int v = 0; v < jSONArray0.length(); ++v) {
            try {
                jSONObject2 = jSONArray0.getJSONObject(v);
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Error parsing the intent data.", jSONException0);
                continue;
            }
            String s1 = jSONObject2.optString("id");
            String s2 = jSONObject2.optString("u");
            String s3 = jSONObject2.optString("i");
            String s4 = jSONObject2.optString("m");
            String s5 = jSONObject2.optString("p");
            String s6 = jSONObject2.optString("c");
            jSONObject2.optString("f");
            jSONObject2.optString("e");
            String s7 = jSONObject2.optString("intent_url");
            Intent intent0 = null;
            if(!TextUtils.isEmpty(s7)) {
                try {
                    intent0 = Intent.parseUri(s7, 0);
                }
                catch(URISyntaxException uRISyntaxException0) {
                    String s8 = String.valueOf(s7);
                    zzawf.zzc((s8.length() == 0 ? new String("Error parsing the url: ") : "Error parsing the url: " + s8), uRISyntaxException0);
                }
            }
            boolean z = true;
            if(intent0 == null) {
                intent0 = new Intent();
                if(!TextUtils.isEmpty(s2)) {
                    intent0.setData(Uri.parse(s2));
                }
                if(!TextUtils.isEmpty(s3)) {
                    intent0.setAction(s3);
                }
                if(!TextUtils.isEmpty(s4)) {
                    intent0.setType(s4);
                }
                if(!TextUtils.isEmpty(s5)) {
                    intent0.setPackage(s5);
                }
                if(!TextUtils.isEmpty(s6)) {
                    String[] arr_s = s6.split("/", 2);
                    if(arr_s.length == 2) {
                        intent0.setComponent(new ComponentName(arr_s[0], arr_s[1]));
                    }
                }
            }
            if(packageManager0.resolveActivity(intent0, 0x10000) == null) {
                z = false;
            }
            try {
                jSONObject1.put(s1, z);
            }
            catch(JSONException jSONException1) {
                zzawf.zzc("Error constructing openable urls response.", jSONException1);
            }
        }
        ((zzaia)zzbev0).zza("openableIntents", jSONObject1);
    }

    static final void zzc(zzbev zzbev0, Map map0) {
        String s = (String)map0.get("urls");
        if(TextUtils.isEmpty(s)) {
            zzawf.zzfa("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] arr_s = s.split(",");
        HashMap hashMap0 = new HashMap();
        PackageManager packageManager0 = zzbev0.getContext().getPackageManager();
        for(int v = 0; v < arr_s.length; ++v) {
            String s1 = arr_s[v];
            String[] arr_s1 = s1.split(";", 2);
            hashMap0.put(s1, Boolean.valueOf(packageManager0.resolveActivity(new Intent((arr_s1.length <= 1 ? "android.intent.action.VIEW" : arr_s1[1].trim()), Uri.parse(arr_s1[0].trim())), 0x10000) != null));
        }
        ((zzaia)zzbev0).zza("openableURLs", hashMap0);
    }
}

