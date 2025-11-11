package com.google.android.gms.internal.ads;

import android.os.Bundle;

final class zzauj implements zzauy {
    private final String zzczz;
    private final Bundle zzdqv;

    zzauj(String s, Bundle bundle0) {
        this.zzczz = s;
        this.zzdqv = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzauy
    public final void zza(zzbgd zzbgd0) {
        zzbgd0.logEvent("am", this.zzczz, this.zzdqv);
    }
}

