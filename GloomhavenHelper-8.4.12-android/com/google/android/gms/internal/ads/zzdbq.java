package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzdbq implements zzdng {
    private final zzdbn zzgni;
    private final zzdgk zzgnj;
    private final zzbod zzgnk;

    zzdbq(zzdbn zzdbn0, zzdgk zzdgk0, zzbod zzbod0) {
        this.zzgni = zzdbn0;
        this.zzgnj = zzdgk0;
        this.zzgnk = zzbod0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        zzbod zzbod0 = this.zzgnk;
        this.zzgnj.zzenb = (zzdeq)object0;
        int v = 0;
        int v1 = 0;
        Iterator iterator0 = ((zzdeq)object0).zzgqm.zzgqi.iterator();
    alab1:
        while(true) {
            if(!iterator0.hasNext()) {
                v = v1;
                break;
            }
            Object object1 = iterator0.next();
            for(Object object2: ((zzdei)object1).zzgpk) {
                if(!((String)object2).contains("FirstPartyRenderer")) {
                    break alab1;
                }
                v1 = 1;
            }
        }
        return v == 0 ? zzdnt.zzaj(null) : zzbod0.zzb(zzdnt.zzaj(((zzdeq)object0)));
    }
}

