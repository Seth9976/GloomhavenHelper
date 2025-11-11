package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

final class zzcde implements zzafz {
    private final WeakReference zzftu;
    private final String zzftv;
    private final zzafz zzftw;
    private final zzccv zzftx;

    private zzcde(zzccv zzccv0, WeakReference weakReference0, String s, zzafz zzafz0) {
        this.zzftx = zzccv0;
        super();
        this.zzftu = weakReference0;
        this.zzftv = s;
        this.zzftw = zzafz0;
    }

    zzcde(zzccv zzccv0, WeakReference weakReference0, String s, zzafz zzafz0, zzccw zzccw0) {
        this(zzccv0, weakReference0, s, zzafz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        Object object1 = this.zzftu.get();
        if(object1 == null) {
            this.zzftx.zzb(this.zzftv, this);
            return;
        }
        this.zzftw.zza(object1, map0);
    }
}

