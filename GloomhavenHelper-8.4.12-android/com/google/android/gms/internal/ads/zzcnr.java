package com.google.android.gms.internal.ads;

final class zzcnr implements Runnable {
    private final zzcer zzgcl;

    private zzcnr(zzcer zzcer0) {
        this.zzgcl = zzcer0;
    }

    @Override
    public final void run() {
        this.zzgcl.zzamg();
    }

    static Runnable zza(zzcer zzcer0) {
        return new zzcnr(zzcer0);
    }
}

