package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzdzn implements Iterator {
    private Iterator zzhui;

    public zzdzn(Iterator iterator0) {
        this.zzhui = iterator0;
    }

    @Override
    public final boolean hasNext() {
        return this.zzhui.hasNext();
    }

    @Override
    public final Object next() {
        Object object0 = this.zzhui.next();
        return ((Map.Entry)object0).getValue() instanceof zzdzm ? new zzdzo(((Map.Entry)object0), null) : ((Map.Entry)object0);
    }

    @Override
    public final void remove() {
        this.zzhui.remove();
    }
}

