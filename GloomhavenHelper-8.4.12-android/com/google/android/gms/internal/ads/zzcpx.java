package com.google.android.gms.internal.ads;

final class zzcpx implements Runnable {
    private final zzcer zzgcl;

    private zzcpx(zzcer zzcer0) {
        this.zzgcl = zzcer0;
    }

    @Override
    public final void run() {
        this.zzgcl.zzamg();
    }

    static Runnable zza(zzcer zzcer0) {
        return new zzcpx(zzcer0);
    }
}

