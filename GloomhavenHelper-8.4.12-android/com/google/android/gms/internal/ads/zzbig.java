package com.google.android.gms.internal.ads;

final class zzbig implements zzcsw {
    private zzbpt zzemz;
    private final zzbhf zzene;
    private zzcta zzfcq;

    private zzbig(zzbhf zzbhf0) {
        this.zzene = zzbhf0;
        super();
    }

    zzbig(zzbhf zzbhf0, zzbhi zzbhi0) {
        this(zzbhf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcsw
    public final zzcsw zza(zzcta zzcta0) {
        this.zzfcq = (zzcta)zzeep.checkNotNull(zzcta0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzcsw
    public final zzcsx zzafi() {
        zzeep.zza(this.zzemz, zzbpt.class);
        zzeep.zza(this.zzfcq, zzcta.class);
        zzcta zzcta0 = this.zzfcq;
        zzbog zzbog0 = new zzbog();
        zzchc zzchc0 = new zzchc();
        zzbpt zzbpt0 = this.zzemz;
        zzdfp zzdfp0 = new zzdfp();
        return new zzbif(this.zzene, zzcta0, zzbog0, zzchc0, zzbpt0, zzdfp0, null, null, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzcsw
    public final zzcsw zzf(zzbpt zzbpt0) {
        this.zzemz = (zzbpt)zzeep.checkNotNull(zzbpt0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzcsw
    @Deprecated
    public final zzcsw zzf(zzbtl zzbtl0) {
        zzeep.checkNotNull(zzbtl0);
        return this;
    }
}

