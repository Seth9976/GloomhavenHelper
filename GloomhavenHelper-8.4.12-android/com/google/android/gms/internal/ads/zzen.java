package com.google.android.gms.internal.ads;

final class zzen implements Runnable {
    private final zzei zzyg;
    private final int zzyj;
    private final boolean zzyk;

    zzen(zzei zzei0, int v, boolean z) {
        this.zzyg = zzei0;
        this.zzyj = v;
        this.zzyk = z;
        super();
    }

    @Override
    public final void run() {
        zza zzbs$zza0 = this.zzyg.zzb(this.zzyj, this.zzyk);
        this.zzyg.zzxv = zzbs$zza0;
        if(zzei.zza(this.zzyj, zzbs$zza0)) {
            this.zzyg.zza(this.zzyj + 1, this.zzyk);
        }
    }
}

