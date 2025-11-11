package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzcub implements zzeej {
    private final zzeew zzgid;

    private zzcub(zzeew zzeew0) {
        this.zzgid = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcub.zzd(((Set)this.zzgid.get()));
    }

    public static zzcub zzaj(zzeew zzeew0) {
        return new zzcub(zzeew0);
    }

    public static zzctz zzd(Set set0) {
        return new zzctz(set0);
    }
}

