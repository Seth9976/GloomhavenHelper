package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public final class zzcdl implements zzagj {
    private final zzbrc zzfil;
    @Nullable
    private final zzasq zzftz;
    private final String zzfua;
    private final String zzfub;

    public zzcdl(zzbrc zzbrc0, zzdei zzdei0) {
        this.zzfil = zzbrc0;
        this.zzftz = zzdei0.zzdmd;
        this.zzfua = zzdei0.zzddf;
        this.zzfub = zzdei0.zzddg;
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    @ParametersAreNonnullByDefault
    public final void zza(zzasq zzasq0) {
        int v;
        String s = "";
        zzasq zzasq1 = this.zzftz;
        if(zzasq1 != null) {
            zzasq0 = zzasq1;
        }
        if(zzasq0 == null) {
            v = 1;
        }
        else {
            s = zzasq0.type;
            v = zzasq0.zzdot;
        }
        zzarp zzarp0 = new zzarp(s, v);
        this.zzfil.zzb(zzarp0, this.zzfua, this.zzfub);
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    public final void zzrx() {
        this.zzfil.onRewardedVideoStarted();
    }

    @Override  // com.google.android.gms.internal.ads.zzagj
    public final void zzry() {
        this.zzfil.onRewardedVideoCompleted();
    }
}

