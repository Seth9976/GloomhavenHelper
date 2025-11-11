package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzbka implements Runnable {
    private final zzbdv zzeiw;
    private final JSONObject zzfej;

    zzbka(zzbdv zzbdv0, JSONObject jSONObject0) {
        this.zzeiw = zzbdv0;
        this.zzfej = jSONObject0;
    }

    @Override
    public final void run() {
        this.zzeiw.zzb("AFMA_updateActiveView", this.zzfej);
    }
}

