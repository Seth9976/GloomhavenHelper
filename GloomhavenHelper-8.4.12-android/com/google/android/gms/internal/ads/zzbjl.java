package com.google.android.gms.internal.ads;

public final class zzbjl implements zzeej {
    private final zzeew zzerc;

    private zzbjl(zzeew zzeew0) {
        this.zzerc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbji(((zzawh)this.zzerc.get()));
    }

    public static zzbjl zza(zzeew zzeew0) {
        return new zzbjl(zzeew0);
    }
}

