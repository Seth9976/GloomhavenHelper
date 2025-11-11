package com.google.android.gms.internal.ads;

public final class zzcwz implements zzeej {
    private final zzeew zzfvk;

    private zzcwz(zzeew zzeew0) {
        this.zzfvk = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcxa(((String)this.zzfvk.get()));
    }

    public static zzcwz zzan(zzeew zzeew0) {
        return new zzcwz(zzeew0);
    }
}

