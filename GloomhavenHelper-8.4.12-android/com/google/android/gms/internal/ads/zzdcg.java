package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzdcg implements zzdco {
    @Nullable
    @GuardedBy("this")
    private zzbpr zzgns;

    @Override  // com.google.android.gms.internal.ads.zzdco
    public final zzdof zza(zzdcp zzdcp0, zzdcq zzdcq0) {
        synchronized(this) {
            this.zzgns = (zzbpr)zzdcq0.zzc(zzdcp0.zzgoi).zzadi();
            return this.zzgns.zzadx().zzahq();
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

