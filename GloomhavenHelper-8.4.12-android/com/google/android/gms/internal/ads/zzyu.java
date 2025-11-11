package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;

public final class zzyu extends zzxb {
    private final OnAdMetadataChangedListener zzcgg;

    public zzyu(OnAdMetadataChangedListener onAdMetadataChangedListener0) {
        this.zzcgg = onAdMetadataChangedListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwy
    public final void onAdMetadataChanged() throws RemoteException {
        OnAdMetadataChangedListener onAdMetadataChangedListener0 = this.zzcgg;
        if(onAdMetadataChangedListener0 != null) {
            onAdMetadataChangedListener0.onAdMetadataChanged();
        }
    }
}

