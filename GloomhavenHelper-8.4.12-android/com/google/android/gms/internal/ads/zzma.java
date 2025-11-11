package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

public final class zzma implements zzmd, zzme {
    private final Uri uri;
    private final zzdkp zzada;
    private final zzhl zzade;
    private zzhj zzadl;
    private final int zzbac;
    private final zzlz zzbad;
    private zzmd zzbae;
    private final String zzbag;
    private final zznn zzbbk;
    private final zzjh zzbbl;
    private final int zzbbm;
    private boolean zzbbn;

    public zzma(Uri uri0, zznn zznn0, zzjh zzjh0, int v, zzdkp zzdkp0, zzlz zzlz0, String s, int v1) {
        this.uri = uri0;
        this.zzbbk = zznn0;
        this.zzbbl = zzjh0;
        this.zzbac = v;
        this.zzada = zzdkp0;
        this.zzbad = zzlz0;
        this.zzbag = null;
        this.zzbbm = v1;
        this.zzade = new zzhl();
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final zzmc zza(int v, zznm zznm0) {
        zzob.checkArgument(v == 0);
        zzno zzno0 = this.zzbbk.zzim();
        zzjg[] arr_zzjg = this.zzbbl.zzgo();
        return new zzls(this.uri, zzno0, arr_zzjg, this.zzbac, this.zzada, this.zzbad, this, zznm0, null, this.zzbbm);
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zza(zzgn zzgn0, boolean z, zzmd zzmd0) {
        this.zzbae = zzmd0;
        this.zzadl = new zzms(0x8000000000000001L, false);
        zzmd0.zzb(this.zzadl, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzmd
    public final void zzb(zzhj zzhj0, Object object0) {
        boolean z = zzhj0.zza(0, this.zzade, false).zzagy != 0x8000000000000001L;
        if(this.zzbbn && !z) {
            return;
        }
        this.zzadl = zzhj0;
        this.zzbbn = z;
        this.zzbae.zzb(this.zzadl, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzb(zzmc zzmc0) {
        ((zzls)zzmc0).release();
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzhw() throws IOException {
    }

    @Override  // com.google.android.gms.internal.ads.zzme
    public final void zzhx() {
        this.zzbae = null;
    }
}

