package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzhi extends zzho {
    private final zzhh zza;

    private zzhi(zzhh zzhh0) {
        this.zza = zzhh0;
        super(zzhh0, null);
    }

    zzhi(zzhh zzhh0, zzhg zzhg0) {
        this(zzhh0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzho
    public final Iterator iterator() {
        return new zzhj(this.zza, null);
    }
}

