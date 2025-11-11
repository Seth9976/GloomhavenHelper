package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

public final class zzasg implements RewardItem {
    private final zzarr zzdox;

    public zzasg(zzarr zzarr0) {
        this.zzdox = zzarr0;
    }

    @Override  // com.google.android.gms.ads.reward.RewardItem
    public final int getAmount() {
        zzarr zzarr0 = this.zzdox;
        if(zzarr0 == null) {
            return 0;
        }
        try {
            return zzarr0.getAmount();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Could not forward getAmount to RewardItem", remoteException0);
            return 0;
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardItem
    public final String getType() {
        zzarr zzarr0 = this.zzdox;
        if(zzarr0 == null) {
            return null;
        }
        try {
            return zzarr0.getType();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Could not forward getType to RewardItem", remoteException0);
            return null;
        }
    }
}

