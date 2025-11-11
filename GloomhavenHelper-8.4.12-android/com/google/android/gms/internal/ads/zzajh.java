package com.google.android.gms.internal.ads;

final class zzajh implements Runnable {
    private final zzaja zzdap;
    private final zzajv zzdas;
    private final zzair zzdat;

    zzajh(zzaja zzaja0, zzajv zzajv0, zzair zzair0) {
        this.zzdap = zzaja0;
        this.zzdas = zzajv0;
        this.zzdat = zzair0;
    }

    @Override
    public final void run() {
        this.zzdap.zza(this.zzdas, this.zzdat);
    }
}

