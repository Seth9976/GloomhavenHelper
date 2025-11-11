package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.util.List;

final class zzdxa extends zzdwz {
    private final zzdwy zzhnn;

    zzdxa() {
        this.zzhnn = new zzdwy();
    }

    @Override  // com.google.android.gms.internal.ads.zzdwz
    public final void zza(Throwable throwable0, PrintWriter printWriter0) {
        throwable0.printStackTrace(printWriter0);
        List list0 = this.zzhnn.zza(throwable0, false);
        if(list0 == null) {
            return;
        }
        synchronized(list0) {
            for(Object object0: list0) {
                printWriter0.print("Suppressed: ");
                ((Throwable)object0).printStackTrace(printWriter0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdwz
    public final void zza(Throwable throwable0, Throwable throwable1) {
        if(throwable1 == throwable0) {
            throw new IllegalArgumentException("Self suppression is not allowed.", throwable1);
        }
        if(throwable1 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzhnn.zza(throwable0, true).add(throwable1);
    }
}

