package com.google.android.gms.internal.ads;

final class zzcqg implements Runnable {
    private final zzdei zzfgp;
    private final zzdeq zzfsg;
    private final zzcqh zzgdr;
    private final zzcmd zzgds;

    zzcqg(zzcqh zzcqh0, zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) {
        this.zzgdr = zzcqh0;
        this.zzfsg = zzdeq0;
        this.zzfgp = zzdei0;
        this.zzgds = zzcmd0;
    }

    @Override
    public final void run() {
        zzcqf.zza(this.zzgdr.zzgdw, this.zzfsg, this.zzfgp, this.zzgds);
    }
}

