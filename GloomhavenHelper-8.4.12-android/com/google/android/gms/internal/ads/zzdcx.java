package com.google.android.gms.internal.ads;

final class zzdcx implements zzdcd {
    private final String zzczs;
    private final String zzdcq;
    private final zzarr zzfkm;

    zzdcx(zzarr zzarr0, String s, String s1) {
        this.zzfkm = zzarr0;
        this.zzczs = s;
        this.zzdcq = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdcd
    public final void zzr(Object object0) {
        ((zzatg)object0).zza(new zzatp(this.zzfkm.getType(), this.zzfkm.getAmount()), this.zzczs, this.zzdcq);
    }
}

