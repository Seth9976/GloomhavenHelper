package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

final class zzckt implements zzdnu {
    private final zzaqr zzfzj;

    zzckt(zzckh zzckh0, zzaqr zzaqr0) {
        this.zzfzj = zzaqr0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        ParcelFileDescriptor parcelFileDescriptor0 = (ParcelFileDescriptor)object0;
        try {
            this.zzfzj.zzb(parcelFileDescriptor0);
        }
        catch(RemoteException remoteException0) {
            zzawf.zza("Service can\'t call client", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        try {
            zzaxp zzaxp0 = zzaxp.zza(throwable0, zzcid.zzd(throwable0));
            this.zzfzj.zza(zzaxp0);
        }
        catch(RemoteException remoteException0) {
            zzawf.zza("Service can\'t call client", remoteException0);
        }
    }
}

