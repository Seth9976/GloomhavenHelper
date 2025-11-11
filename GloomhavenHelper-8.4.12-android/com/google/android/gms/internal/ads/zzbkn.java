package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzbkn implements Runnable {
    private final JSONObject zzfej;
    private final zzbkk zzffl;

    zzbkn(zzbkk zzbkk0, JSONObject jSONObject0) {
        this.zzffl = zzbkk0;
        this.zzfej = jSONObject0;
    }

    @Override
    public final void run() {
        this.zzffl.zzi(this.zzfej);
    }
}

