package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdmy extends zzdmv {
    zzdmy(zzdof zzdof0, zzdng zzdng0) {
        super(zzdof0, zzdng0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmv
    final void setResult(Object object0) {
        this.setFuture(((zzdof)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdmv
    final Object zzc(Object object0, @NullableDecl Object object1) throws Exception {
        zzdof zzdof0 = ((zzdng)object0).zzf(object1);
        zzdlg.zza(zzdof0, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", ((zzdng)object0));
        return zzdof0;
    }
}

