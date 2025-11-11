package com.google.android.gms.internal.ads;

import java.io.PrintWriter;

final class zzdxc extends zzdwz {
    @Override  // com.google.android.gms.internal.ads.zzdwz
    public final void zza(Throwable throwable0, PrintWriter printWriter0) {
        throwable0.printStackTrace(printWriter0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdwz
    public final void zza(Throwable throwable0, Throwable throwable1) {
        throwable0.addSuppressed(throwable1);
    }
}

