package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzebj extends zzebp {
    private final zzebi zzhxd;

    private zzebj(zzebi zzebi0) {
        this.zzhxd = zzebi0;
        super(zzebi0, null);
    }

    zzebj(zzebi zzebi0, zzebh zzebh0) {
        this(zzebi0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebp
    public final Iterator iterator() {
        return new zzebk(this.zzhxd, null);
    }
}

