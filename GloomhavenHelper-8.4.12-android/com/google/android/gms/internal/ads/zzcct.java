package com.google.android.gms.internal.ads;

public final class zzcct implements zzeej {
    private final zzeew zzfkd;
    private final zzeew zzfnw;
    private final zzeew zzfte;

    private zzcct(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfkd = zzeew0;
        this.zzfte = zzeew1;
        this.zzfnw = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzccq(((String)this.zzfkd.get()), ((zzbyo)this.zzfte.get()), ((zzbyz)this.zzfnw.get()));
    }

    public static zzcct zzl(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcct(zzeew0, zzeew1, zzeew2);
    }
}

