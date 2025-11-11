package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzczl implements Callable {
    private final zzczm zzglo;

    zzczl(zzczm zzczm0) {
        this.zzglo = zzczm0;
    }

    @Override
    public final Object call() {
        return new zzczj(this.zzglo.zzglp.zzg(this.zzglo.zzur));
    }
}

