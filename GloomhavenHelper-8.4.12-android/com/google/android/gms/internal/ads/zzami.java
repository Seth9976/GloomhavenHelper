package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;

final class zzami implements InitializationCompleteCallback {
    private final zzahb zzdes;

    zzami(zzamg zzamg0, zzahb zzahb0) {
        this.zzdes = zzahb0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.InitializationCompleteCallback
    public final void onInitializationFailed(String s) {
        try {
            this.zzdes.onInitializationFailed(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.InitializationCompleteCallback
    public final void onInitializationSucceeded() {
        try {
            this.zzdes.onInitializationSucceeded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }
}

