package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

final class zzckg implements Callable {
    private final zzdof zzfjy;
    private final zzdof zzfsq;

    zzckg(zzdof zzdof0, zzdof zzdof1) {
        this.zzfsq = zzdof0;
        this.zzfjy = zzdof1;
    }

    @Override
    public final Object call() {
        return new zzckz(((JSONObject)this.zzfsq.get()), ((zzard)this.zzfjy.get()));
    }
}

