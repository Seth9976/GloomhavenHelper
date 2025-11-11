package com.google.android.gms.internal.ads;

import androidx.collection.ArrayMap;

public final class zzcak implements zzbqu {
    private final zzbyz zzfne;
    private final zzbzd zzfof;

    public zzcak(zzbyz zzbyz0, zzbzd zzbzd0) {
        this.zzfne = zzbyz0;
        this.zzfof = zzbzd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        if(this.zzfne.zzakl() == null) {
            return;
        }
        zzbdv zzbdv0 = this.zzfne.zzakk();
        zzbdv zzbdv1 = this.zzfne.zzakj();
        if(zzbdv0 == null) {
            zzbdv0 = zzbdv1 == null ? null : zzbdv1;
        }
        if(this.zzfof.zzaka() && zzbdv0 != null) {
            zzbdv0.zza("onSdkImpression", new ArrayMap());
        }
    }
}

