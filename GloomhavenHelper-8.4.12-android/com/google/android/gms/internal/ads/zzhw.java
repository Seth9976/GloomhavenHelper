package com.google.android.gms.internal.ads;

final class zzhw implements Runnable {
    private final zzhq zzahq;
    private final int zzahw;

    zzhw(zzhq zzhq0, int v) {
        this.zzahq = zzhq0;
        this.zzahw = v;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzs(this.zzahw);
    }
}

