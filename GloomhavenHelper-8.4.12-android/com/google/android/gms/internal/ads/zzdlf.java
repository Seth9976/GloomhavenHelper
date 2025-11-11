package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdlf extends zzdla {
    private final Object zzczy;

    zzdlf(Object object0) {
        this.zzczy = object0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(@NullableDecl Object object0) {
        return object0 instanceof zzdlf ? this.zzczy.equals(((zzdlf)object0).zzczy) : false;
    }

    @Override
    public final int hashCode() {
        return this.zzczy.hashCode() + 0x598DF91C;
    }

    @Override
    public final String toString() {
        return "Optional.of(" + this.zzczy + ")";
    }

    @Override  // com.google.android.gms.internal.ads.zzdla
    public final Object zzats() {
        return this.zzczy;
    }
}

