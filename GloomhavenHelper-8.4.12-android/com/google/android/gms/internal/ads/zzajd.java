package com.google.android.gms.internal.ads;

final class zzajd implements Runnable {
    private final zzaja zzdap;
    private final zzdq zzdaq;
    private final zzajv zzdar;

    zzajd(zzaja zzaja0, zzdq zzdq0, zzajv zzajv0) {
        this.zzdap = zzaja0;
        this.zzdaq = zzdq0;
        this.zzdar = zzajv0;
    }

    @Override
    public final void run() {
        this.zzdap.zza(this.zzdaq, this.zzdar);
    }
}

