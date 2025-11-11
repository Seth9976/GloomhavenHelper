package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzdiz implements Callable {
    private final Context zzcgz;

    zzdiz(Context context0) {
        this.zzcgz = context0;
    }

    @Override
    public final Object call() {
        return new zzss(this.zzcgz, "GLAS", null);
    }
}

