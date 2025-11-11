package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

public final class zzceu {
    private final Executor executor;
    private final Map zzfus;
    private JSONObject zzfut;
    private boolean zzvk;

    public zzceu(Executor executor0) {
        this.zzfus = new ConcurrentHashMap();
        this.executor = executor0;
    }

    public final void zzamh() {
        zzq.zzkz().zzvk().zzb(() -> {
            zzcez zzcez0 = () -> this.zzami();
            this.executor.execute(zzcez0);
        });
        zzcew zzcew0 = () -> this.zzami();
        this.executor.execute(zzcew0);
    }

    private final void zzami() {
        Map map0;
        synchronized(this) {
            this.zzvk = true;
            zzavs zzavs0 = zzq.zzkz().zzvk().zzwf();
            if(zzavs0 == null) {
                return;
            }
            JSONObject jSONObject0 = zzavs0.zzvt();
            if(jSONObject0 == null) {
                return;
            }
            this.zzfut = jSONObject0.optJSONObject("ad_unit_patterns");
            JSONArray jSONArray0 = jSONObject0.optJSONArray("ad_unit_id_settings");
            if(jSONArray0 == null) {
                return;
            }
            for(int v1 = 0; v1 < jSONArray0.length(); ++v1) {
                JSONObject jSONObject1 = jSONArray0.optJSONObject(v1);
                if(jSONObject1 != null) {
                    String s = jSONObject1.optString("ad_unit_id");
                    String s1 = jSONObject1.optString("format");
                    JSONObject jSONObject2 = jSONObject1.optJSONObject("request_signals");
                    if(s != null && jSONObject2 != null && s1 != null) {
                        if(this.zzfus.containsKey(s1)) {
                            map0 = (Map)this.zzfus.get(s1);
                        }
                        else {
                            ConcurrentHashMap concurrentHashMap0 = new ConcurrentHashMap();
                            this.zzfus.put(s1, concurrentHashMap0);
                            map0 = concurrentHashMap0;
                        }
                        map0.put(s, jSONObject2);
                    }
                }
            }
        }
    }

    // 检测为 Lambda 实现
    final void zzamj() [...]

    // 检测为 Lambda 实现
    final void zzamk() [...]

    // 检测为 Lambda 实现
    final void zzaml() [...]
}

