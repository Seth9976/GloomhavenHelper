package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.util.List;

final class zzyb extends zzahf {
    private final OnInitializationCompleteListener zzcfu;
    private final zzxu zzcfv;

    private zzyb(zzxu zzxu0, OnInitializationCompleteListener onInitializationCompleteListener0) {
        this.zzcfv = zzxu0;
        super();
        this.zzcfu = onInitializationCompleteListener0;
    }

    zzyb(zzxu zzxu0, OnInitializationCompleteListener onInitializationCompleteListener0, zzxy zzxy0) {
        this(zzxu0, onInitializationCompleteListener0);
    }

    @Override  // com.google.android.gms.internal.ads.zzahc
    public final void zzd(List list0) throws RemoteException {
        InitializationStatus initializationStatus0 = zzxu.zza(this.zzcfv, list0);
        this.zzcfu.onInitializationComplete(initializationStatus0);
    }
}

