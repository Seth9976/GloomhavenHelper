package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;

final class zzazt implements zzdnu {
    private final String zzdxs;

    zzazt(String s) {
        this.zzdxs = s;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@Nullable Object object0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        zzq.zzkz().zza(throwable0, this.zzdxs);
    }
}

