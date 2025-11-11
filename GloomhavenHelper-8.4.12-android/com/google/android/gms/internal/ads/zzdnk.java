package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

final class zzdnk extends zzdnj {
    private final zzdnh zzhcs;
    private final Callable zzhct;

    zzdnk(zzdnh zzdnh0, Callable callable0, Executor executor0) {
        this.zzhcs = zzdnh0;
        super(zzdnh0, executor0);
        this.zzhct = (Callable)zzdlg.checkNotNull(callable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnj
    final void setValue(Object object0) {
        this.zzhcs.set(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final Object zzaur() throws Exception {
        this.zzhcr = false;
        return this.zzhct.call();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final String zzaus() {
        return this.zzhct.toString();
    }
}

