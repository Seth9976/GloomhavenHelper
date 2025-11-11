package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

abstract class zzdlm extends zzdml {
    private int position;
    private final int size;

    protected zzdlm(int v, int v1) {
        zzdlg.zzt(v1, v);
        this.size = v;
        this.position = v1;
    }

    protected abstract Object get(int arg1);

    @Override
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override
    public final Object next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int v = this.position;
        this.position = v + 1;
        return this.get(v);
    }

    @Override
    public final int nextIndex() {
        return this.position;
    }

    @Override
    public final Object previous() {
        if(!this.hasPrevious()) {
            throw new NoSuchElementException();
        }
        int v = this.position - 1;
        this.position = v;
        return this.get(v);
    }

    @Override
    public final int previousIndex() {
        return this.position - 1;
    }
}

