package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

final class zzdma extends zzdmm {
    private boolean zzhan;
    private final Object zzhao;

    zzdma(Object object0) {
        this.zzhao = object0;
        super();
    }

    @Override
    public final boolean hasNext() {
        return !this.zzhan;
    }

    @Override
    public final Object next() {
        if(this.zzhan) {
            throw new NoSuchElementException();
        }
        this.zzhan = true;
        return this.zzhao;
    }
}

