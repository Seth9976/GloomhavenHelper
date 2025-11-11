package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzbzc extends zzbzd {
    private final boolean zzdec;
    private final boolean zzded;
    private final boolean zzdmn;
    private final JSONObject zzfpp;
    private final boolean zzfpq;

    public zzbzc(zzdei zzdei0, JSONObject jSONObject0) {
        super(zzdei0);
        boolean z = false;
        this.zzfpp = zzayf.zza(jSONObject0, new String[]{"tracking_urls_and_actions", "active_view"});
        this.zzded = zzayf.zza(false, jSONObject0, new String[]{"allow_pub_owned_ad_view"});
        this.zzdec = zzayf.zza(false, jSONObject0, new String[]{"attribution", "allow_pub_rendering"});
        this.zzdmn = zzayf.zza(false, jSONObject0, new String[]{"enable_omid"});
        if(jSONObject0 != null && jSONObject0.optJSONObject("overlay") != null) {
            z = true;
        }
        this.zzfpq = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzd
    public final boolean zzaka() {
        return this.zzdmn;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzd
    public final JSONObject zzakp() {
        JSONObject jSONObject0 = this.zzfpp;
        if(jSONObject0 != null) {
            return jSONObject0;
        }
        try {
            return new JSONObject(this.zzfpr.zzdlx);
        }
        catch(JSONException unused_ex) {
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzd
    public final boolean zzakq() {
        return this.zzfpq;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzd
    public final boolean zzakr() {
        return this.zzded;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzd
    public final boolean zzaks() {
        return this.zzdec;
    }
}

