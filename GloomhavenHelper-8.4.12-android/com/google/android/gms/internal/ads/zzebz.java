package com.google.android.gms.internal.ads;

import java.util.ListIterator;

final class zzebz implements ListIterator {
    private final int zzhcc;
    private ListIterator zzhxn;
    private final zzeca zzhxo;

    zzebz(zzeca zzeca0, int v) {
        this.zzhxo = zzeca0;
        this.zzhcc = v;
        super();
        this.zzhxn = zzeca.zza(this.zzhxo).listIterator(this.zzhcc);
    }

    @Override
    public final void add(Object object0) {
        String s = (String)object0;
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean hasNext() {
        return this.zzhxn.hasNext();
    }

    @Override
    public final boolean hasPrevious() {
        return this.zzhxn.hasPrevious();
    }

    @Override
    public final Object next() {
        return (String)this.zzhxn.next();
    }

    @Override
    public final int nextIndex() {
        return this.zzhxn.nextIndex();
    }

    @Override
    public final Object previous() {
        return (String)this.zzhxn.previous();
    }

    @Override
    public final int previousIndex() {
        return this.zzhxn.previousIndex();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void set(Object object0) {
        String s = (String)object0;
        throw new UnsupportedOperationException();
    }
}

