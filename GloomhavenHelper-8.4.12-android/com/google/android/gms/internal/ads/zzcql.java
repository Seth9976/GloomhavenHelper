package com.google.android.gms.internal.ads;

import org.json.JSONObject;

public final class zzcql implements zzcmc {
    private final zzcro zzgec;

    public zzcql(zzcro zzcro0) {
        this.zzgec = zzcro0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmc
    public final zzcmd zzd(String s, JSONObject jSONObject0) throws zzdfa {
        zzanq zzanq0 = this.zzgec.zzgk(s);
        return zzanq0 == null ? null : new zzcmd(zzanq0, new zzcni(), s);
    }
}

