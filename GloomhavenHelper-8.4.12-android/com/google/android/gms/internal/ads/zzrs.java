package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@ParametersAreNonnullByDefault
public final class zzrs {
    private final Object zzbro;
    @GuardedBy("poolLock")
    private boolean zzbrp;

    public zzrs() {
        this.zzbro = new Object();
        this.zzbrp = false;
    }
}

