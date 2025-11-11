package com.google.android.gms.internal.ads;

public final class zzdgl implements zzdgj {
    private final String zzcc;

    public zzdgl(String s) {
        this.zzcc = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgj
    public final boolean equals(Object object0) {
        return object0 instanceof zzdgl ? this.zzcc.equals(((zzdgl)object0).zzcc) : false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdgj
    public final int hashCode() {
        return this.zzcc.hashCode();
    }

    @Override
    public final String toString() {
        return this.zzcc;
    }
}

