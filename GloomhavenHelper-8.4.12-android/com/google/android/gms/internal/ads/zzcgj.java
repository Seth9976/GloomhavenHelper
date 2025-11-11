package com.google.android.gms.internal.ads;

public final class zzcgj implements zzeej {
    private final zzeew zzelu;
    private final zzeew zzerf;

    private zzcgj(zzeew zzeew0, zzeew zzeew1) {
        this.zzerf = zzeew0;
        this.zzelu = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgg(((zzcgq)this.zzerf.get()), ((zzcgx)this.zzelu.get()));
    }

    public static zzcgj zzab(zzeew zzeew0, zzeew zzeew1) {
        return new zzcgj(zzeew0, zzeew1);
    }
}

