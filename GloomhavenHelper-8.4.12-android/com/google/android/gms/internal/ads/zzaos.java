package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzaos {
    private final boolean zzdhc;
    private final boolean zzdhd;
    private final boolean zzdhe;
    private final boolean zzdhf;
    private final boolean zzdhg;

    private zzaos(zzaou zzaou0) {
        this.zzdhc = zzaou0.zzdhc;
        this.zzdhd = zzaou0.zzdhd;
        this.zzdhe = zzaou0.zzdhe;
        this.zzdhf = zzaou0.zzdhf;
        this.zzdhg = zzaou0.zzdhg;
    }

    zzaos(zzaou zzaou0, zzaov zzaov0) {
        this(zzaou0);
    }

    public final JSONObject zztm() {
        try {
            return new JSONObject().put("sms", this.zzdhc).put("tel", this.zzdhd).put("calendar", this.zzdhe).put("storePicture", this.zzdhf).put("inlineVideo", this.zzdhg);
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occured while obtaining the MRAID capabilities.", jSONException0);
            return null;
        }
    }
}

