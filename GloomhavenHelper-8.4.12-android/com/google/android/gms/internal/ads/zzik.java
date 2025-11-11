package com.google.android.gms.internal.ads;

final class zzik implements zzif {
    private final zzii zzalf;

    private zzik(zzii zzii0) {
        this.zzalf = zzii0;
        super();
    }

    zzik(zzii zzii0, zzil zzil0) {
        this(zzii0);
    }

    @Override  // com.google.android.gms.internal.ads.zzif
    public final void zzc(int v, long v1, long v2) {
        zzii.zza(this.zzalf).zza(v, v1, v2);
    }

    @Override  // com.google.android.gms.internal.ads.zzif
    public final void zzef() {
        zzii.zza(this.zzalf, true);
    }

    @Override  // com.google.android.gms.internal.ads.zzif
    public final void zzs(int v) {
        zzii.zza(this.zzalf).zzr(v);
    }
}

