package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

final class zzckj implements Callable {
    private final zzdof zzfig;
    private final zzdof zzfjy;
    private final zzdof zzfsq;

    zzckj(zzdof zzdof0, zzdof zzdof1, zzdof zzdof2) {
        this.zzfsq = zzdof0;
        this.zzfjy = zzdof1;
        this.zzfig = zzdof2;
    }

    @Override
    public final Object call() {
        return new zzckv(((zzcky)this.zzfsq.get()), ((JSONObject)this.zzfjy.get()), ((zzard)this.zzfig.get()));
    }
}

