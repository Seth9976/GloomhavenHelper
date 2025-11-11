package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdReason;

public final class zzwz implements MuteThisAdReason {
    private final String description;
    private zzwu zzceq;

    public zzwz(zzwu zzwu0) {
        String s;
        this.zzceq = zzwu0;
        try {
            s = zzwu0.getDescription();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            s = null;
        }
        this.description = s;
    }

    @Override  // com.google.android.gms.ads.MuteThisAdReason
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final String toString() {
        return this.description;
    }

    public final zzwu zzpn() {
        return this.zzceq;
    }
}

