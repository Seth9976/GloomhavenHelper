package com.google.android.gms.internal.ads;

public final class zzcll implements zzeej {
    private final zzeew zzgai;

    private zzcll(zzeew zzeew0) {
        this.zzgai = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcli(((zzclr)this.zzgai.get()));
    }

    public static zzcll zzae(zzeew zzeew0) {
        return new zzcll(zzeew0);
    }
}

