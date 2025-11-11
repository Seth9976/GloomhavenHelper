package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzuv extends zzve {
    private final Context val$context;
    private final String zzcdu;
    private final zzalk zzcdv;
    private final zzus zzcdw;
    private final zzuk zzcdx;

    zzuv(zzus zzus0, Context context0, zzuk zzuk0, String s, zzalk zzalk0) {
        this.zzcdw = zzus0;
        this.val$context = context0;
        this.zzcdx = zzuk0;
        this.zzcdu = s;
        this.zzcdv = zzalk0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.val$context), this.zzcdx, this.zzcdu, this.zzcdv, 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzou() {
        zzus.zza(this.val$context, "banner");
        return new zzyk();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdl.zza(this.val$context, this.zzcdx, this.zzcdu, this.zzcdv, 1);
    }
}

