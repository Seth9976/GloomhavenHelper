package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

final class zzcqj extends zzasl {
    private final zzbrv zzgdx;
    private final zzbqg zzgdy;
    private final zzbrc zzgdz;
    private final zzbvf zzgea;

    zzcqj(zzcqf zzcqf0, zzbrv zzbrv0, zzbqg zzbqg0, zzbrc zzbrc0, zzbvf zzbvf0) {
        this.zzgdx = zzbrv0;
        this.zzgdy = zzbqg0;
        this.zzgdz = zzbrc0;
        this.zzgea = zzbvf0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zza(IObjectWrapper iObjectWrapper0, zzasq zzasq0) {
        this.zzgea.zza(zzasq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaf(IObjectWrapper iObjectWrapper0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzag(IObjectWrapper iObjectWrapper0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzah(IObjectWrapper iObjectWrapper0) {
        this.zzgdx.zztk();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzai(IObjectWrapper iObjectWrapper0) {
        this.zzgea.zzrx();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzaj(IObjectWrapper iObjectWrapper0) {
        this.zzgdx.zztj();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzak(IObjectWrapper iObjectWrapper0) {
        this.zzgdy.onAdClicked();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzal(IObjectWrapper iObjectWrapper0) {
        this.zzgdz.onAdLeftApplication();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzam(IObjectWrapper iObjectWrapper0) throws RemoteException {
        this.zzgdz.onRewardedVideoCompleted();
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzb(Bundle bundle0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zzd(IObjectWrapper iObjectWrapper0, int v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzasm
    public final void zze(IObjectWrapper iObjectWrapper0, int v) {
    }
}

