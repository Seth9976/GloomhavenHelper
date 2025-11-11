package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;

final class zzanb implements Runnable {
    private final zzamr zzdfi;
    private final ErrorCode zzdfj;

    zzanb(zzamr zzamr0, ErrorCode adRequest$ErrorCode0) {
        this.zzdfi = zzamr0;
        this.zzdfj = adRequest$ErrorCode0;
        super();
    }

    @Override
    public final void run() {
        try {
            int v = zzand.zza(this.zzdfj);
            this.zzdfi.zzdex.onAdFailedToLoad(v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

