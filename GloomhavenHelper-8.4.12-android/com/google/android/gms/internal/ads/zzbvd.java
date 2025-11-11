package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzbvd implements zzbva {
    private final List zzdlr;
    private final zzdis zzfdr;
    private boolean zzflr;

    public zzbvd(zzdei zzdei0, zzdis zzdis0) {
        this.zzdlr = zzdei0.zzdlr;
        this.zzfdr = zzdis0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbva
    public final void zzaix() {
        if(!this.zzflr) {
            this.zzfdr.zzh(this.zzdlr);
            this.zzflr = true;
        }
    }
}

