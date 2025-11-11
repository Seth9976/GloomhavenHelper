package com.google.android.gms.internal.ads;

public final class zzbmh implements zzeej {
    private final zzeew zzeun;
    private final zzeew zzezs;
    private final zzeew zzezu;

    public zzbmh(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzezs = zzeew0;
        this.zzezu = zzeew1;
        this.zzeun = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        boolean z = ((Boolean)this.zzezs.get()).booleanValue();
        zzcow zzcow0 = (zzcow)this.zzezu.get();
        zzcqp zzcqp0 = (zzcqp)this.zzeun.get();
        if(!z) {
            zzcow0 = zzcqp0;
        }
        return (zzcly)zzeep.zza(zzcow0, "Cannot return null from a non-@Nullable @Provides method");
    }
}

