package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class zzdhv {
    private final Object zzgur;
    private final zzdhr zzguv;

    private zzdhv(zzdhr zzdhr0, Object object0) {
        this.zzguv = zzdhr0;
        super();
        this.zzgur = object0;
    }

    zzdhv(zzdhr zzdhr0, Object object0, zzdhu zzdhu0) {
        this(zzdhr0, object0);
    }

    private final zzdhx zza(Callable callable0, zzdoe zzdoe0) {
        zzdof zzdof0 = zzdhr.zzgup;
        List list0 = Collections.emptyList();
        zzdof zzdof1 = zzdoe0.zzd(callable0);
        return new zzdhx(this.zzguv, this.zzgur, null, zzdof0, list0, zzdof1, null);
    }

    public final zzdhx zza(zzdhp zzdhp0, zzdoe zzdoe0) {
        return this.zza(new zzdhy(zzdhp0), zzdoe0);
    }

    public final zzdhx zzc(Callable callable0) {
        return this.zza(callable0, this.zzguv.zzfrv);
    }

    public final zzdhx zze(zzdof zzdof0) {
        List list0 = Collections.emptyList();
        return new zzdhx(this.zzguv, this.zzgur, null, zzdhr.zzgup, list0, zzdof0, null);
    }
}

