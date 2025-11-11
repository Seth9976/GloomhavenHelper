package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcbc {
    private final Executor executor;
    private final zzazo zzblu;
    private final zzach zzdff;
    private final zzsn zzefm;
    private final zzdq zzehb;
    private final ScheduledExecutorService zzfib;
    private final zzcax zzfsi;
    private final zza zzfsj;
    private final zzcbt zzfsk;
    private final Context zzur;

    public zzcbc(Context context0, zzcax zzcax0, zzdq zzdq0, zzazo zzazo0, zza zza0, zzsn zzsn0, Executor executor0, zzdeu zzdeu0, zzcbt zzcbt0, ScheduledExecutorService scheduledExecutorService0) {
        this.zzur = context0;
        this.zzfsi = zzcax0;
        this.zzehb = zzdq0;
        this.zzblu = zzazo0;
        this.zzfsj = zza0;
        this.zzefm = zzsn0;
        this.executor = executor0;
        this.zzdff = zzdeu0.zzdff;
        this.zzfsk = zzcbt0;
        this.zzfib = scheduledExecutorService0;
    }

    private static zzdof zza(zzdof zzdof0, Object object0) {
        zzcbi zzcbi0 = new zzcbi(null);
        return zzdnt.zzb(zzdof0, Exception.class, zzcbi0, zzazq.zzdxp);
    }

    private final zzdof zza(@Nullable JSONArray jSONArray0, boolean z, boolean z1) {
        if(jSONArray0 != null && jSONArray0.length() > 0) {
            ArrayList arrayList0 = new ArrayList();
            int v = z1 ? jSONArray0.length() : 1;
            for(int v1 = 0; v1 < v; ++v1) {
                arrayList0.add(this.zza(jSONArray0.optJSONObject(v1), z));
            }
            return zzdnt.zzb(zzdnt.zzg(arrayList0), zzcbf.zzdpv, this.executor);
        }
        return zzdnt.zzaj(Collections.emptyList());
    }

    private final zzdof zza(@Nullable JSONObject jSONObject0, boolean z) {
        if(jSONObject0 == null) {
            return zzdnt.zzaj(null);
        }
        String s = jSONObject0.optString("url");
        if(TextUtils.isEmpty(s)) {
            return zzdnt.zzaj(null);
        }
        double f = jSONObject0.optDouble("scale", 1.0);
        boolean z1 = jSONObject0.optBoolean("is_transparent", true);
        int v = jSONObject0.optInt("width", -1);
        int v1 = jSONObject0.optInt("height", -1);
        if(z) {
            return zzdnt.zzaj(new zzacd(null, Uri.parse(s), f, v, v1));
        }
        zzdof zzdof0 = zzdnt.zzb(this.zzfsi.zza(s, f, z1), new zzcbe(s, f, v, v1), this.executor);
        return zzcbc.zza(jSONObject0.optBoolean("require"), zzdof0, null);
    }

    // 去混淆评级： 低(20)
    private static zzdof zza(boolean z, zzdof zzdof0, Object object0) {
        return z ? zzdnt.zzb(zzdof0, new zzcbl(zzdof0), zzazq.zzdxp) : zzcbc.zza(zzdof0, null);
    }

    // 检测为 Lambda 实现
    final zzaby zza(JSONObject jSONObject0, List list0) [...]

    final zzdof zzb(String s, Object object0) throws Exception {
        zzbfl zzbfl0 = zzbfl.zzabv();
        zzbdv zzbdv0 = zzbee.zza(this.zzur, zzbfl0, "native-omid", false, false, this.zzehb, this.zzblu, null, null, this.zzfsj, this.zzefm, null, false);
        zzdof zzdof0 = zzazv.zzl(zzbdv0);
        zzbdv0.zzaaf().zza(new zzcbk(((zzazv)zzdof0)));
        zzbdv0.loadData(s, "text/html", "UTF-8");
        return zzdof0;
    }

    public final zzdof zzc(JSONObject jSONObject0, String s) {
        return this.zza(jSONObject0.optJSONObject(s), this.zzdff.zzcws);
    }

    public final zzdof zzd(JSONObject jSONObject0, String s) {
        return this.zza(jSONObject0.optJSONArray(s), this.zzdff.zzcws, this.zzdff.zzbkg);
    }

    public final zzdof zze(JSONObject jSONObject0, String s) {
        JSONObject jSONObject1 = jSONObject0.optJSONObject(s);
        if(jSONObject1 == null) {
            return zzdnt.zzaj(null);
        }
        JSONArray jSONArray0 = jSONObject1.optJSONArray("images");
        JSONObject jSONObject2 = jSONObject1.optJSONObject("image");
        if(jSONArray0 == null && jSONObject2 != null) {
            jSONArray0 = new JSONArray();
            jSONArray0.put(jSONObject2);
        }
        zzdof zzdof0 = zzdnt.zzb(this.zza(jSONArray0, false, true), (List list0) -> {
            Integer integer0 = null;
            if(list0 != null && !list0.isEmpty()) {
                String s = jSONObject1.optString("text");
                Integer integer1 = zzcbc.zzf(jSONObject1, "bg_color");
                Integer integer2 = zzcbc.zzf(jSONObject1, "text_color");
                int v = jSONObject1.optInt("text_size", -1);
                boolean z = jSONObject1.optBoolean("allow_pub_rendering");
                int v1 = jSONObject1.optInt("animation_ms", 1000);
                int v2 = jSONObject1.optInt("presentation_ms", 4000);
                if(v > 0) {
                    integer0 = v;
                }
                return new zzaby(s, list0, integer1, integer2, integer0, v2 + v1, this.zzdff.zzbkh, z);
            }
            return null;
        }, this.executor);
        return zzcbc.zza(jSONObject1.optBoolean("require"), zzdof0, null);
    }

    private static Integer zzf(JSONObject jSONObject0, String s) {
        try {
            JSONObject jSONObject1 = jSONObject0.getJSONObject(s);
            return Color.rgb(jSONObject1.getInt("r"), jSONObject1.getInt("g"), jSONObject1.getInt("b"));
        }
        catch(JSONException unused_ex) {
            return null;
        }
    }

    public static List zzj(JSONObject jSONObject0) {
        JSONObject jSONObject1 = jSONObject0.optJSONObject("mute");
        if(jSONObject1 == null) {
            return Collections.emptyList();
        }
        JSONArray jSONArray0 = jSONObject1.optJSONArray("reasons");
        if(jSONArray0 != null && jSONArray0.length() > 0) {
            List list0 = new ArrayList();
            for(int v = 0; v < jSONArray0.length(); ++v) {
                zzyc zzyc0 = zzcbc.zzl(jSONArray0.optJSONObject(v));
                if(zzyc0 != null) {
                    list0.add(zzyc0);
                }
            }
            return list0;
        }
        return Collections.emptyList();
    }

    @Nullable
    public static zzyc zzk(JSONObject jSONObject0) {
        JSONObject jSONObject1 = jSONObject0.optJSONObject("mute");
        if(jSONObject1 == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject1.optJSONObject("default_reason");
        return jSONObject2 == null ? null : zzcbc.zzl(jSONObject2);
    }

    @Nullable
    private static zzyc zzl(@Nullable JSONObject jSONObject0) {
        if(jSONObject0 == null) {
            return null;
        }
        String s = jSONObject0.optString("reason");
        String s1 = jSONObject0.optString("ping_url");
        return TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) ? null : new zzyc(s, s1);
    }

    public final zzdof zzm(JSONObject jSONObject0) {
        JSONObject jSONObject1 = zzayf.zza(jSONObject0, new String[]{"html_containers", "instream"});
        if(jSONObject1 == null) {
            JSONObject jSONObject2 = jSONObject0.optJSONObject("video");
            if(jSONObject2 == null) {
                return zzdnt.zzaj(null);
            }
            if(TextUtils.isEmpty(jSONObject2.optString("vast_xml"))) {
                zzawf.zzfa("Required field \'vast_xml\' is missing");
                return zzdnt.zzaj(null);
            }
            return zzcbc.zza(zzdnt.zza(this.zzfsk.zzn(jSONObject2), ((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcmw)))))), TimeUnit.SECONDS, this.zzfib), null);
        }
        String s = jSONObject1.optString("base_url");
        String s1 = jSONObject1.optString("html");
        zzdof zzdof0 = this.zzfsk.zzo(s, s1);
        return zzdnt.zzb(zzdof0, new zzcbj(zzdof0), zzazq.zzdxp);
    }
}

