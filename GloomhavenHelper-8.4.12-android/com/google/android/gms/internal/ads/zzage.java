package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONObject;

final class zzage implements zzagh {
    private final zzazy zzcyv;

    zzage(zzagf zzagf0, zzazy zzazy0) {
        this.zzcyv = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void onFailure(@Nullable String s) {
        zzakd zzakd0 = new zzakd(s);
        this.zzcyv.setException(zzakd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void zzc(JSONObject jSONObject0) {
        this.zzcyv.set(jSONObject0);
    }
}

