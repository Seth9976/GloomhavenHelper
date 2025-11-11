package com.google.android.gms.internal.ads;

final class zzcnh implements Runnable {
    private final zzdei zzfgp;
    private final zzdeq zzfsg;
    private final zzcnf zzgbx;

    zzcnh(zzcnf zzcnf0, zzdeq zzdeq0, zzdei zzdei0) {
        this.zzgbx = zzcnf0;
        this.zzfsg = zzdeq0;
        this.zzfgp = zzdei0;
    }

    @Override
    public final void run() {
        this.zzgbx.zzd(this.zzfsg, this.zzfgp);
    }
}

