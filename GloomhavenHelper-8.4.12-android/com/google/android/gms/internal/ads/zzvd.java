package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzvd extends zzve {
    private final Context val$context;
    private final zzus zzcdw;
    private final FrameLayout zzceb;
    private final FrameLayout zzcec;

    zzvd(zzus zzus0, FrameLayout frameLayout0, FrameLayout frameLayout1, Context context0) {
        this.zzcdw = zzus0;
        this.zzceb = frameLayout0;
        this.zzcec = frameLayout1;
        this.val$context = context0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zza(zzwg zzwg0) throws RemoteException {
        return zzwg0.zza(ObjectWrapper.wrap(this.zzceb), ObjectWrapper.wrap(this.zzcec));
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    protected final Object zzou() {
        zzus.zza(this.val$context, "native_ad_view_delegate");
        return new zzyo();
    }

    @Override  // com.google.android.gms.internal.ads.zzve
    public final Object zzov() throws RemoteException {
        return this.zzcdw.zzcdo.zzb(this.val$context, this.zzceb, this.zzcec);
    }
}

