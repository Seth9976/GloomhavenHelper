package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzuz extends zzve {
    private final Context val$context;
    private final String zzcdu;
    private final zzus zzcdw;
    private final zzuk zzcdx;

    zzuz(zzus zzus0, Context context0, zzuk zzuk0, String s) {
        this.zzcdw = zzus0;
        this.val$context = context0;
        this.zzcdx = zzuk0;
        this.zzcdu = s;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.val$context), this.zzcdx, this.zzcdu, 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzou() {
        zzus.zza(this.val$context, "search");
        return new zzyk();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdl.zza(this.val$context, this.zzcdx, this.zzcdu, null, 3);
    }
}

