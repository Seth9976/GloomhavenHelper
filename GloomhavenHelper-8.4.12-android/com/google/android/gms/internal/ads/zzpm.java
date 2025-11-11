package com.google.android.gms.internal.ads;

final class zzpm implements Runnable {
    private final zzpf zzbjp;
    private final int zzbjt;
    private final int zzbju;
    private final int zzbjv;
    private final float zzbjw;

    zzpm(zzpf zzpf0, int v, int v1, int v2, float f) {
        this.zzbjp = zzpf0;
        this.zzbjt = v;
        this.zzbju = v1;
        this.zzbjv = v2;
        this.zzbjw = f;
        super();
    }

    @Override
    public final void run() {
        this.zzbjp.zzbjo.zzb(this.zzbjt, this.zzbju, this.zzbjv, this.zzbjw);
    }
}

