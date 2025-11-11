package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

final class zzcok implements Callable {
    private final zzdof zzfig;
    private final zzdof zzfjy;
    private final zzdeq zzgck;
    private final zzcoh zzgcq;
    private final zzdei zzgct;
    private final JSONObject zzgcu;

    zzcok(zzcoh zzcoh0, zzdof zzdof0, zzdof zzdof1, zzdeq zzdeq0, zzdei zzdei0, JSONObject jSONObject0) {
        this.zzgcq = zzcoh0;
        this.zzfjy = zzdof0;
        this.zzfig = zzdof1;
        this.zzgck = zzdeq0;
        this.zzgct = zzdei0;
        this.zzgcu = jSONObject0;
    }

    @Override
    public final Object call() {
        return this.zzgcq.zza(this.zzfjy, this.zzfig, this.zzgck, this.zzgct, this.zzgcu);
    }
}

