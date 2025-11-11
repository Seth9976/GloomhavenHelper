package com.google.android.gms.internal.ads;

import org.json.JSONException;

public final class zzbqc extends zzxh {
    private final String zzfke;
    private final String zzfkf;

    public zzbqc(zzdei zzdei0, String s) {
        this.zzfkf = zzdei0 == null ? null : zzdei0.zzfkf;
        String s1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(s) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(s) ? zzbqc.zzb(zzdei0) : null;
        if(s1 == null) {
            s1 = s;
        }
        this.zzfke = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzxe
    public final String getMediationAdapterClassName() {
        return this.zzfke;
    }

    @Override  // com.google.android.gms.internal.ads.zzxe
    public final String getResponseId() {
        return this.zzfkf;
    }

    private static String zzb(zzdei zzdei0) {
        try {
            return zzdei0.zzgpt.getString("class_name");
        }
        catch(JSONException unused_ex) {
            return null;
        }
    }
}

