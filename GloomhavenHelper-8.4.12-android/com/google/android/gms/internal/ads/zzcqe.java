package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzcqe implements zzbxb {
    private final zzcmd zzgbo;

    zzcqe(zzcmd zzcmd0) {
        this.zzgbo = zzcmd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        try {
            ((zzdfb)this.zzgbo.zzdel).setImmersiveMode(z);
            ((zzdfb)this.zzgbo.zzdel).showVideo();
        }
        catch(zzdfa zzdfa0) {
            zzawf.zzd("Cannot show rewarded video.", zzdfa0);
        }
    }
}

