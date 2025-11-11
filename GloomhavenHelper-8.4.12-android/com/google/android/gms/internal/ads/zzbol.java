package com.google.android.gms.internal.ads;

public final class zzbol implements zzeej {
    private final zzbog zzfja;
    private final zzeew zzfjb;

    private zzbol(zzbog zzbog0, zzeew zzeew0) {
        this.zzfja = zzbog0;
        this.zzfjb = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbop)this.zzfjb.get()), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbol zzd(zzbog zzbog0, zzeew zzeew0) {
        return new zzbol(zzbog0, zzeew0);
    }
}

