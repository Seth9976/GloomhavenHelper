package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdcc implements zzdnu {
    zzdcc(zzdbx zzdbx0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@NullableDecl Object object0) {
        Void void0 = (Void)object0;
        zzawf.zzee("Notification of cache hit successful.");
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        zzawf.zzee("Notification of cache hit failed.");
    }
}

