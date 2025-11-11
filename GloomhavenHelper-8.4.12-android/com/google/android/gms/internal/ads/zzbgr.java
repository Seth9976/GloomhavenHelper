package com.google.android.gms.internal.ads;

public final class zzbgr implements zzeej {
    private final zzbgn zzeky;
    private final zzeew zzekz;

    public zzbgr(zzbgn zzbgn0, zzeew zzeew0) {
        this.zzeky = zzbgn0;
        this.zzekz = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzcmc)zzeep.zza(new zzcof(((zzcnk)this.zzekz.get())), "Cannot return null from a non-@Nullable @Provides method");
    }
}

