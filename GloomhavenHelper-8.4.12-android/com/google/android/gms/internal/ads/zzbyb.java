package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

final class zzbyb implements zzafz {
    private WeakReference zzfnt;

    private zzbyb(zzbxz zzbxz0) {
        this.zzfnt = new WeakReference(zzbxz0);
    }

    zzbyb(zzbxz zzbxz0, zzbxy zzbxy0) {
        this(zzbxz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzbxz zzbxz0 = (zzbxz)this.zzfnt.get();
        if(zzbxz0 == null) {
            return;
        }
        if("_ac".equals(((String)map0.get("eventName")))) {
            zzbxz0.zzfng.onAdClicked();
        }
    }
}

