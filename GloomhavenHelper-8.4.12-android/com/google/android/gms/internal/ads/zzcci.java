package com.google.android.gms.internal.ads;

public final class zzcci implements zzeej {
    private final zzeew zzfnw;
    private final zzeew zzfte;

    private zzcci(zzeew zzeew0, zzeew zzeew1) {
        this.zzfte = zzeew0;
        this.zzfnw = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcch(((zzbyo)this.zzfte.get()), ((zzbyz)this.zzfnw.get()));
    }

    public static zzcci zzr(zzeew zzeew0, zzeew zzeew1) {
        return new zzcci(zzeew0, zzeew1);
    }
}

