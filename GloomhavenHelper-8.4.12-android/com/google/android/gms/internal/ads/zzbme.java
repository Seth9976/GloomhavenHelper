package com.google.android.gms.internal.ads;

public final class zzbme implements zzeej {
    private final zzeew zzezp;
    private final zzeew zzezq;
    private final zzeew zzfgu;

    public zzbme(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfgu = zzeew0;
        this.zzezq = zzeew1;
        this.zzezp = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdeu zzdeu0 = (zzdeu)this.zzfgu.get();
        zzcnf zzcnf0 = (zzcnf)this.zzezq.get();
        zzcml zzcml0 = (zzcml)this.zzezp.get();
        if(zzdeu0.zzaqx() == null || !((Boolean)zzvh.zzpd().zzd(zzzx.zzcki)).booleanValue()) {
            zzcnf0 = zzcml0;
        }
        return (zzcly)zzeep.zza(zzcnf0, "Cannot return null from a non-@Nullable @Provides method");
    }
}

