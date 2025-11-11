package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzauu implements Callable {
    private final Context zzcft;
    private final zzaui zzdqx;

    zzauu(zzaui zzaui0, Context context0) {
        this.zzdqx = zzaui0;
        this.zzcft = context0;
    }

    @Override
    public final Object call() {
        return this.zzdqx.zzal(this.zzcft);
    }
}

