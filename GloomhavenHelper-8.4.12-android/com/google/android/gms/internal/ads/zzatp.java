package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.rewarded.RewardItem;

public final class zzatp extends zzasr {
    private final String type;
    private final int zzdot;

    public zzatp(@Nullable RewardItem rewardItem0) {
        this((rewardItem0 == null ? "" : rewardItem0.getType()), (rewardItem0 == null ? 1 : rewardItem0.getAmount()));
    }

    public zzatp(@Nullable zzasq zzasq0) {
        this((zzasq0 == null ? "" : zzasq0.type), (zzasq0 == null ? 1 : zzasq0.zzdot));
    }

    public zzatp(String s, int v) {
        this.type = s;
        this.zzdot = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzass
    public final int getAmount() throws RemoteException {
        return this.zzdot;
    }

    @Override  // com.google.android.gms.internal.ads.zzass
    public final String getType() throws RemoteException {
        return this.type;
    }
}

