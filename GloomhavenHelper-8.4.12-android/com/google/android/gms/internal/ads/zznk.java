package com.google.android.gms.internal.ads;

public final class zznk {
    public final zzmu zzben;
    public final zznf zzbeo;
    public final Object zzbep;
    public final zzhg[] zzbeq;

    public zznk(zzmu zzmu0, zznf zznf0, Object object0, zzhg[] arr_zzhg) {
        this.zzben = zzmu0;
        this.zzbeo = zznf0;
        this.zzbep = object0;
        this.zzbeq = arr_zzhg;
    }

    // 去混淆评级： 低(30)
    public final boolean zza(zznk zznk0, int v) {
        return zznk0 == null ? false : zzop.zza(this.zzbeo.zzay(v), zznk0.zzbeo.zzay(v)) && zzop.zza(this.zzbeq[v], zznk0.zzbeq[v]);
    }
}

