package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.rtb.SignalCallbacks;

final class zzaoc implements SignalCallbacks {
    private final zzanv zzdfv;

    zzaoc(zzanz zzanz0, zzanv zzanv0) {
        this.zzdfv = zzanv0;
        super();
    }

    @Override  // com.google.android.gms.ads.mediation.rtb.SignalCallbacks
    public final void onFailure(String s) {
        try {
            this.zzdfv.onFailure(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.mediation.rtb.SignalCallbacks
    public final void onSuccess(String s) {
        try {
            this.zzdfv.zzdo(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }
}

