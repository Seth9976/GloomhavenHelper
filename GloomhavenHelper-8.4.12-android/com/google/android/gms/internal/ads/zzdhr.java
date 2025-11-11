package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

public abstract class zzdhr {
    private final ScheduledExecutorService zzffm;
    private final zzdoe zzfrv;
    private static final zzdof zzgup;
    private final zzdid zzguq;

    static {
        zzdhr.zzgup = zzdnt.zzaj(null);
    }

    public zzdhr(zzdoe zzdoe0, ScheduledExecutorService scheduledExecutorService0, zzdid zzdid0) {
        this.zzfrv = zzdoe0;
        this.zzffm = scheduledExecutorService0;
        this.zzguq = zzdid0;
    }

    public final zzdht zza(Object object0, zzdof[] arr_zzdof) {
        return new zzdht(this, object0, Arrays.asList(arr_zzdof), null);
    }

    public final zzdhx zza(Object object0, zzdof zzdof0) {
        return new zzdhx(this, object0, null, zzdof0, Collections.singletonList(zzdof0), zzdof0, null);
    }

    public final zzdhv zzu(Object object0) {
        return new zzdhv(this, object0, null);
    }

    protected abstract String zzv(Object arg1);
}

