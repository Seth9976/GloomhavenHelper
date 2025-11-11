package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

public final class zzdht {
    private final Object zzgur;
    private final List zzguu;
    private final zzdhr zzguv;

    private zzdht(zzdhr zzdhr0, Object object0, List list0) {
        this.zzguv = zzdhr0;
        super();
        this.zzgur = object0;
        this.zzguu = list0;
    }

    zzdht(zzdhr zzdhr0, Object object0, List list0, zzdhu zzdhu0) {
        this(zzdhr0, object0, list0);
    }

    public final zzdhx zzb(Callable callable0) {
        zzdny zzdny0 = zzdnt.zzi(this.zzguu);
        zzdof zzdof0 = zzdny0.zza(zzdhw.zzgjg, zzazq.zzdxp);
        zzdof zzdof1 = zzdny0.zza(callable0, this.zzguv.zzfrv);
        return new zzdhx(this.zzguv, this.zzgur, null, zzdof0, this.zzguu, zzdof1, null);
    }
}

