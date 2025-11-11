package com.google.android.gms.internal.ads;

public final class zzcgt implements zzeej {
    private final zzeew zzelu;

    private zzcgt(zzeew zzeew0) {
        this.zzelu = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgq(((zzcgx)this.zzelu.get()));
    }

    public static zzcgt zzaa(zzeew zzeew0) {
        return new zzcgt(zzeew0);
    }
}

