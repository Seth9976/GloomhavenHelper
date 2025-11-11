package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbjx implements zzafz {
    final zzbjs zzfef;

    zzbjx(zzbjs zzbjs0) {
        this.zzfef = zzbjs0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        if(!this.zzfef.zzl(map0)) {
            return;
        }
        zzbjw zzbjw0 = new zzbjw(this);
        this.zzfef.executor.execute(zzbjw0);
    }
}

