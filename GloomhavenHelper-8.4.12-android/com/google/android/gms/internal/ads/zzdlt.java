package com.google.android.gms.internal.ads;

final class zzdlt extends zzdlm {
    private final zzdlr zzhaf;

    zzdlt(zzdlr zzdlr0, int v) {
        super(zzdlr0.size(), v);
        this.zzhaf = zzdlr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlm
    protected final Object get(int v) {
        return this.zzhaf.get(v);
    }
}

