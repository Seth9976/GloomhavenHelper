package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

public final class zzbqd implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzffh;
    private final zzbqe zzfkg;
    private final zzeew zzfkh;
    private final zzeew zzfki;

    private zzbqd(zzbqe zzbqe0, zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfkg = zzbqe0;
        this.zzelc = zzeew0;
        this.zzfkh = zzeew1;
        this.zzffh = zzeew2;
        this.zzfki = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    @Nullable
    public final Object get() {
        Object object0 = this.zzelc.get();
        Object object1 = this.zzfkh.get();
        zzdei zzdei0 = (zzdei)this.zzffh.get();
        Object object2 = this.zzfki.get();
        return zzdei0.zzgpu != null ? new zzats(((Context)object0), ((zzazo)object1), zzdei0.zzgpu, zzdei0.zzgpq.zzdiu, ((zzaud)object2)) : null;
    }

    public static zzbqd zza(zzbqe zzbqe0, zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzbqd(zzbqe0, zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

