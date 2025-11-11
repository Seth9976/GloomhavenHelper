package com.google.android.gms.internal.ads;

final class zzhx implements Runnable {
    private final zzhq zzahq;
    private final zzis zzahx;

    zzhx(zzhq zzhq0, zzis zzis0) {
        this.zzahq = zzhq0;
        this.zzahx = zzis0;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzd(this.zzahx);
    }
}

