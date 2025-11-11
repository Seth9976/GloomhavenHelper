package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.RewardItem;

public final class zzath implements RewardItem {
    private final zzass zzdpd;

    public zzath(zzass zzass0) {
        this.zzdpd = zzass0;
    }

    @Override  // com.google.android.gms.ads.rewarded.RewardItem
    public final int getAmount() {
        zzass zzass0 = this.zzdpd;
        if(zzass0 == null) {
            return 0;
        }
        try {
            return zzass0.getAmount();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Could not forward getAmount to RewardItem", remoteException0);
            return 0;
        }
    }

    @Override  // com.google.android.gms.ads.rewarded.RewardItem
    public final String getType() {
        zzass zzass0 = this.zzdpd;
        if(zzass0 == null) {
            return null;
        }
        try {
            return zzass0.getType();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Could not forward getType to RewardItem", remoteException0);
            return null;
        }
    }
}

