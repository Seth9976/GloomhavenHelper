package com.google.android.gms.internal.ads;

final class zzbes implements Runnable {
    private final int zzdul;
    private final int zzdum;
    private final boolean zzegk;
    private final boolean zzegl;
    private final zzbeq zzeik;

    zzbes(zzbeq zzbeq0, int v, int v1, boolean z, boolean z1) {
        this.zzeik = zzbeq0;
        this.zzdul = v;
        this.zzdum = v1;
        this.zzegk = z;
        this.zzegl = z1;
    }

    @Override
    public final void run() {
        this.zzeik.zzb(this.zzdul, this.zzdum, this.zzegk, this.zzegl);
    }
}

