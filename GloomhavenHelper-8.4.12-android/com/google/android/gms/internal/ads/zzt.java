package com.google.android.gms.internal.ads;

final class zzt implements Runnable {
    private final String zzas;
    private final long zzat;
    private final zzq zzau;

    zzt(zzq zzq0, String s, long v) {
        this.zzau = zzq0;
        this.zzas = s;
        this.zzat = v;
        super();
    }

    @Override
    public final void run() {
        this.zzau.zzae.zza(this.zzas, this.zzat);
        this.zzau.zzae.zzc(this.zzau.toString());
    }
}

