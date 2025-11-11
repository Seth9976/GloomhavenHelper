package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzcca implements zzpt {
    private final zzbdv zzeiw;

    zzcca(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("isVisible", (zzpu0.zzbnz ? "1" : "0"));
        this.zzeiw.zza("onAdVisibilityChanged", hashMap0);
    }
}

