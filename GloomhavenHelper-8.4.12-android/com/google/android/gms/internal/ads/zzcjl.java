package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor;

public final class zzcjl extends zzaqu {
    private final zzcji zzfym;

    protected zzcjl(zzcji zzcji0) {
        this.zzfym = zzcji0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzaqr
    public final void zza(zzaxp zzaxp0) {
        zzaxs zzaxs0 = new zzaxs(zzaxp0.zzdux, zzaxp0.errorCode);
        this.zzfym.zzdcg.setException(zzaxs0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqr
    public final void zzb(ParcelFileDescriptor parcelFileDescriptor0) {
        ParcelFileDescriptor.AutoCloseInputStream parcelFileDescriptor$AutoCloseInputStream0 = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor0);
        this.zzfym.zzdcg.set(parcelFileDescriptor$AutoCloseInputStream0);
    }
}

