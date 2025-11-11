package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;

final class zzcnd implements zzbxb {
    private final zzazy zzbsd;

    zzcnd(zzazy zzazy0) {
        this.zzbsd = zzazy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        try {
            zzl.zza(context0, ((AdOverlayInfoParcel)this.zzbsd.get()), true);
        }
        catch(Exception unused_ex) {
        }
    }
}

