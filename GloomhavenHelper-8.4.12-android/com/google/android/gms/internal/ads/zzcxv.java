package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcxv implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzyz;

    zzcxv(zzdoe zzdoe0, Context context0) {
        this.zzfrv = zzdoe0;
        this.zzyz = context0;
    }

    public static Bundle zza(Context context0, JSONArray jSONArray0) {
        Object object0;
        String s2;
        SharedPreferences sharedPreferences0;
        int v1;
        Bundle bundle0 = new Bundle();
        for(int v = 0; v < jSONArray0.length(); ++v) {
            JSONObject jSONObject0 = jSONArray0.optJSONObject(v);
            String s = jSONObject0.optString("bk");
            String s1 = jSONObject0.optString("sk");
            switch(jSONObject0.optInt("type", -1)) {
                case 0: {
                    v1 = zzcxz.zzgkk;
                    break;
                }
                case 1: {
                    v1 = zzcxz.zzgkl;
                    break;
                }
                case 2: {
                    v1 = zzcxz.zzgkm;
                    break;
                }
                default: {
                    v1 = 0;
                }
            }
            if(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1) && v1 != 0) {
                String[] arr_s = s1.split("/");
                if(arr_s.length > 2 || arr_s.length == 0) {
                    object0 = null;
                }
                else {
                    if(arr_s.length == 1) {
                        sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(context0);
                        s2 = arr_s[0];
                    }
                    else {
                        sharedPreferences0 = context0.getSharedPreferences(arr_s[0], 0);
                        s2 = arr_s[1];
                    }
                    object0 = sharedPreferences0.getAll().get(s2);
                }
                if(object0 != null) {
                    switch(zzcya.zzgko[v1 - 1]) {
                        case 1: {
                            if(object0 instanceof String) {
                                bundle0.putString(s, ((String)object0));
                            }
                            break;
                        }
                        case 2: {
                            if(object0 instanceof Integer) {
                                bundle0.putInt(s, ((int)(((Integer)object0))));
                            }
                            else if(object0 instanceof Long) {
                                bundle0.putLong(s, ((long)(((Long)object0))));
                            }
                            else if(object0 instanceof Float) {
                                bundle0.putFloat(s, ((float)(((Float)object0))));
                            }
                            break;
                        }
                        case 3: {
                            if(object0 instanceof Boolean) {
                                bundle0.putBoolean(s, ((Boolean)object0).booleanValue());
                            }
                        }
                    }
                }
            }
        }
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcxy zzcxy0 = () -> {
            String s = (String)zzvh.zzpd().zzd(zzzx.zzcqy);
            if(TextUtils.isEmpty(s)) {
                return null;
            }
            try {
                JSONArray jSONArray0 = new JSONArray(s);
                return new zzcxx(zzcxv.zza(this.zzyz, jSONArray0));
            }
            catch(JSONException jSONException0) {
                zzawf.zzb("JSON parsing error", jSONException0);
                return null;
            }
        };
        return this.zzfrv.zzd(zzcxy0);
    }

    // 检测为 Lambda 实现
    final zzcyb zzapp() throws Exception [...]
}

