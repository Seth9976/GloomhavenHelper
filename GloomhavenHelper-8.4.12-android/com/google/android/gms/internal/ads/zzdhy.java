package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzdhy implements Callable {
    private final zzdhp zzguy;

    zzdhy(zzdhp zzdhp0) {
        this.zzguy = zzdhp0;
    }

    @Override
    public final Object call() {
        this.zzguy.run();
        return null;
    }
}

