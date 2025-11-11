package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdmu extends zzdms {
    zzdmu(zzdof zzdof0, Class class0, zzdku zzdku0) {
        super(zzdof0, class0, zzdku0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdms
    final void setResult(@NullableDecl Object object0) {
        this.set(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdms
    @NullableDecl
    final Object zza(Object object0, Throwable throwable0) throws Exception {
        return ((zzdku)object0).apply(throwable0);
    }
}

