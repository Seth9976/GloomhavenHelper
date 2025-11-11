package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzux extends zzve {
    private final Activity val$activity;
    private final zzus zzcdw;

    zzux(zzus zzus0, Activity activity0) {
        this.zzcdw = zzus0;
        this.val$activity = activity0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zzb(ObjectWrapper.wrap(this.val$activity));
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.val$activity, "ad_overlay");
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdr.zzc(this.val$activity);
    }
}

