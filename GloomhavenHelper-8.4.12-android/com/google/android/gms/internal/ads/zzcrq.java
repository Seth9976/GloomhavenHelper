package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcrq {
    private final Executor executor;
    private JSONObject zzfut;
    private final Map zzgfe;
    private final Map zzgff;

    public zzcrq(Executor executor0) {
        this.zzgfe = new ConcurrentHashMap();
        this.zzgff = new ConcurrentHashMap();
        this.executor = executor0;
    }

    public final void zzamh() {
        zzq.zzkz().zzvk().zzb(() -> {
            zzcrv zzcrv0 = () -> this.zzaoj();
            this.executor.execute(zzcrv0);
        });
        zzcrs zzcrs0 = () -> this.zzaoj();
        this.executor.execute(zzcrs0);
    }

    private final void zzaoj() {
        synchronized(this) {
            JSONObject jSONObject0 = zzq.zzkz().zzvk().zzwf().zzvt();
            if(jSONObject0 != null) {
                try {
                    JSONArray jSONArray0 = jSONObject0.optJSONArray("ad_unit_id_settings");
                    this.zzfut = jSONObject0.optJSONObject("ad_unit_patterns");
                    if(jSONArray0 != null) {
                        for(int v1 = 0; v1 < jSONArray0.length(); ++v1) {
                            JSONObject jSONObject1 = jSONArray0.getJSONObject(v1);
                            String s = jSONObject1.optString("ad_unit_id", "");
                            String s1 = jSONObject1.optString("format", "");
                            ArrayList arrayList0 = new ArrayList();
                            JSONObject jSONObject2 = jSONObject1.optJSONObject("mediation_config");
                            if(jSONObject2 != null) {
                                JSONArray jSONArray1 = jSONObject2.optJSONArray("ad_networks");
                                if(jSONArray1 != null) {
                                    for(int v2 = 0; v2 < jSONArray1.length(); ++v2) {
                                        JSONObject jSONObject3 = jSONArray1.getJSONObject(v2);
                                        ArrayList arrayList1 = new ArrayList();
                                        if(jSONObject3 != null) {
                                            JSONObject jSONObject4 = jSONObject3.optJSONObject("data");
                                            Bundle bundle0 = new Bundle();
                                            if(jSONObject4 != null) {
                                                Iterator iterator0 = jSONObject4.keys();
                                                while(iterator0.hasNext()) {
                                                    Object object0 = iterator0.next();
                                                    bundle0.putString(((String)object0), jSONObject4.optString(((String)object0), ""));
                                                }
                                            }
                                            JSONArray jSONArray2 = jSONObject3.optJSONArray("rtb_adapters");
                                            if(jSONArray2 != null) {
                                                ArrayList arrayList2 = new ArrayList();
                                                for(int v3 = 0; v3 < jSONArray2.length(); ++v3) {
                                                    String s2 = jSONArray2.optString(v3, "");
                                                    if(!TextUtils.isEmpty(s2)) {
                                                        arrayList2.add(s2);
                                                    }
                                                }
                                                ArrayList arrayList3 = arrayList2;
                                                int v4 = arrayList3.size();
                                                int v5 = 0;
                                                while(v5 < v4) {
                                                    Object object1 = arrayList3.get(v5);
                                                    ++v5;
                                                    String s3 = (String)object1;
                                                    this.zzgl(s3);
                                                    if(((zzcrx)this.zzgfe.get(s3)) != null) {
                                                        arrayList1.add(new zzcrx(s3, s1, bundle0));
                                                    }
                                                }
                                            }
                                        }
                                        arrayList0.addAll(arrayList1);
                                    }
                                }
                            }
                            if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s)) {
                                Map map0 = (Map)this.zzgff.get(s1);
                                if(map0 == null) {
                                    map0 = new ConcurrentHashMap();
                                }
                                this.zzgff.put(s1, map0);
                                List list0 = (List)map0.get(s);
                                if(list0 == null) {
                                    list0 = new ArrayList();
                                }
                                list0.addAll(arrayList0);
                                map0.put(s, list0);
                            }
                        }
                    }
                }
                catch(JSONException jSONException0) {
                    zzawf.zza("Malformed config loading JSON.", jSONException0);
                }
            }
        }
    }

    // 检测为 Lambda 实现
    final void zzaok() [...]

    // 检测为 Lambda 实现
    final void zzaol() [...]

    // 检测为 Lambda 实现
    final void zzaom() [...]

    public final void zzgl(String s) {
        if(TextUtils.isEmpty(s)) {
            return;
        }
        if(this.zzgfe.containsKey(s)) {
            return;
        }
        zzcrx zzcrx0 = new zzcrx(s, "", new Bundle());
        this.zzgfe.put(s, zzcrx0);
    }

    public final Map zzr(String s, String s1) {
        if(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1)) {
            Map map0 = (Map)this.zzgff.get(s);
            if(map0 == null) {
                return Collections.emptyMap();
            }
            List list0 = (List)map0.get(s1);
            if(list0 == null) {
                list0 = (List)map0.get(zzcfb.zza(this.zzfut, s1, s));
            }
            if(list0 == null) {
                return Collections.emptyMap();
            }
            Map map1 = new HashMap();
            for(Object object0: list0) {
                String s2 = ((zzcrx)object0).zzfik;
                if(!map1.containsKey(s2)) {
                    map1.put(s2, new ArrayList());
                }
                ((List)map1.get(s2)).add(((zzcrx)object0).zzejn);
            }
            return map1;
        }
        return Collections.emptyMap();
    }
}

