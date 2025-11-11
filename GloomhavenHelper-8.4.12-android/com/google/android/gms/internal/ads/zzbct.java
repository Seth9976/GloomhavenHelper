package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbct implements zzafz {
    private boolean zzeec;

    private static int zza(Context context0, Map map0, String s, int v) {
        String s1 = (String)map0.get(s);
        if(s1 != null) {
            try {
                return zzayx.zzb(context0, Integer.parseInt(s1));
            }
            catch(NumberFormatException unused_ex) {
                zzawf.zzfa(("Could not parse " + s + " in a video GMSG: " + s1));
            }
        }
        return v;
    }

    private static void zza(zzbav zzbav0, Map map0) {
        String s = (String)map0.get("minBufferMs");
        String s1 = (String)map0.get("maxBufferMs");
        String s2 = (String)map0.get("bufferForPlaybackMs");
        String s3 = (String)map0.get("bufferForPlaybackAfterRebufferMs");
        String s4 = (String)map0.get("socketReceiveBufferSize");
        try {
            if(s != null) {
                zzbav0.zzcv(Integer.parseInt(s));
            }
            if(s1 != null) {
                zzbav0.zzcw(Integer.parseInt(s1));
            }
            if(s2 != null) {
                zzbav0.zzcx(Integer.parseInt(s2));
            }
            if(s3 != null) {
                zzbav0.zzcy(Integer.parseInt(s3));
            }
            if(s4 != null) {
                zzbav0.zzcz(Integer.parseInt(s4));
            }
        }
        catch(NumberFormatException unused_ex) {
            zzawf.zzfa(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", s, s1));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        int v11;
        zzbbm zzbbm0 = (zzbbm)object0;
        String s = (String)map0.get("action");
        if(s == null) {
            zzawf.zzfa("Action missing from video GMSG.");
            return;
        }
        if(zzawf.isLoggable(3)) {
            JSONObject jSONObject0 = new JSONObject(map0);
            jSONObject0.remove("google.afma.Notify_dt");
            zzawf.zzeb(("Video GMSG: " + s + " " + jSONObject0.toString()));
        }
        if("background".equals(s)) {
            String s1 = (String)map0.get("color");
            if(TextUtils.isEmpty(s1)) {
                zzawf.zzfa("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzbbm0.setBackgroundColor(Color.parseColor(s1));
            }
            catch(IllegalArgumentException unused_ex) {
                zzawf.zzfa("Invalid color parameter in video GMSG.");
            }
            return;
        }
        if("decoderProps".equals(s)) {
            String s2 = (String)map0.get("mimeTypes");
            if(s2 == null) {
                zzawf.zzfa("No MIME types specified for decoder properties inspection.");
                zzbav.zza(zzbbm0, "missingMimeTypes");
                return;
            }
            HashMap hashMap0 = new HashMap();
            String[] arr_s = s2.split(",");
            for(int v = 0; v < arr_s.length; ++v) {
                String s3 = arr_s[v];
                hashMap0.put(s3, zzayv.zzes(s3.trim()));
            }
            zzbav.zza(zzbbm0, hashMap0);
            return;
        }
        zzbbb zzbbb0 = zzbbm0.zzyp();
        if(zzbbb0 == null) {
            zzawf.zzfa("Could not get underlay container for a video GMSG.");
            return;
        }
        boolean z = "new".equals(s);
        if(!z && !"position".equals(s)) {
            zzbeq zzbeq0 = zzbbm0.zzyq();
            if(zzbeq0 != null) {
                if("timeupdate".equals(s)) {
                    String s4 = (String)map0.get("currentTime");
                    if(s4 == null) {
                        zzawf.zzfa("currentTime parameter missing from timeupdate video GMSG.");
                        return;
                    }
                    try {
                        zzbeq0.zze(Float.parseFloat(s4));
                    }
                    catch(NumberFormatException unused_ex) {
                        String s5 = String.valueOf(s4);
                        zzawf.zzfa((s5.length() == 0 ? new String("Could not parse currentTime parameter from timeupdate video GMSG: ") : "Could not parse currentTime parameter from timeupdate video GMSG: " + s5));
                    }
                    return;
                }
                if("skip".equals(s)) {
                    zzbeq0.zzabt();
                    return;
                }
            }
            zzbav zzbav0 = zzbbb0.zzyj();
            if(zzbav0 == null) {
                zzbav.zza(zzbbm0);
                return;
            }
            if("click".equals(s)) {
                Context context0 = zzbbm0.getContext();
                int v1 = zzbct.zza(context0, map0, "x", 0);
                int v2 = zzbct.zza(context0, map0, "y", 0);
                long v3 = SystemClock.uptimeMillis();
                MotionEvent motionEvent0 = MotionEvent.obtain(v3, v3, 0, ((float)v1), ((float)v2), 0);
                zzbav0.zze(motionEvent0);
                motionEvent0.recycle();
                return;
            }
            if("currentTime".equals(s)) {
                String s6 = (String)map0.get("time");
                if(s6 == null) {
                    zzawf.zzfa("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zzbav0.seekTo(((int)(Float.parseFloat(s6) * 1000.0f)));
                }
                catch(NumberFormatException unused_ex) {
                    String s7 = String.valueOf(s6);
                    zzawf.zzfa((s7.length() == 0 ? new String("Could not parse time parameter from currentTime video GMSG: ") : "Could not parse time parameter from currentTime video GMSG: " + s7));
                }
                return;
            }
            if("hide".equals(s)) {
                zzbav0.setVisibility(4);
                return;
            }
            if("load".equals(s)) {
                zzbav0.zzhv();
                return;
            }
            if("loadControl".equals(s)) {
                zzbct.zza(zzbav0, map0);
                return;
            }
            if("muted".equals(s)) {
                if(Boolean.parseBoolean(((String)map0.get("muted")))) {
                    zzbav0.zzyd();
                    return;
                }
                zzbav0.zzye();
                return;
            }
            if("pause".equals(s)) {
                zzbav0.pause();
                return;
            }
            if("play".equals(s)) {
                zzbav0.play();
                return;
            }
            if("show".equals(s)) {
                zzbav0.setVisibility(0);
                return;
            }
            if("src".equals(s)) {
                String s8 = (String)map0.get("src");
                String[] arr_s1 = {s8};
                String s9 = (String)map0.get("demuxed");
                if(s9 != null) {
                    try {
                        JSONArray jSONArray0 = new JSONArray(s9);
                        String[] arr_s2 = new String[jSONArray0.length()];
                        for(int v4 = 0; v4 < jSONArray0.length(); ++v4) {
                            arr_s2[v4] = jSONArray0.getString(v4);
                        }
                        arr_s1 = arr_s2;
                    }
                    catch(JSONException unused_ex) {
                        String s10 = String.valueOf(s9);
                        zzawf.zzfa((s10.length() == 0 ? new String("Malformed demuxed URL list for playback: ") : "Malformed demuxed URL list for playback: " + s10));
                        arr_s1 = new String[]{s8};
                    }
                }
                zzbav0.zzc(s8, arr_s1);
                return;
            }
            if("touchMove".equals(s)) {
                Context context1 = zzbbm0.getContext();
                zzbav0.zza(((float)zzbct.zza(context1, map0, "dx", 0)), ((float)zzbct.zza(context1, map0, "dy", 0)));
                if(!this.zzeec) {
                    zzbbm0.zztx();
                    this.zzeec = true;
                }
                return;
            }
            if("volume".equals(s)) {
                String s11 = (String)map0.get("volume");
                if(s11 == null) {
                    zzawf.zzfa("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zzbav0.setVolume(Float.parseFloat(s11));
                }
                catch(NumberFormatException unused_ex) {
                    String s12 = String.valueOf(s11);
                    zzawf.zzfa((s12.length() == 0 ? new String("Could not parse volume parameter from volume video GMSG: ") : "Could not parse volume parameter from volume video GMSG: " + s12));
                }
                return;
            }
            if("watermark".equals(s)) {
                zzbav0.zzyf();
                return;
            }
            String s13 = String.valueOf(s);
            zzawf.zzfa((s13.length() == 0 ? new String("Unknown video action: ") : "Unknown video action: " + s13));
            return;
        }
        Context context2 = zzbbm0.getContext();
        int v5 = zzbct.zza(context2, map0, "x", 0);
        int v6 = zzbct.zza(context2, map0, "y", 0);
        int v7 = zzbct.zza(context2, map0, "w", -1);
        int v8 = zzbct.zza(context2, map0, "h", -1);
        int v9 = Math.min(v7, zzbbm0.zzyy() - v5);
        int v10 = Math.min(v8, zzbbm0.zzyx() - v6);
        try {
            v11 = 0;
            v11 = Integer.parseInt(((String)map0.get("player")));
        }
        catch(NumberFormatException unused_ex) {
        }
        boolean z1 = Boolean.parseBoolean(((String)map0.get("spherical")));
        if(z && zzbbb0.zzyj() == null) {
            zzbbb0.zza(v5, v6, v9, v10, v11, z1, new zzbbj(((String)map0.get("flags"))));
            zzbav zzbav1 = zzbbb0.zzyj();
            if(zzbav1 != null) {
                zzbct.zza(zzbav1, map0);
            }
            return;
        }
        zzbbb0.zze(v5, v6, v9, v10);
    }
}

