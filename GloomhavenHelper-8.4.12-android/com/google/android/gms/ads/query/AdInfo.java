package com.google.android.gms.ads.query;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzazh;
import org.json.JSONException;
import org.json.JSONObject;

@KeepForSdk
public class AdInfo {
    private final QueryInfo zzgwj;
    private final String zzgwk;

    @KeepForSdk
    public AdInfo(QueryInfo queryInfo0, String s) {
        this.zzgwj = queryInfo0;
        this.zzgwk = s;
    }

    @KeepForSdk
    public String getAdString() {
        return this.zzgwk;
    }

    @KeepForSdk
    public QueryInfo getQueryInfo() {
        return this.zzgwj;
    }

    @KeepForSdk
    public static String getRequestId(String s) {
        if(s == null) {
            zzazh.zzfa("adString passed to AdInfo.getRequestId() cannot be null. Returning empty string.");
            return "";
        }
        try {
            return new JSONObject(s).optString("request_id", "");
        }
        catch(JSONException unused_ex) {
            zzazh.zzfa("Invalid adString passed to AdInfo.getRequestId(). Returning empty string.");
            return "";
        }
    }
}

