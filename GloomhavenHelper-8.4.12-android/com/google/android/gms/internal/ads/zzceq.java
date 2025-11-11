package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

public final class zzceq implements zzbqt {
    @Nullable
    private final zzbdv zzdae;

    zzceq(@Nullable zzbdv zzbdv0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcjo)).booleanValue()) {
            zzbdv0 = null;
        }
        this.zzdae = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzby(@Nullable Context context0) {
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 != null) {
            zzbdv0.onPause();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzbz(@Nullable Context context0) {
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 != null) {
            zzbdv0.onResume();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzca(@Nullable Context context0) {
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 != null) {
            zzbdv0.destroy();
        }
    }
}

