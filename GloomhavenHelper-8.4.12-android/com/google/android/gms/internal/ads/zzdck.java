package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdck implements zzdku {
    private final zzdci zzgog;

    zzdck(zzdci zzdci0) {
        this.zzgog = zzdci0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    @NullableDecl
    public final Object apply(@NullableDecl Object object0) {
        zzazh.zzc("", ((zzcjv)object0));
        zzawf.zzee("Failed to get a cache key, reverting to legacy flow.");
        zzdcm zzdcm0 = new zzdcm(null, this.zzgog.zzaqp(), null);
        this.zzgog.zzgof = zzdcm0;
        return this.zzgog.zzgof;
    }
}

