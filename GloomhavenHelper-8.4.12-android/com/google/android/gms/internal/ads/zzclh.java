package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzclh implements Callable {
    private final zzclc zzfzw;

    private zzclh(zzclc zzclc0) {
        this.zzfzw = zzclc0;
    }

    @Override
    public final Object call() {
        return this.zzfzw.getWritableDatabase();
    }

    static Callable zza(zzclc zzclc0) {
        return new zzclh(zzclc0);
    }
}

