package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzcsf implements zzbsg {
    private final AtomicReference zzggc;

    public zzcsf() {
        this.zzggc = new AtomicReference();
    }

    @Override  // com.google.android.gms.internal.ads.zzbsg
    public final void zzb(zzum zzum0) {
        zzcse zzcse0 = new zzcse(zzum0);
        zzdce.zza(this.zzggc, zzcse0);
    }

    public final void zzb(zzxd zzxd0) {
        this.zzggc.set(zzxd0);
    }
}

