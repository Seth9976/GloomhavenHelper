package com.google.android.gms.internal.ads;

final class zzdib implements Runnable {
    private final zzdhx zzgva;
    private final zzdhs zzgvb;

    zzdib(zzdhx zzdhx0, zzdhs zzdhs0) {
        this.zzgva = zzdhx0;
        this.zzgvb = zzdhs0;
    }

    @Override
    public final void run() {
        this.zzgva.zzguv.zzguq.zzb(this.zzgvb);
    }
}

