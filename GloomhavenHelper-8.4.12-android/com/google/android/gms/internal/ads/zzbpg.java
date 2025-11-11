package com.google.android.gms.internal.ads;

public final class zzbpg implements zzeej {
    private final zzeew zzffc;
    private final zzbph zzfjk;

    private zzbpg(zzbph zzbph0, zzeew zzeew0) {
        this.zzfjk = zzbph0;
        this.zzffc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(((zzbpf)this.zzffc.get()), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbpg zza(zzbph zzbph0, zzeew zzeew0) {
        return new zzbpg(zzbph0, zzeew0);
    }
}

