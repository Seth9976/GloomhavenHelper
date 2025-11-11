package com.google.android.gms.internal.ads;

import java.util.List;

abstract class zzdzr {
    private static final zzdzr zzhuq;
    private static final zzdzr zzhur;

    static {
        zzdzr.zzhuq = new zzdzt(null);
        zzdzr.zzhur = new zzdzw(null);
    }

    private zzdzr() {
    }

    zzdzr(zzdzu zzdzu0) {
    }

    abstract List zza(Object arg1, long arg2);

    abstract void zza(Object arg1, Object arg2, long arg3);

    abstract void zzb(Object arg1, long arg2);

    static zzdzr zzbdw() {
        return zzdzr.zzhuq;
    }

    static zzdzr zzbdx() {
        return zzdzr.zzhur;
    }
}

