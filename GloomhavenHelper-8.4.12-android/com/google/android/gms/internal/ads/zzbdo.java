package com.google.android.gms.internal.ads;

final class zzbdo implements Runnable {
    private final boolean zzdzs;
    private final long zzect;
    private final zzbbm zzeff;

    zzbdo(zzbbm zzbbm0, boolean z, long v) {
        this.zzeff = zzbbm0;
        this.zzdzs = z;
        this.zzect = v;
    }

    @Override
    public final void run() {
        this.zzeff.zza(this.zzdzs, this.zzect);
    }
}

