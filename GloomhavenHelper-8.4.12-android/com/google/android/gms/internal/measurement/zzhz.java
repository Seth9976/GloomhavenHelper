package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzhz extends AbstractList implements zzfu, RandomAccess {
    private final zzfu zza;

    public zzhz(zzfu zzfu0) {
        this.zza = zzfu0;
    }

    @Override
    public final Object get(int v) {
        return (String)this.zza.get(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final zzfu i_() {
        return this;
    }

    @Override
    public final Iterator iterator() {
        return new zzib(this);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return new zzhy(this, v);
    }

    @Override
    public final int size() {
        return this.zza.size();
    }

    static zzfu zza(zzhz zzhz0) {
        return zzhz0.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final void zza(zzdv zzdv0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final Object zzb(int v) {
        return this.zza.zzb(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final List zzb() {
        return this.zza.zzb();
    }
}

