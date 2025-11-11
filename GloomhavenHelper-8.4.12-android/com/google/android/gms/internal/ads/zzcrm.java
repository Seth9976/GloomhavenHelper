package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zze;

final class zzcrm implements zze {
    private final zzbvw zzgfc;

    zzcrm(zzcrl zzcrl0, zzbvw zzbvw0) {
        this.zzgfc = zzbvw0;
        super();
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzg(View view0) {
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjw() {
        this.zzgfc.zzadl().onAdClicked();
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjx() {
        this.zzgfc.zzadm().onAdImpression();
        this.zzgfc.zzadn().zzaix();
    }
}

