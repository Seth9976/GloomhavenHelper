package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbfb {
    private static final Pattern zzeix;
    private static final Pattern zzeiy;

    static {
        zzbfb.zzeix = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
        zzbfb.zzeiy = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);
    }

    public static String zzabu() {
        String s = (String)zzvh.zzpd().zzd(zzzx.zzcih);
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("version", s);
            jSONObject0.put("sdk", "Google Mobile Ads");
            jSONObject0.put("sdkVersion", "12.4.51-000");
            return "<script>Object.defineProperty(window,\'MRAID_ENV\',{get:function(){return " + jSONObject0.toString() + "}});" + "</script>";
        }
        catch(JSONException jSONException0) {
            zzawf.zzd("Unable to build MRAID_ENV", jSONException0);
            return null;
        }
    }

    public static String zzf(@NonNull String s, @Nullable String[] arr_s) {
        if(arr_s.length == 0) {
            return s;
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        Matcher matcher0 = zzbfb.zzeix.matcher(s);
        int v = 0;
        if(matcher0.find()) {
            int v1 = matcher0.end();
            stringBuilder0.append(s.substring(0, v1));
            while(v < arr_s.length) {
                String s1 = arr_s[v];
                if(s1 != null) {
                    stringBuilder0.append(s1);
                }
                ++v;
            }
            stringBuilder0.append(s.substring(v1));
            return stringBuilder0.toString();
        }
        if(!zzbfb.zzeiy.matcher(s).find()) {
            while(v < arr_s.length) {
                String s2 = arr_s[v];
                if(s2 != null) {
                    stringBuilder0.append(s2);
                }
                ++v;
            }
        }
        stringBuilder0.append(s);
        return stringBuilder0.toString();
    }
}

