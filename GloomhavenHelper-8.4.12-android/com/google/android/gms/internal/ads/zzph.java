package com.google.android.gms.internal.ads;

final class zzph implements Runnable {
    private final String zzahn;
    private final long zzaho;
    private final long zzahp;
    private final zzpf zzbjp;

    zzph(zzpf zzpf0, String s, long v, long v1) {
        this.zzbjp = zzpf0;
        this.zzahn = s;
        this.zzaho = v;
        this.zzahp = v1;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzd(this.zzahn, this.zzaho, this.zzahp);
    }
}

