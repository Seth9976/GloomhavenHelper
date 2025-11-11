package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;

final class zzcsy implements zzdnu {
    private final zzava zzggz;
    private final zzcsz zzgha;

    zzcsy(zzcsz zzcsz0, zzava zzava0) {
        this.zzgha = zzcsz0;
        this.zzggz = zzava0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@Nullable Object object0) {
        zzctj zzctj0 = (zzctj)object0;
        try {
            if(zzctj0 == null) {
                this.zzggz.zzk(null, null);
                return;
            }
            this.zzggz.zzk(zzctj0.zzghm, zzctj0.zzghn);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        try {
            this.zzggz.onError("Internal error.");
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }
}

