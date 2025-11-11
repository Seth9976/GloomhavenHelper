package com.google.android.gms.internal.ads;

final class zzdmr extends zzdms {
    zzdmr(zzdof zzdof0, Class class0, zzdng zzdng0) {
        super(zzdof0, class0, zzdng0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdms
    final void setResult(Object object0) {
        this.setFuture(((zzdof)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdms
    final Object zza(Object object0, Throwable throwable0) throws Exception {
        zzdof zzdof0 = ((zzdng)object0).zzf(throwable0);
        zzdlg.zza(zzdof0, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", ((zzdng)object0));
        return zzdof0;
    }
}

