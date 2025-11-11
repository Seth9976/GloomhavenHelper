package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Iterator;

public final class zzcqp implements zzcly {
    private final zzcmc zzfcu;
    private final zzdif zzfis;
    private final zzcmf zzgee;
    private final zzdoe zzgef;

    public zzcqp(zzdif zzdif0, zzdoe zzdoe0, zzcmc zzcmc0, zzcmf zzcmf0) {
        this.zzfis = zzdif0;
        this.zzgef = zzdoe0;
        this.zzgee = zzcmf0;
        this.zzfcu = zzcmc0;
    }

    // 检测为 Lambda 实现
    final Object zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0, Void void0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return !zzdei0.zzgpr.isEmpty();
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        zzcmd zzcmd0;
        Iterator iterator0 = zzdei0.zzgpr.iterator();
    label_1:
        if(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            String s = (String)object0;
            try {
                zzcmd0 = this.zzfcu.zzd(s, zzdei0.zzgpt);
            }
            catch(zzdfa unused_ex) {
                goto label_1;
            }
        }
        else {
            zzcmd0 = null;
        }
        if(zzcmd0 == null) {
            return zzdnt.immediateFailedFuture(new zzcos("unable to instantiate mediation adapter class"));
        }
        zzazy zzazy0 = new zzazy();
        zzcqq zzcqq0 = new zzcqq(this, zzazy0, zzcmd0);
        zzcmd0.zzgbd.zza(zzcqq0);
        if(zzdei0.zzdnk) {
            Bundle bundle0 = zzdeq0.zzgql.zzfir.zzgqq.zzcct;
            Bundle bundle1 = bundle0.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            if(bundle1 == null) {
                bundle1 = new Bundle();
                bundle0.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle1);
            }
            bundle1.putBoolean("render_test_ad_label", true);
        }
        return this.zzfis.zzu(zzdig.zzgvr).zza(() -> this.zzgee.zza(zzdeq0, zzdei0, zzcmd0), this.zzgef).zzw(zzdig.zzgvs).zze(zzazy0).zzw(zzdig.zzgvt).zzb((Void void0) -> this.zzgee.zzb(zzdeq0, zzdei0, zzcmd0)).zzata();
    }

    // 检测为 Lambda 实现
    final void zzd(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws Exception [...]
}

