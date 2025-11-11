package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zze;

final class zzcrc implements zze {
    private final zzdeq zzgdt;
    private final zzdei zzgdu;
    private final zzazy zzget;
    private final zzcri zzgeu;
    private final zzcra zzgev;

    zzcrc(zzcra zzcra0, zzazy zzazy0, zzdeq zzdeq0, zzdei zzdei0, zzcri zzcri0) {
        this.zzgev = zzcra0;
        this.zzget = zzazy0;
        this.zzgdt = zzdeq0;
        this.zzgdu = zzdei0;
        this.zzgeu = zzcri0;
        super();
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzg(View view0) {
        Object object0 = this.zzgev.zzgeq.zza(this.zzgdt, this.zzgdu, view0, this.zzgeu);
        this.zzget.set(object0);
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjw() {
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjx() {
    }
}

