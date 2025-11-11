package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final class zzdny {
    private final boolean zzhcf;
    private final zzdlr zzhcz;

    private zzdny(boolean z, zzdlr zzdlr0) {
        this.zzhcf = z;
        this.zzhcz = zzdlr0;
    }

    zzdny(boolean z, zzdlr zzdlr0, zzdnw zzdnw0) {
        this(z, zzdlr0);
    }

    public final zzdof zza(Callable callable0, Executor executor0) {
        return new zzdnh(this.zzhcz, this.zzhcf, executor0, callable0);
    }
}

