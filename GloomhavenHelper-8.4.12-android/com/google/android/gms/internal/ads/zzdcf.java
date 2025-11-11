package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzdcf implements zzdco {
    @Nullable
    @GuardedBy("this")
    private zzbpr zzgns;
    private final zzdco zzgod;

    public zzdcf(zzdco zzdco0) {
        this.zzgod = zzdco0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0) {
        synchronized(this) {
            if(zzdcp0.zzgoh != null) {
                this.zzgns = (zzbpr)zzdcq0.zzc(zzdcp0.zzgoi).zzadi();
                return this.zzgns.zzadx().zzb(zzdcp0.zzgoh);
            }
            zzdof zzdof1 = this.zzgod.zza(zzdcp0, zzdcq0);
            this.zzgns = (zzbpr)this.zzgod.zzaqj();
            return zzdof1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final Object zzaqj() {
        return this.zzaqk();
    }

    private final zzbpr zzaqk() {
        synchronized(this) {
        }
        return this.zzgns;
    }
}

