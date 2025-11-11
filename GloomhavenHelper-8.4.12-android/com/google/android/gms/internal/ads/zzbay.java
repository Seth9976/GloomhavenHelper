package com.google.android.gms.internal.ads;

final class zzbay implements Runnable {
    private final zzbat zzdzt;

    private zzbay(zzbat zzbat0) {
        this.zzdzt = zzbat0;
    }

    @Override
    public final void run() {
        this.zzdzt.stop();
    }

    static Runnable zza(zzbat zzbat0) {
        return new zzbay(zzbat0);
    }
}

