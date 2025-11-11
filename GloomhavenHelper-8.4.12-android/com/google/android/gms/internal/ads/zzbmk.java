package com.google.android.gms.internal.ads;

public final class zzbmk implements zzeej {
    private final zzeew zzfgm;
    private final zzeew zzfgx;
    private final zzeew zzfgy;
    private final zzeew zzfgz;

    public zzbmk(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfgx = zzeew0;
        this.zzfgm = zzeew1;
        this.zzfgy = zzeew2;
        this.zzfgz = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdif zzdif0 = (zzdif)this.zzfgx.get();
        zzdoe zzdoe0 = (zzdoe)this.zzfgm.get();
        zzcmu zzcmu0 = (zzcmu)this.zzfgy.get();
        return (zzcly)zzeep.zza(new zzcqp(zzdif0, zzdoe0, ((zzcql)this.zzfgz.get()), zzcmu0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

