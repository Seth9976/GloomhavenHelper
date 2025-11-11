package com.google.android.gms.internal.ads;

public final class zzbnw implements zzeej {
    private final zzbnv zzfij;

    private zzbnw(zzbnv zzbnv0) {
        this.zzfij = zzbnv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbnw.zzd(this.zzfij);
    }

    public static zzbnw zzc(zzbnv zzbnv0) {
        return new zzbnw(zzbnv0);
    }

    public static zzdeq zzd(zzbnv zzbnv0) {
        return (zzdeq)zzeep.zza(zzbnv0.zzahm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

