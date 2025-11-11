package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbdf implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzbda zzbda0;
        if(zzawf.isLoggable(3)) {
            JSONObject jSONObject0 = new JSONObject(map0);
            jSONObject0.remove("google.afma.Notify_dt");
            zzawf.zzeb(("Precache GMSG: " + jSONObject0));
        }
        if(map0.containsKey("abort")) {
            if(!zzbcx.zzc(((zzbbm)object0))) {
                zzawf.zzfa("Precache abort but no precache task running.");
            }
            return;
        }
        String s = (String)map0.get("src");
        if(s != null) {
            String[] arr_s = {s};
            String s1 = (String)map0.get("demuxed");
            if(s1 != null) {
                try {
                    JSONArray jSONArray0 = new JSONArray(s1);
                    String[] arr_s1 = new String[jSONArray0.length()];
                    for(int v = 0; v < jSONArray0.length(); ++v) {
                        arr_s1[v] = jSONArray0.getString(v);
                    }
                    arr_s = arr_s1;
                }
                catch(JSONException unused_ex) {
                    String s2 = String.valueOf(s1);
                    zzawf.zzfa((s2.length() == 0 ? new String("Malformed demuxed URL list for precache: ") : "Malformed demuxed URL list for precache: " + s2));
                    arr_s = null;
                }
            }
            if(arr_s == null) {
                arr_s = new String[]{s};
            }
            if(zzbcx.zzd(((zzbbm)object0)) != null) {
                zzawf.zzfa("Precache task is already running.");
                return;
            }
            if(((zzbbm)object0).zzyt() == null) {
                zzawf.zzfa("Precache requires a dependency provider.");
                return;
            }
            zzbbj zzbbj0 = new zzbbj(((String)map0.get("flags")));
            Integer integer0 = zzbdf.zzb(map0, "player");
            if(integer0 == null) {
                integer0 = 0;
            }
            zzbda0 = ((zzbbm)object0).zzyt().zzbkw.zza(((zzbbm)object0), ((int)integer0), null, zzbbj0);
            new zzbcv(((zzbbm)object0), zzbda0, s, arr_s).zzvw();
            goto label_43;
        }
        zzbcv zzbcv0 = zzbcx.zzd(((zzbbm)object0));
        if(zzbcv0 != null) {
            zzbda0 = zzbcv0.zzeee;
        label_43:
            Integer integer1 = zzbdf.zzb(map0, "minBufferMs");
            if(integer1 != null) {
                zzbda0.zzcv(((int)integer1));
            }
            Integer integer2 = zzbdf.zzb(map0, "maxBufferMs");
            if(integer2 != null) {
                zzbda0.zzcw(((int)integer2));
            }
            Integer integer3 = zzbdf.zzb(map0, "bufferForPlaybackMs");
            if(integer3 != null) {
                zzbda0.zzcx(((int)integer3));
            }
            Integer integer4 = zzbdf.zzb(map0, "bufferForPlaybackAfterRebufferMs");
            if(integer4 != null) {
                zzbda0.zzcy(((int)integer4));
            }
            return;
        }
        zzawf.zzfa("Precache must specify a source.");
    }

    private static Integer zzb(Map map0, String s) {
        if(!map0.containsKey(s)) {
            return null;
        }
        try {
            return Integer.parseInt(((String)map0.get(s)));
        }
        catch(NumberFormatException unused_ex) {
            zzawf.zzfa(("Precache invalid numeric parameter \'" + s + "\': " + ((String)map0.get(s))));
            return null;
        }
    }
}

