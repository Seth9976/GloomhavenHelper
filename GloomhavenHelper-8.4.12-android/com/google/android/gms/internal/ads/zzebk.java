package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzebk implements Iterator {
    private int pos;
    private final zzebi zzhxd;
    private Iterator zzhxe;

    private zzebk(zzebi zzebi0) {
        this.zzhxd = zzebi0;
        super();
        this.pos = this.zzhxd.zzhwy.size();
    }

    zzebk(zzebi zzebi0, zzebh zzebh0) {
        this(zzebi0);
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean hasNext() {
        return this.pos > 0 && this.pos <= this.zzhxd.zzhwy.size() || this.zzbfb().hasNext();
    }

    @Override
    public final Object next() {
        if(this.zzbfb().hasNext()) {
            return this.zzbfb().next();
        }
        int v = this.pos - 1;
        this.pos = v;
        return (Map.Entry)this.zzhxd.zzhwy.get(v);
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator zzbfb() {
        if(this.zzhxe == null) {
            this.zzhxe = this.zzhxd.zzhxb.entrySet().iterator();
        }
        return this.zzhxe;
    }
}

