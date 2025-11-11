package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzakj {
    private static final Charset UTF_8;
    public static final zzakg zzdbu;
    public static final zzake zzdbv;

    static {
        zzakj.UTF_8 = Charset.forName("UTF-8");
        zzakj.zzdbu = new zzakl();
        zzakj.zzdbv = zzaki.zzdbt;
    }

    static final InputStream zze(JSONObject jSONObject0) throws JSONException {
        return new ByteArrayInputStream(jSONObject0.toString().getBytes(zzakj.UTF_8));
    }
}

