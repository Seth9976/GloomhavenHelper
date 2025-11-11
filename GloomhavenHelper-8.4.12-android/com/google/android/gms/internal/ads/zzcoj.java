package com.google.android.gms.internal.ads;

import org.json.JSONArray;

final class zzcoj implements zzdng {
    private final zzdei zzfgp;
    private final zzdeq zzfsg;
    private final zzcoh zzgcq;

    zzcoj(zzcoh zzcoh0, zzdeq zzdeq0, zzdei zzdei0) {
        this.zzgcq = zzcoh0;
        this.zzfsg = zzdeq0;
        this.zzfgp = zzdei0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        return this.zzgcq.zza(this.zzfsg, this.zzfgp, ((JSONArray)object0));
    }
}

