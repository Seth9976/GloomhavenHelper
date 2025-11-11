package com.google.android.gms.internal.ads;

import android.os.Bundle;

final class zzavp {
    private long zzdrc;
    private long zzdrd;
    private final zzavq zzdre;

    public zzavp(zzavq zzavq0) {
        this.zzdre = zzavq0;
        super();
        this.zzdrc = -1L;
        this.zzdrd = -1L;
    }

    public final Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        bundle0.putLong("topen", this.zzdrc);
        bundle0.putLong("tclose", this.zzdrd);
        return bundle0;
    }

    public final long zzux() {
        return this.zzdrd;
    }

    public final void zzuy() {
        this.zzdrd = this.zzdre.zzbmz.elapsedRealtime();
    }

    public final void zzuz() {
        this.zzdrc = this.zzdre.zzbmz.elapsedRealtime();
    }
}

