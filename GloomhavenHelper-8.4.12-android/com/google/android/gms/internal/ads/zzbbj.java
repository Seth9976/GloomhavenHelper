package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzbbj {
    public final boolean zzeak;
    public final int zzeal;
    public final int zzeam;
    public final int zzean;
    private final String zzeao;
    public final int zzeap;
    public final int zzeaq;
    public final int zzear;
    public final int zzeas;
    public final boolean zzeat;
    public final int zzeau;

    public zzbbj(String s) {
        JSONObject jSONObject0 = null;
        if(s != null) {
            try {
                jSONObject0 = new JSONObject(s);
            }
            catch(JSONException unused_ex) {
            }
        }
        this.zzeak = zzbbj.zza(jSONObject0, "aggressive_media_codec_release", zzzx.zzcia);
        this.zzeal = zzbbj.zzb(jSONObject0, "byte_buffer_precache_limit", zzzx.zzchj);
        this.zzeam = zzbbj.zzb(jSONObject0, "exo_cache_buffer_size", zzzx.zzchp);
        this.zzean = zzbbj.zzb(jSONObject0, "exo_connect_timeout_millis", zzzx.zzchf);
        this.zzeao = zzbbj.zzc(jSONObject0, "exo_player_version", zzzx.zzche);
        this.zzeap = zzbbj.zzb(jSONObject0, "exo_read_timeout_millis", zzzx.zzchg);
        this.zzeaq = zzbbj.zzb(jSONObject0, "load_check_interval_bytes", zzzx.zzchh);
        this.zzear = zzbbj.zzb(jSONObject0, "player_precache_limit", zzzx.zzchi);
        this.zzeas = zzbbj.zzb(jSONObject0, "socket_receive_buffer_size", zzzx.zzchk);
        this.zzeat = zzbbj.zza(jSONObject0, "use_cache_data_source", zzzx.zzcnz);
        this.zzeau = zzbbj.zzb(jSONObject0, "min_retry_count", zzzx.zzchm);
    }

    private static boolean zza(JSONObject jSONObject0, String s, zzzi zzzi0) {
        return zzbbj.zza(jSONObject0, s, ((Boolean)zzvh.zzpd().zzd(zzzi0)).booleanValue());
    }

    private static boolean zza(JSONObject jSONObject0, String s, boolean z) {
        if(jSONObject0 != null) {
            try {
                return jSONObject0.getBoolean(s);
            }
            catch(JSONException unused_ex) {
            }
        }
        return z;
    }

    private static int zzb(JSONObject jSONObject0, String s, zzzi zzzi0) {
        if(jSONObject0 != null) {
            try {
                return jSONObject0.getInt(s);
            }
            catch(JSONException unused_ex) {
            }
        }
        return (int)(((Integer)zzvh.zzpd().zzd(zzzi0)));
    }

    private static String zzc(JSONObject jSONObject0, String s, zzzi zzzi0) {
        if(jSONObject0 != null) {
            try {
                return jSONObject0.getString(s);
            }
            catch(JSONException unused_ex) {
            }
        }
        return (String)zzvh.zzpd().zzd(zzzi0);
    }
}

