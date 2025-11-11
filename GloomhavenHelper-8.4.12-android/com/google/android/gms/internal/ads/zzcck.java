package com.google.android.gms.internal.ads;

public final class zzcck implements zzeej {
    private final zzeew zzfkd;
    private final zzeew zzfnw;
    private final zzeew zzfte;

    private zzcck(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfkd = zzeew0;
        this.zzfte = zzeew1;
        this.zzfnw = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzccl(((String)this.zzfkd.get()), ((zzbyo)this.zzfte.get()), ((zzbyz)this.zzfnw.get()));
    }

    public static zzcck zzj(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcck(zzeew0, zzeew1, zzeew2);
    }
}

