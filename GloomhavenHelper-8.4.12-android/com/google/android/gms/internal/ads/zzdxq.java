package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

final class zzdxq extends zzdxs {
    private final int limit;
    private int position;
    private final zzdxn zzhoh;

    zzdxq(zzdxn zzdxn0) {
        this.zzhoh = zzdxn0;
        super();
        this.position = 0;
        this.limit = this.zzhoh.size();
    }

    @Override
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxw
    public final byte nextByte() {
        int v = this.position;
        if(v >= this.limit) {
            throw new NoSuchElementException();
        }
        this.position = v + 1;
        return this.zzhoh.zzff(v);
    }
}

