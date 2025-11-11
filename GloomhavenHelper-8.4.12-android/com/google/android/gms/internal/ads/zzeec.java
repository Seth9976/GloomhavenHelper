package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

final class zzeec implements Iterator {
    private int pos;
    private final zzedz zzifw;

    zzeec(zzedz zzedz0) {
        this.zzifw = zzedz0;
        super();
        this.pos = 0;
    }

    @Override
    public final boolean hasNext() {
        return this.pos < this.zzifw.zzifu.size() || this.zzifw.zzifv.hasNext();
    }

    @Override
    public final Object next() {
        while(this.pos >= this.zzifw.zzifu.size()) {
            List list0 = this.zzifw.zzifu;
            Object object0 = this.zzifw.zzifv.next();
            list0.add(object0);
        }
        int v = this.pos;
        this.pos = v + 1;
        return this.zzifw.zzifu.get(v);
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

