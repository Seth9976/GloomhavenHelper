package com.google.android.gms.internal.ads;

import java.util.List;

final class zzdzw extends zzdzr {
    private zzdzw() {
        super(null);
    }

    zzdzw(zzdzu zzdzu0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final List zza(Object object0, long v) {
        List list0 = zzdzw.zzd(object0, v);
        if(!((zzdzi)list0).zzbam()) {
            int v1 = ((zzdzi)list0).size();
            list0 = ((zzdzi)list0).zzfd((v1 == 0 ? 10 : v1 << 1));
            zzecb.zza(object0, v, list0);
        }
        return list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final void zza(Object object0, Object object1, long v) {
        zzdzi zzdzi0 = zzdzw.zzd(object0, v);
        zzdzi zzdzi1 = zzdzw.zzd(object1, v);
        int v1 = zzdzi0.size();
        int v2 = zzdzi1.size();
        if(v1 > 0 && v2 > 0) {
            if(!zzdzi0.zzbam()) {
                zzdzi0 = zzdzi0.zzfd(v2 + v1);
            }
            zzdzi0.addAll(zzdzi1);
        }
        if(v1 > 0) {
            zzdzi1 = zzdzi0;
        }
        zzecb.zza(object0, v, zzdzi1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzr
    final void zzb(Object object0, long v) {
        zzdzw.zzd(object0, v).zzban();
    }

    private static zzdzi zzd(Object object0, long v) {
        return (zzdzi)zzecb.zzp(object0, v);
    }
}

