package com.google.android.gms.internal.ads;

import java.util.AbstractMap.SimpleImmutableEntry;

final class zzdmg extends zzdlr {
    private final zzdmd zzhaw;

    zzdmg(zzdmd zzdmd0) {
        this.zzhaw = zzdmd0;
        super();
    }

    @Override
    public final Object get(int v) {
        zzdlg.zzs(v, this.zzhaw.size);
        return new AbstractMap.SimpleImmutableEntry(this.zzhaw.zzhas[v * 2], this.zzhaw.zzhas[v * 2 + 1]);
    }

    @Override
    public final int size() {
        return this.zzhaw.size;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final boolean zzaub() {
        return true;
    }
}

