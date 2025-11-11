package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzdhx {
    private final Object zzgur;
    @Nullable
    private final String zzgus;
    private final List zzguu;
    final zzdhr zzguv;
    private final zzdof zzguw;
    private final zzdof zzgux;

    private zzdhx(zzdhr zzdhr0, Object object0, String s, zzdof zzdof0, List list0, zzdof zzdof1) {
        this.zzguv = zzdhr0;
        super();
        this.zzgur = object0;
        this.zzgus = s;
        this.zzguw = zzdof0;
        this.zzguu = list0;
        this.zzgux = zzdof1;
    }

    zzdhx(zzdhr zzdhr0, Object object0, String s, zzdof zzdof0, List list0, zzdof zzdof1, zzdhu zzdhu0) {
        this(zzdhr0, object0, null, zzdof0, list0, zzdof1);
    }

    private final zzdhx zza(zzdng zzdng0, Executor executor0) {
        zzdof zzdof0 = zzdnt.zzb(this.zzgux, zzdng0, executor0);
        return new zzdhx(this.zzguv, this.zzgur, this.zzgus, this.zzguw, this.zzguu, zzdof0);
    }

    public final zzdhx zza(long v, TimeUnit timeUnit0) {
        ScheduledExecutorService scheduledExecutorService0 = this.zzguv.zzffm;
        zzdof zzdof0 = zzdnt.zza(this.zzgux, v, timeUnit0, scheduledExecutorService0);
        return new zzdhx(this.zzguv, this.zzgur, this.zzgus, this.zzguw, this.zzguu, zzdof0);
    }

    public final zzdhx zza(zzdng zzdng0) {
        return this.zza(zzdng0, this.zzguv.zzfrv);
    }

    public final zzdhx zza(Class class0, zzdhq zzdhq0) {
        return this.zza(class0, new zzdic(zzdhq0));
    }

    public final zzdhx zza(Class class0, zzdng zzdng0) {
        zzdof zzdof0 = zzdnt.zzb(this.zzgux, class0, zzdng0, this.zzguv.zzfrv);
        return new zzdhx(this.zzguv, this.zzgur, this.zzgus, this.zzguw, this.zzguu, zzdof0);
    }

    public final zzdhs zzata() {
        String s = this.zzgus == null ? this.zzguv.zzv(this.zzgur) : this.zzgus;
        zzdhs zzdhs0 = new zzdhs(this.zzgur, s, this.zzgux);
        this.zzguv.zzguq.zza(zzdhs0);
        zzdib zzdib0 = new zzdib(this, zzdhs0);
        this.zzguw.addListener(zzdib0, zzazq.zzdxp);
        zzdnt.zza(zzdhs0, new zzdie(this, zzdhs0), zzazq.zzdxp);
        return zzdhs0;
    }

    public final zzdhx zzb(zzdhq zzdhq0) {
        return this.zza(new zzdia(zzdhq0));
    }

    public final zzdhx zze(zzdof zzdof0) {
        return this.zza(new zzdhz(zzdof0), zzazq.zzdxp);
    }

    public final zzdhx zzgq(String s) {
        return new zzdhx(this.zzguv, this.zzgur, s, this.zzguw, this.zzguu, this.zzgux);
    }

    public final zzdhx zzw(Object object0) {
        zzdhs zzdhs0 = this.zzata();
        return this.zzguv.zza(object0, zzdhs0);
    }
}

