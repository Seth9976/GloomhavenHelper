package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.ShouldDelayBannerRenderingListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzza extends zzaek {
    private final ShouldDelayBannerRenderingListener zzcgj;

    public zzza(ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener0) {
        this.zzcgj = shouldDelayBannerRenderingListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzael
    public final boolean zzm(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Runnable runnable0 = (Runnable)ObjectWrapper.unwrap(iObjectWrapper0);
        return this.zzcgj.shouldDelayBannerRendering(runnable0);
    }
}

