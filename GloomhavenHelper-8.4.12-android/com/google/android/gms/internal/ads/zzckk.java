package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzckk implements zzdhq {
    static final zzdhq zzfxq;

    static {
        zzckk.zzfxq = new zzckk();
    }

    @Override  // com.google.android.gms.internal.ads.zzdhq
    public final Object apply(Object object0) {
        zzawf.zzee("Ad request signals:");
        zzawf.zzee(((JSONObject)object0).toString(2));
        return (JSONObject)object0;
    }
}

