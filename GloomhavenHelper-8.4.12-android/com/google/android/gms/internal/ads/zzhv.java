package com.google.android.gms.internal.ads;

final class zzhv implements Runnable {
    private final zzhq zzahq;
    private final zzgz zzahv;

    zzhv(zzhq zzhq0, zzgz zzgz0) {
        this.zzahq = zzhq0;
        this.zzahv = zzgz0;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzc(this.zzahv);
    }
}

