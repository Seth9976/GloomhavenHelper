package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public class zzaow {
    private final zzbdv zzdae;
    private final String zzdhh;

    public zzaow(zzbdv zzbdv0) {
        this(zzbdv0, "");
    }

    public zzaow(zzbdv zzbdv0, String s) {
        this.zzdae = zzbdv0;
        this.zzdhh = s;
    }

    public final void zza(int v, int v1, int v2, int v3) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("x", v).put("y", v1).put("width", v2).put("height", v3);
            this.zzdae.zza("onSizeChanged", jSONObject0);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while dispatching size change.", jSONException0);
        }
    }

    public final void zza(int v, int v1, int v2, int v3, float f, int v4) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("width", v).put("height", v1).put("maxSizeWidth", v2).put("maxSizeHeight", v3).put("density", ((double)f)).put("rotation", v4);
            this.zzdae.zza("onScreenInfoChanged", jSONObject0);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while obtaining screen information.", jSONException0);
        }
    }

    public final void zzb(int v, int v1, int v2, int v3) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("x", v).put("y", v1).put("width", v2).put("height", v3);
            this.zzdae.zza("onDefaultPositionReceived", jSONObject0);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while dispatching default position.", jSONException0);
        }
    }

    public final void zzdt(String s) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("message", s).put("action", this.zzdhh);
            if(this.zzdae != null) {
                this.zzdae.zza("onError", jSONObject0);
            }
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while dispatching error event.", jSONException0);
        }
    }

    public final void zzdu(String s) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("js", s);
            this.zzdae.zza("onReadyEventReceived", jSONObject0);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while dispatching ready Event.", jSONException0);
        }
    }

    public final void zzdv(String s) {
        try {
            JSONObject jSONObject0 = new JSONObject().put("state", s);
            this.zzdae.zza("onStateChanged", jSONObject0);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while dispatching state change.", jSONException0);
        }
    }
}

