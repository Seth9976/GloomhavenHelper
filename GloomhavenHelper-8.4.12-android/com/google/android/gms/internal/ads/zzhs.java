package com.google.android.gms.internal.ads;

final class zzhs implements Runnable {
    private final String zzahn;
    private final long zzaho;
    private final long zzahp;
    private final zzhq zzahq;

    zzhs(zzhq zzhq0, String s, long v, long v1) {
        this.zzahq = zzhq0;
        this.zzahn = s;
        this.zzaho = v;
        this.zzahp = v1;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzb(this.zzahn, this.zzaho, this.zzahp);
    }
}

