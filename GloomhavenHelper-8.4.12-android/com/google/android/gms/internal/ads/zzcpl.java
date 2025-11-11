package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzcpl implements zzbxb {
    private final zzcmd zzgbo;

    zzcpl(zzcmd zzcmd0) {
        this.zzgbo = zzcmd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        try {
            ((zzdfb)this.zzgbo.zzdel).setImmersiveMode(z);
            ((zzdfb)this.zzgbo.zzdel).zzcg(context0);
        }
        catch(zzdfa zzdfa0) {
            zzawf.zzd("Cannot show rewarded .", zzdfa0);
        }
    }
}

