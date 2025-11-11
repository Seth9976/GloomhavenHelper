package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbjg implements zzdng {
    private Map zzfdm;

    public zzbjg(Map map0) {
        this.zzfdm = map0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) throws Exception {
        for(Object object1: ((zzdeq)object0).zzgqm.zzgqk) {
            zzden zzden0 = (zzden)object1;
            if(this.zzfdm.containsKey(zzden0.name)) {
                ((zzbjj)this.zzfdm.get(zzden0.name)).zzk(zzden0.zzgqh);
            }
        }
        return zzdnt.zzaj(((zzdeq)object0));
    }
}

