package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.ads.zzbfh;

final class zzd implements zzbfh {
    private final zze zzdht;

    zzd(zze zze0) {
        this.zzdht = zze0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfh
    public final void zzai(boolean z) {
        zze zze0 = this.zzdht;
        if(zze0.zzdae != null) {
            zze0.zzdae.zztw();
        }
    }
}

