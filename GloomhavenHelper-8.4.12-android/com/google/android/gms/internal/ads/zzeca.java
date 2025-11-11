package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzeca extends AbstractList implements zzdzs, RandomAccess {
    private final zzdzs zzhxp;

    public zzeca(zzdzs zzdzs0) {
        this.zzhxp = zzdzs0;
    }

    @Override
    public final Object get(int v) {
        return (String)this.zzhxp.get(v);
    }

    @Override
    public final Iterator iterator() {
        return new zzecc(this);
    }

    @Override
    public final ListIterator listIterator(int v) {
        return new zzebz(this, v);
    }

    @Override
    public final int size() {
        return this.zzhxp.size();
    }

    static zzdzs zza(zzeca zzeca0) {
        return zzeca0.zzhxp;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final void zzaj(zzdxn zzdxn0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final List zzbdu() {
        return this.zzhxp.zzbdu();
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final zzdzs zzbdv() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final Object zzgm(int v) {
        return this.zzhxp.zzgm(v);
    }
}

