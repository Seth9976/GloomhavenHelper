package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.OnPaidEventListener;

public final class zzyx extends zzxc {
    @Nullable
    private final OnPaidEventListener zzcgi;

    public zzyx(@Nullable OnPaidEventListener onPaidEventListener0) {
        this.zzcgi = onPaidEventListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzxd
    public final void zza(zzum zzum0) {
        if(this.zzcgi != null) {
            AdValue adValue0 = AdValue.zza(zzum0.zzabo, zzum0.zzabp, zzum0.zzabq);
            this.zzcgi.onPaidEvent(adValue0);
        }
    }
}

