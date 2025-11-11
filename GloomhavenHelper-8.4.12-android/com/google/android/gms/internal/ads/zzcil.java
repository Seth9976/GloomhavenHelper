package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzcil implements Callable {
    private final Context zzcft;
    private final zzdq zzfxs;

    zzcil(zzdq zzdq0, Context context0) {
        this.zzfxs = zzdq0;
        this.zzcft = context0;
    }

    @Override
    public final Object call() {
        return this.zzfxs.zzcb().zzb(this.zzcft);
    }
}

