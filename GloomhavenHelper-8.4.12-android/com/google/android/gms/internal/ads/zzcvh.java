package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

public final class zzcvh implements zzeej {
    private final zzeew zzemh;
    private final zzeew zzfex;

    public zzcvh(zzeew zzeew0, zzeew zzeew1) {
        this.zzemh = zzeew0;
        this.zzfex = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Object object0 = this.zzemh.get();
        Clock clock0 = (Clock)this.zzfex.get();
        return (zzcvg)zzeep.zza(new zzcvg(((zzcyk)object0), ((long)(((Long)zzaba.zzcto.get()))), clock0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

