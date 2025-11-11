package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzva extends zzve {
    private final Context val$context;
    private final zzus zzcdw;

    zzva(zzus zzus0, Context context0) {
        this.zzcdw = zzus0;
        this.val$context = context0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.val$context), 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.val$context, "mobile_ads_settings");
        return new zzym();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdn.zzj(this.val$context);
    }
}

