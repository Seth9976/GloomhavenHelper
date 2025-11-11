package com.google.android.gms.internal.ads;

public final class zzbnx implements zzeej {
    private final zzbnv zzfij;

    private zzbnx(zzbnv zzbnv0) {
        this.zzfij = zzbnv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbnx.zzf(this.zzfij);
    }

    public static zzbnx zze(zzbnv zzbnv0) {
        return new zzbnx(zzbnv0);
    }

    public static String zzf(zzbnv zzbnv0) {
        return (String)zzeep.zza(zzbnv0.zzaho(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

