package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzdov extends zzdob {
    private final Callable zzhct;
    private final zzdot zzhdr;

    zzdov(zzdot zzdot0, Callable callable0) {
        this.zzhdr = zzdot0;
        super();
        this.zzhct = (Callable)zzdlg.checkNotNull(callable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final boolean isDone() {
        return this.zzhdr.isDone();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final Object zzaur() throws Exception {
        return this.zzhct.call();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final String zzaus() {
        return this.zzhct.toString();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final void zzb(Object object0, Throwable throwable0) {
        if(throwable0 == null) {
            this.zzhdr.set(object0);
            return;
        }
        this.zzhdr.setException(throwable0);
    }
}

