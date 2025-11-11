package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzuu extends zzve {
    private final Context val$context;
    private final String zzcdu;
    private final zzalk zzcdv;
    private final zzus zzcdw;

    zzuu(zzus zzus0, Context context0, String s, zzalk zzalk0) {
        this.zzcdw = zzus0;
        this.val$context = context0;
        this.zzcdu = s;
        this.zzcdv = zzalk0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zzb(ObjectWrapper.wrap(this.val$context), this.zzcdu, this.zzcdv, 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.val$context, "rewarded");
        return new zzyq();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return zzatj.zzd(this.val$context, this.zzcdu, this.zzcdv);
    }
}

