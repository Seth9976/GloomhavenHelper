package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzcxu implements zzcye {
    private final boolean zzgkh;

    zzcxu(@Nullable zzdcu zzdcu0) {
        if(zzdcu0 != null) {
            this.zzgkh = true;
            return;
        }
        this.zzgkh = false;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return this.zzgkh ? zzdnt.zzaj(zzcxt.zzgkg) : zzdnt.zzaj(null);
    }
}

