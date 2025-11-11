package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzczm implements zzcye {
    private zzdoe zzfrv;
    zzsb zzglp;
    Context zzur;

    public zzczm(zzsb zzsb0, zzdoe zzdoe0, Context context0) {
        this.zzglp = zzsb0;
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return this.zzfrv.zzd(new zzczl(this));
    }
}

