package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;

final class zzvc extends zzve {
    private final zzus zzcdw;
    private final View zzcdy;
    private final HashMap zzcdz;
    private final HashMap zzcea;

    zzvc(zzus zzus0, View view0, HashMap hashMap0, HashMap hashMap1) {
        this.zzcdw = zzus0;
        this.zzcdy = view0;
        this.zzcdz = hashMap0;
        this.zzcea = hashMap1;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.zzcdy), ObjectWrapper.wrap(this.zzcdz), ObjectWrapper.wrap(this.zzcea));
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.zzcdy.getContext(), "native_ad_view_holder_delegate");
        return new zzyr();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcds.zzb(this.zzcdy, this.zzcdz, this.zzcea);
    }
}

