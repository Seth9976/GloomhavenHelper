package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzvf extends zzve {
    private final Context val$context;
    private final zzalk zzcdv;
    private final zzus zzcdw;

    zzvf(zzus zzus0, Context context0, zzalk zzalk0) {
        this.zzcdw = zzus0;
        this.val$context = context0;
        this.zzcdv = zzalk0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.val$context), this.zzcdv, 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.val$context, "rewarded_video");
        return new zzys();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdp.zza(this.val$context, this.zzcdv);
    }
}

