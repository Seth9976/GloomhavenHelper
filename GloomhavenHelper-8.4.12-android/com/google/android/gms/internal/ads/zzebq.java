package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzebq implements Iterator {
    private int pos;
    private final zzebi zzhxd;
    private Iterator zzhxe;
    private boolean zzhxi;

    private zzebq(zzebi zzebi0) {
        this.zzhxd = zzebi0;
        super();
        this.pos = -1;
    }

    zzebq(zzebi zzebi0, zzebh zzebh0) {
        this(zzebi0);
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean hasNext() {
        return this.pos + 1 < this.zzhxd.zzhwy.size() || !this.zzhxd.zzhwz.isEmpty() && this.zzbfb().hasNext();
    }

    @Override
    public final Object next() {
        this.zzhxi = true;
        int v = this.pos + 1;
        this.pos = v;
        return v < this.zzhxd.zzhwy.size() ? ((Map.Entry)this.zzhxd.zzhwy.get(this.pos)) : this.zzbfb().next();
    }

    @Override
    public final void remove() {
        if(!this.zzhxi) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzhxi = false;
        this.zzhxd.zzbez();
        if(this.pos < this.zzhxd.zzhwy.size()) {
            int v = this.pos;
            this.pos = v - 1;
            this.zzhxd.zzgy(v);
            return;
        }
        this.zzbfb().remove();
    }

    private final Iterator zzbfb() {
        if(this.zzhxe == null) {
            this.zzhxe = this.zzhxd.zzhwz.entrySet().iterator();
        }
        return this.zzhxe;
    }
}

