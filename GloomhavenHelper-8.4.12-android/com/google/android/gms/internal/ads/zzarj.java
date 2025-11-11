package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamite.DynamiteModule;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzarj extends zzarl {
    private final Object lock;
    @Nullable
    private SharedPreferences zzdoo;
    private final zzakc zzdop;
    private final Context zzyz;

    public zzarj(Context context0, zzakc zzakc0) {
        this.lock = new Object();
        this.zzyz = context0.getApplicationContext();
        this.zzdop = zzakc0;
    }

    public static JSONObject zzaa(Context context0) {
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("js", zzazo.zzxr().zzbmj);
            jSONObject0.put("mf", zzabj.zzcux.get());
            jSONObject0.put("cl", "300152424");
            jSONObject0.put("rapid_rc", "dev");
            jSONObject0.put("rapid_rollup", "HEAD");
            jSONObject0.put("admob_module_version", 20360);
            jSONObject0.put("dynamite_local_version", 21300);
            jSONObject0.put("dynamite_version", DynamiteModule.getRemoteVersion(context0, "com.google.android.gms.ads.dynamite"));
            jSONObject0.put("container_version", 12451009);
        }
        catch(JSONException unused_ex) {
        }
        return jSONObject0;
    }

    // 检测为 Lambda 实现
    final Void zzf(JSONObject jSONObject0) [...]

    @Override  // com.google.android.gms.internal.ads.zzarl
    public final zzdof zzum() {
        synchronized(this.lock) {
            if(this.zzdoo == null) {
                this.zzdoo = this.zzyz.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        long v1 = this.zzdoo.getLong("js_last_update", 0L);
        if(zzq.zzlc().currentTimeMillis() - v1 < ((long)(((Long)zzabj.zzcuy.get())))) {
            return zzdnt.zzaj(null);
        }
        JSONObject jSONObject0 = zzarj.zzaa(this.zzyz);
        return zzdnt.zzb(this.zzdop.zzi(jSONObject0), (JSONObject jSONObject0) -> {
            zzzx.zza(this.zzyz, 1, jSONObject0);
            this.zzdoo.edit().putLong("js_last_update", zzq.zzlc().currentTimeMillis()).apply();
            return null;
        }, zzazq.zzdxp);
    }
}

