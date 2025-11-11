package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzib implements Iterator {
    private Iterator zza;
    private final zzhz zzb;

    zzib(zzhz zzhz0) {
        this.zzb = zzhz0;
        super();
        this.zza = zzhz.zza(this.zzb).iterator();
    }

    @Override
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override
    public final Object next() {
        return this.zza.next();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

