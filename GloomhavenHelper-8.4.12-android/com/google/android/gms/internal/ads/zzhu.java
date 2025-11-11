package com.google.android.gms.internal.ads;

final class zzhu implements Runnable {
    private final zzhq zzahq;
    private final int zzahs;
    private final long zzaht;
    private final long zzahu;

    zzhu(zzhq zzhq0, int v, long v1, long v2) {
        this.zzahq = zzhq0;
        this.zzahs = v;
        this.zzaht = v1;
        this.zzahu = v2;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzb(this.zzahs, this.zzaht, this.zzahu);
    }
}

