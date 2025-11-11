package com.google.android.gms.internal.ads;

public final class zzdfw {
    private zzdfv zzgrl;
    private zzdfv zzgrm;
    private zzdfv zzgrn;
    private zzdfv zzgro;
    private zzdfv zzgrp;
    private zzdfv zzgrq;
    private zzdfv zzgrr;
    private zzdfv zzgrs;

    public zzdfw() {
        this.zzgrl = null;
        this.zzgrm = null;
        this.zzgrn = null;
        this.zzgro = null;
        this.zzgrp = null;
        this.zzgrq = null;
        this.zzgrr = null;
        this.zzgrs = null;
    }

    public final void onAdClosed() {
        zzdfv zzdfv0 = this.zzgro;
        if(zzdfv0 != null) {
            zzdfv0.execute();
        }
    }

    public final void zza(zzdfv zzdfv0) {
        this.zzgro = zzdfv0;
    }
}

