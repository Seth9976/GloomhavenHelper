package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzecc implements Iterator {
    private final zzeca zzhxo;
    private Iterator zzhyk;

    zzecc(zzeca zzeca0) {
        this.zzhxo = zzeca0;
        super();
        this.zzhyk = zzeca.zza(this.zzhxo).iterator();
    }

    @Override
    public final boolean hasNext() {
        return this.zzhyk.hasNext();
    }

    @Override
    public final Object next() {
        return this.zzhyk.next();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

