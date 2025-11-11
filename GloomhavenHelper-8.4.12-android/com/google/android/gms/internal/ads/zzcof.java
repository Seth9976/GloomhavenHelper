package com.google.android.gms.internal.ads;

import org.json.JSONObject;

public final class zzcof implements zzcmc {
    private final zzcnk zzfws;

    public zzcof(zzcnk zzcnk0) {
        this.zzfws = zzcnk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmc
    public final zzcmd zzd(String s, JSONObject jSONObject0) throws zzdfa {
        zzdfb zzdfb0 = this.zzfws.zze(s, jSONObject0);
        return zzdfb0 == null ? null : new zzcmd(zzdfb0, new zzcni(), s);
    }
}

