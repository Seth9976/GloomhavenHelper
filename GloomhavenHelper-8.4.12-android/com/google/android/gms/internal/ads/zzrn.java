package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.appopen.AppOpenAd;

public final class zzrn extends AppOpenAd {
    private final zzrg zzbrg;

    public zzrn(zzrg zzrg0) {
        this.zzbrg = zzrg0;
    }

    @Override  // com.google.android.gms.ads.appopen.AppOpenAd
    protected final void zza(zzrm zzrm0) {
        try {
            this.zzbrg.zza(zzrm0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.appopen.AppOpenAd
    protected final zzvx zzdr() {
        try {
            return this.zzbrg.zzdr();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }
}

