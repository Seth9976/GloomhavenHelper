package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzbwd implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfda;
    private final zzeew zzffh;
    private final zzeew zzfgu;
    private final zzbvz zzfmc;

    private zzbwd(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfmc = zzbvz0;
        this.zzelc = zzeew0;
        this.zzfda = zzeew1;
        this.zzffh = zzeew2;
        this.zzfgu = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return (zzbuv)zzeep.zza(new zzbuv(new zzbvy(((Context)this.zzelc.get()), ((zzazo)this.zzfda.get()), ((zzdei)this.zzffh.get()), ((zzdeu)this.zzfgu.get())), zzazq.zzdxp), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbwd zza(zzbvz zzbvz0, zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzbwd(zzbvz0, zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

