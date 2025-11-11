package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzcob implements zzbxb {
    private final zzcmd zzgbo;
    private final zzcma zzgcp;

    zzcob(zzcmd zzcmd0, zzcma zzcma0) {
        this.zzgbo = zzcmd0;
        this.zzgcp = zzcma0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        try {
            zzcma zzcma0 = this.zzgcp;
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            if(((zzanq)this.zzgbo.zzdel).zzz(iObjectWrapper0)) {
                zzcma0.zzaoc();
                return;
            }
            zzawf.zzfa("Cannot show interstitial.");
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Cannot show interstitial.", remoteException0);
        }
    }
}

